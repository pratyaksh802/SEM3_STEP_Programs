class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int counter = 1;

    public BankAccount(String name, double initialDeposit) {
        this.accountHolderName = name;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public double checkBalance() {
        return balance;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        String acc = String.format("ACC%03d", counter);
        counter++;
        return acc;
    }

    public void displayAccountInfo() {
        System.out.printf("%-10s %-15s %-10.2f\n", accountNumber, accountHolderName, balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new BankAccount("Alice", 1000);
        accounts[1] = new BankAccount("Bob", 500);
        accounts[2] = new BankAccount("Charlie", 2000);

        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        accounts[2].deposit(300);
        accounts[2].withdraw(2500);

        System.out.println("AccountNumber AccountHolder   Balance");
        for (BankAccount acc : accounts) acc.displayAccountInfo();

        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());
    }
}
