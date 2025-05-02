package GUI.Customer;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;

public class RegisterCustomerPanel extends CustomerPanel {
    public RegisterCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel("Phone Number: ");
        JTextField phoneField = new JTextField();
        JButton registerButton = new JButton("Register");

        add(nameLabel); add(nameField);
        add(emailLabel); add(emailField);
        add(passwordLabel); add(passwordField);
        add(phoneLabel); add(phoneField);
        add(registerButton);

        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String phoneNumber = phoneField.getText();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }
            if (app.validateEmail(email)) {
                JOptionPane.showMessageDialog(null, "Email already exists!");
                return;
            }

            app.addCustomer(email, name, password, phoneNumber);
            JOptionPane.showMessageDialog(null, "Registration successful!");
            cardLayout.show(mainPanel, "MainMenu"); // Go back to main menu after registration
        });
    }
}
