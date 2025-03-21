package models;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int userId;
    private String type;
    private double amount;
    private Timestamp date;

    public Transaction(int id, int userId, String type, double amount, Timestamp date) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public Timestamp getDate() { return date; }
}
