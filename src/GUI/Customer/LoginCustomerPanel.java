package GUI.Customer;

import App.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginCustomerPanel extends CustomerPanel {

    public LoginCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            if (app.validateEmailAndPassword(email, password, "Customer") == null) {
                JOptionPane.showMessageDialog(null, "Email or password is wrong!");
                return;
            }

            JOptionPane.showMessageDialog(null, "Login successful!");
            cardLayout.show(mainPanel, "MainMenu");
        });
    }
}

