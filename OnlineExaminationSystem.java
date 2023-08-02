import java.util.*;

class Question {
    private String question;
    private List<String> options;
    private int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class User {
    private String username;
    private String password;
    private String fullName;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName2) {
    }
}

public class OnlineExaminationSystem {
    private List<User> users;
    private List<Question> questions;
    private User currentUser;
    private int timerInSeconds;

    public OnlineExaminationSystem(int timerInSeconds) {
        users = new ArrayList<>();
        questions = new ArrayList<>();
        this.timerInSeconds = timerInSeconds;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Welcome, " + currentUser.getFullName() + "!");
                return;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
    }

    public void updateProfile(String fullName, String newPassword) {
        if (currentUser != null) {
            currentUser.setFullName(fullName);
            currentUser.setPassword(newPassword);
            System.out.println("Profile and password updated successfully!");
        } else {
            System.out.println("Please log in to update your profile and password.");
        }
    }

    public void selectAnswer(int questionIndex, int selectedOption) {
        if (currentUser != null && questionIndex >= 0 && questionIndex < questions.size()) {
            Question question = questions.get(questionIndex);
            List<String> options = question.getOptions();
            if (selectedOption >= 1 && selectedOption <= options.size()) {
                String selectedAnswer = options.get(selectedOption - 1);
                System.out.println("Answer selected for question: " + question.getQuestion());
                System.out.println("Selected answer: " + selectedAnswer);
                int correctOption = question.getCorrectOption();
                String correctAnswer = options.get(correctOption - 1);
                System.out.println("Correct answer: " + correctAnswer);
            } else {
                System.out.println("Invalid option selected for the question.");
            }
        } else {
            System.out.println("Invalid question index or not logged in.");
        }
    }

    public void startExam() {
        if (currentUser != null) {
            System.out.println("Exam timer started. You have " + timerInSeconds + " seconds to complete the exam.");
            for (int i = 0; i < questions.size(); i++) {
                displayQuestion(i);
            }
        } else {
            System.out.println("Please log in to start the exam.");
        }
    }

    private void displayQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < questions.size()) {
            Question question = questions.get(questionIndex);
            System.out.println("Question " + (questionIndex + 1) + ": " + question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
        } else {
            System.out.println("Invalid question index.");
        }
    }

    public void autoSubmit() {
        if (currentUser != null) {
            System.out.println("Exam automatically submitted.");
            logout();
        } else {
            System.out.println("Please log in to auto-submit the exam.");
        }
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logout successful.");
    }

    public static void main(String[] args) {
        int examTimerInSeconds = 180; // Set the exam timer to 180 seconds (3 minutes)
        OnlineExaminationSystem examSystem = new OnlineExaminationSystem(examTimerInSeconds);

        List<String> options1 = Arrays.asList("5", "2", "1", "7");
        Question question1 = new Question("What is 1 + 1?", options1, 2);

        List<String> options2 = Arrays.asList("0", "5", "22", "4");
        Question question2 = new Question("What is 2 + 2?", options2, 4);

        examSystem.addQuestion(question1);
        examSystem.addQuestion(question2);

        User user1 = new User("user1", "password123", "Sayan");
        User user2 = new User("user2", "securepass", "Sourav");

        examSystem.addUser(user1);
        examSystem.addUser(user2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------- Online Examination System --------");
            System.out.println("1. Login");
            System.out.println("2. Update Profile and Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Select Answer");
            System.out.println("5. Auto Submit");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    examSystem.login(username, password);
                    break;

                case 2:
                    if (examSystem.currentUser != null) {
                        scanner.nextLine(); // Consume the remaining newline character from the previous input
                        System.out.print("Enter your full name: ");
                        String fullName = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.next();
                        examSystem.updateProfile(fullName, newPassword);
                    } else {
                        System.out.println("Please log in to update your profile and password.");
                    }
                    break;

                case 3:
                    examSystem.startExam();
                    break;

                case 4:
                    System.out.print("Enter question index: ");
                    int questionIndex = scanner.nextInt();
                    System.out.print("Enter selected option (1-4): ");
                    int selectedOption = scanner.nextInt();
                    examSystem.selectAnswer(questionIndex - 1, selectedOption);
                    break;

                case 5:
                    examSystem.autoSubmit();
                    break;

                case 6:
                    examSystem.logout();
                    break;

                case 7:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
