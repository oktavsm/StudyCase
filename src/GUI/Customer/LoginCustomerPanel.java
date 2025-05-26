package GUI.Customer;

import App.Application;
import User.Customer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginCustomerPanel extends CustomerPanel {

    public LoginCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("=== Login Customer ===", SwingConstants.CENTER);
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

                Customer customer = (Customer) app.validateEmailAndPassword(email, password, "Customer");
                if (customer == null) {
                    JOptionPane.showMessageDialog(null, "Email or password is wrong!");
                    System.out.println("email: " + email);
                    System.out.println("password: " + password);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Login successful!");

                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    cardLayout.show(mainPanel, "CustomerMenu");
                }
            }
        });
    }
}
