import java.util.*;

class PersonalAccount {
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;
    private static int totalAccounts = 0;
    private static String bankName;
    private static int accountCounter = 1000;

    PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0;
        this.totalIncome = 0;
        this.totalExpenses = 0;
        totalAccounts++;
    }

    public void addIncome(double amount, String description) {
        if (amount > 0) {
            totalIncome += amount;
            currentBalance += amount;
            System.out.println(accountHolderName + " credited " + amount + " for " + description);
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            totalExpenses += amount;
            currentBalance -= amount;
            System.out.println(accountHolderName + " debited " + amount + " for " + description);
        } else {
            System.out.println(accountHolderName + " failed expense of " + amount + " for " + description + " (Insufficient balance)");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary ---");
        System.out.println("Bank: " + bankName);
        System.out.println("Holder: " + accountHolderName);
        System.out.println("Account No: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Savings: " + calculateSavings());
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        return "AC" + (accountCounter++);
    }
}

public class PersonalFinanceManager {
    public static void main(String[] args) {
        PersonalAccount.setBankName("Global Trust Bank");

        PersonalAccount acc1 = new PersonalAccount("Alice");
        PersonalAccount acc2 = new PersonalAccount("Bob");
        PersonalAccount acc3 = new PersonalAccount("Charlie");

        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1200, "Groceries");
        acc1.addExpense(2500, "Rent");

        acc2.addIncome(8000, "Freelance Project");
        acc2.addExpense(2000, "Laptop");
        acc2.addExpense(1500, "Bills");

        acc3.addIncome(10000, "Business");
        acc3.addExpense(4000, "Raw Materials");
        acc3.addExpense(3000, "Transport");

        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        System.out.println("\nTotal Accounts Created: " + PersonalAccount.getTotalAccounts());
        System.out.println("Bank Name (shared by all accounts): " + "Global Trust Bank");
    }
}
