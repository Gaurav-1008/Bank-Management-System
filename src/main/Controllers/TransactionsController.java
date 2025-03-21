package controllers;

import application.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
        double amount = Double.parseDouble(amountField.getText());

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO transactions (user_id, type, amount) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, 1); // Replace with actual user ID
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();

            messageLabel.setText(type + " successful!");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Transaction failed.");
        }
    }
}
