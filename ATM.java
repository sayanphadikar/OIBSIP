import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private static class Transaction {
        private String type;
        private double amount;
        private Date date;
        
        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
            this.date = new Date();
        }
        
        public String getType() {
            return type;
        }
        
        public double getAmount() {
            return amount;
        }
        
        public Date getDate() {
            return date;
        }
    }
    
    private static class TransactionHistory {
        private List<Transaction> transactions;
        
        public TransactionHistory() {
            transactions = new ArrayList<>();
        }
        
        public void addTransaction(Transaction transaction) {
            transactions.add(transaction);
        }
        
        public List<Transaction> getTransactions() {
            return transactions;
        }
    }
    
    private static class ATMSystem {
        private double balance;
        private TransactionHistory transactionHistory;
        
        public ATMSystem(double initialBalance) {
            this.balance = initialBalance;
            this.transactionHistory = new TransactionHistory();
        }
        
        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                Transaction transaction = new Transaction("Withdraw", amount);
                transactionHistory.addTransaction(transaction);
                System.out.println("Withdrawal successful. Current balance: " + balance);
            } else {
                System.out.println("Invalid amount or insufficient balance.");
            }
        }
        
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                Transaction transaction = new Transaction("Deposit", amount);
                transactionHistory.addTransaction(transaction);
                System.out.println("Deposit successful. Current balance: " + balance);
            } else {
                System.out.println("Invalid amount.");
            }
        }
        
        public void transfer(double amount) {
            if (amount > 0 && amount <= balance) {
                
                balance -= amount;
                Transaction transaction = new Transaction("Transfer", amount);
                transactionHistory.addTransaction(transaction);
                System.out.println("Transfer successful. Current balance: " + balance);
            } else {
                System.out.println("Invalid amount or insufficient balance.");
            }
        }
        
        public void showTransactionHistory() {
            List<Transaction> transactions = transactionHistory.getTransactions();
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction.getType() + " - Amount: " + transaction.getAmount()
                        + ", Date: " + transaction.getDate());
            }
        }
    }
    
    public static void main(String[] args) {
        ATMSystem atm = new ATMSystem(1000.0);
        showMenu(atm);
    }
    
    private static void showMenu(ATMSystem atm) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Show Transaction History");
            System.out.println("5. Quit");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    atm.transfer(transferAmount);
                    break;
                case 4:
                    atm.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
        
        
        scanner.close();
    }
}
