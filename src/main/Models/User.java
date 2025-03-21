package models;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String accountType;
    private double balance;

    public User(int id, String username, String password, String email, String accountType, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }
}
