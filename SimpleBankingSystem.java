import java.util.*;

// Account class to represent a bank account
class Account {
    // Private fields for encapsulation
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    // Static counter to generate unique account numbers
    private static int accountCounter = 1000;
    
    // Constructor to initialize account with name and balance
    public Account(String name, double initialBalance) {
        this.accountNumber = "ACC" + (++accountCounter);
        this.accountHolderName = name;
        this.balance = initialBalance;
    }
    
    // Method to deposit money into account
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
    
    // Method to withdraw money from account
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    // Getter methods for accessing private fields
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
}

// BankingSystem class to manage accounts and user interactions
class BankingSystem {
    // HashMap to store accounts with account number as key
    private Map<String, Account> accounts;
    // Scanner for user input
    private Scanner scanner;
    
    // Constructor to initialize banking system
    public BankingSystem() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
    }
    
    // Main method to run the banking system
    public void run() {
        while (true) {
            // Display menu options
            System.out.println("=== Simple Bank System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Balance");
            System.out.println("5. Exit");
            
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            // Handle user choice with exception handling
            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        viewBalance();
                        break;
                    case 5:
                        System.out.println("Thank you for using Simple Bank System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                // Handle any exceptions that occur
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
    
    // Method to create a new account
    private void createAccount() {
        System.out.print("Enter account holder name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        
        // Validate initial balance
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        
        // Create new account and add to accounts map
        Account account = new Account(name, balance);
        accounts.put(account.getAccountNumber(), account);
        
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
    }
    
    // Method to deposit money into an account
    private void depositMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        
        // Find account in the system
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        
        // Attempt to deposit money
        if (account.deposit(amount)) {
            System.out.println("Deposit successful!");
            System.out.println("New balance: $" + account.getBalance());
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    // Method to withdraw money from an account
    private void withdrawMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        
        // Find account in the system
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        
        // Attempt to withdraw money
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
            System.out.println("New balance: $" + account.getBalance());
        } else {
            System.out.println("Insufficient funds or invalid amount");
        }
    }
    
    // Method to view account balance and details
    private void viewBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        
        // Find account in the system
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        
        // Display account information
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getAccountHolderName());
        System.out.println("Current Balance: $" + account.getBalance());
    }
}

// Main class to run the banking system
public class SimpleBankingSystem {
    // Main method - entry point of the program
    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        bank.run();
    }
}