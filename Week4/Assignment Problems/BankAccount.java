import java.util.Random;

class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // Default constructor
    public BankAccount() {
        this("Unknown", 0.0);
    }

    // Constructor with name only
    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(100000);
        this.balance = 0.0;
    }

    // Constructor with name and initial balance
    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(100000);
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited. New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        } else if (balance >= amount) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void displayAccount() {
        System.out.println("Account Holder: " + accountHolder + 
                         " | Account No: " + accountNumber + 
                         " | Balance: Rs." + balance);
    }

    public static void main(String[] args) {
        // Create accounts using different constructors
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount("Alice");
        BankAccount acc3 = new BankAccount("Bob", 5000.0);
        
        // Perform transactions
        acc1.deposit(2000);
        acc2.deposit(3000);
        acc3.withdraw(1000);
        
        // Try invalid transactions
        acc1.withdraw(-100);  // Invalid amount
        acc1.deposit(-50);    // Invalid deposit
        
        // Display account information
        System.out.println("\n--- Account Details ---");
        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();
    }
}
