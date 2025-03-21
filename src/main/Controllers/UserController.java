package controllers;

import application.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {
    @FXML private Label balanceLabel;

    public void initialize() {
        loadBalance();
    }

    private void loadBalance() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT balance FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "testuser"); // Replace with actual logged-in user
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                balanceLabel.setText("Balance: $" + rs.getDouble("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
