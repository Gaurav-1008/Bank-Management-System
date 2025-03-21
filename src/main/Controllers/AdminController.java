package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminController {
    @FXML private Label adminLabel;

    public void initialize() {
        adminLabel.setText("Welcome, Admin!");
    }
}
