import java.util.Random;
import java.util.Scanner;

public class NumberGuessing {
    public static void main(String[] args) {
        int min = 1; // Minimum value of the range
        int max = 100; // Maximum value of the range
        int maxAttempts = 5; // Maximum number of attempts allowed

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int roundsWon = 0;

        boolean playAgain = true;

        while (playAgain) {
            totalRounds++;
            int randomNumber = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean guessedCorrectly = false;

          

            System.out.println("Round " + totalRounds);
            System.out.println("You have only 5 attempt");
            System.out.println("Guess the random number between " + min + " and " + max + ".");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Attempt " + (attempts + 1) + ": ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    System.out.println("Attempts: " + attempts);
                    guessedCorrectly = true;
                } else if (userGuess < randomNumber) {
                    System.out.println("Nope! This number is lower. Guess again");
                } else {
                    System.out.println("Nope! This number is higher. Guess again.");
                }
            }

            if (guessedCorrectly) {
                roundsWon++;
            } else {
                System.out.println("Sorry, you've reached the maximum number of attempts.");
                System.out.println("The correct random number was: " + randomNumber);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
            System.out.println();
        }

        System.out.println("Game Over!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Rounds won: " + roundsWon);

        scanner.close();
    }
}
