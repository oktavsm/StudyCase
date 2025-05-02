package GUI.Driver;


import App.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginDriverPanel extends DriverPanel {
    public LoginDriverPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(4, 2)); // 4 baris, 2 kolom

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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Validate all fields filled
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                // Validate email and password
                if (app.validateEmailAndPassword(email, password,"Driver")!= null) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Show driver menu panel
                    // JPanel driverMenuPanel = new DriverMenuPanel(app, cardLayout, mainPanel);
                    // mainPanel.add(driverMenuPanel, "DriverMenu");
                    // cardLayout.show(mainPanel, "DriverMenu");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password!");
                }
            }
        });
}
}