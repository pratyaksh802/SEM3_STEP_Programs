public class BankAccount {
    private static String bankName;
    private static int totalAccounts = 0;
    private static double interestRate;
    
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        totalAccounts++;
    }
    
    public static void setBankName(String name) {
        bankName = name;
    }
    
    public static void setInterestRate(double rate) {
        interestRate = rate;
    }
    
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    public static void displayBankInfo() {
        System.out.println("Bank Name: " + bankName);
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Current Interest Rate: " + interestRate + "%");
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount");
        }
    }
    
    public double calculateInterest() {
        return balance * (interestRate / 100);
    }
    
    public void displayAccountInfo() {
        System.out.println("\n--- Account Information ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.printf("Interest Earned (at %.2f%%): $%.2f\n", interestRate, calculateInterest());
    }
    
    public static void main(String[] args) {
        BankAccount.setBankName("Global Bank");
        BankAccount.setInterestRate(4.5);
        
        BankAccount account1 = new BankAccount("1001", "John Doe", 1000.0);
        BankAccount account2 = new BankAccount("1002", "Jane Smith", 2500.0);
        
        System.out.println("--- Bank Information ---");
        BankAccount.displayBankInfo();
        
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        
        System.out.println("\n--- Demonstrating Static vs Instance Methods ---");
        System.out.println("Calling static method with class name: " + BankAccount.getTotalAccounts() + " accounts");
        System.out.println("Calling static method with object reference: " + account1.getTotalAccounts() + " accounts");
        
        System.out.println("\n--- Performing Transactions ---");
        account1.deposit(500.0);
        account1.withdraw(200.0);
        account1.displayAccountInfo();
        
        account2.deposit(1000.0);
        account2.withdraw(300.0);
        account2.displayAccountInfo();
    }
}
