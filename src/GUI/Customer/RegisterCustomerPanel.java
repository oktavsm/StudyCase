package gui.Customer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import app.Application;

public class RegisterCustomerPanel extends CustomerPanel {
    public RegisterCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("=== Customer Register ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(30, 20, 300, 30);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel("Phone Number: ");
        JTextField phoneField = new JTextField();
        JButton registerButton = new JButton("Register");

        nameLabel.setBounds(30, 60, 100, 30);
        nameField.setBounds(30, 90, 300, 30);

        emailLabel.setBounds(30, 130, 100, 30);
        emailField.setBounds(30, 160, 300, 30);

        passwordLabel.setBounds(30, 200, 100, 30);
        passwordField.setBounds(30, 230, 300, 30);

        phoneLabel.setBounds(30, 270, 100, 30);
        phoneField.setBounds(30, 300, 300, 30);

        registerButton.setBounds(30, 350, 150, 30);

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(phoneLabel);
        add(phoneField);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String phoneNumber = phoneField.getText();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                if (app.validateEmailCustomer(email)) {
                    JOptionPane.showMessageDialog(null, "Email already exists!");
                    return;
                } else {
                    app.addCustomer(email, name, password, phoneNumber);
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    cardLayout.show(mainPanel, "MainMenu");
                }
            }
        });
    }
}
