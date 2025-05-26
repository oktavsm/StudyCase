package gui.Driver;

import domain.user.Driver;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import app.Application;

public class LoginDriverPanel extends DriverPanel {
    public LoginDriverPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("=== Login Driver ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(30, 20, 300, 30);
        add(titleLabel);

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        emailLabel.setBounds(30, 60, 100, 30);
        emailField.setBounds(30, 90, 300, 30);

        passwordLabel.setBounds(30, 130, 100, 30);
        passwordField.setBounds(30, 160, 300, 30);

        loginButton.setBounds(30, 210, 150, 30);

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                Driver driver = (Driver) app.validateEmailAndPassword(email, password, "Driver");
                if (driver != null) {
                    JOptionPane.showMessageDialog(null, "Login successful!");

                    JPanel menuDriver = new MenuDriver(app, cardLayout, mainPanel, driver);
                    mainPanel.add(menuDriver, "DriverMenu");
                    cardLayout.show(mainPanel, "DriverMenu");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password!");
                }
            }
        });
    }
}
