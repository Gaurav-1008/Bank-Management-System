package models;

public class Account {
    private int userId;
    private double balance;

    public Account(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }
}
