package controllers;

import application.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionsController {
    @FXML private TextField amountField;
    @FXML private Button depositButton, withdrawButton;
    @FXML private Label messageLabel;

    @FXML
    public void handleDeposit() {
        processTransaction("Deposit");
    }

    @FXML
    public void handleWithdraw() {
        processTransaction("Withdraw");
    }

    private void processTransaction(String type) {
        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            messageLabel.setText("Amount must be positive.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Fetch current balance
            String balanceQuery = "SELECT balance FROM users WHERE id = ?";
            try (PreparedStatement balanceStmt = conn.prepareStatement(balanceQuery)) {
                balanceStmt.setInt(1, 1); // Replace with actual user ID
                try (ResultSet rs = balanceStmt.executeQuery()) {
                    if (!rs.next()) {
                        messageLabel.setText("User not found.");
                        return;
                    }

                    double balance = rs.getDouble("balance");
                    double newBalance = type.equals("Deposit") ? balance + amount : balance - amount;

                    if (newBalance < 0) {
                        messageLabel.setText("Insufficient balance.");
                        return;
                    }

                    // Update balance
                    String updateQuery = "UPDATE users SET balance = ? WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setDouble(1, newBalance);
                        updateStmt.setInt(2, 1); // Replace with actual user ID
                        updateStmt.executeUpdate();
                    }

                    // Record transaction
                    String transactionQuery = "INSERT INTO transactions (user_id, type, amount) VALUES (?, ?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(transactionQuery)) {
                        stmt.setInt(1, 1); // Replace with actual user ID
                        stmt.setString(2, type);
                        stmt.setDouble(3, amount);
                        stmt.executeUpdate();
                    }

                    messageLabel.setText(type + " successful!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Transaction failed.");
        }
    }
}
