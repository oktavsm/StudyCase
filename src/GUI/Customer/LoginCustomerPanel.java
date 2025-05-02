package GUI.Customer;

import App.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginCustomerPanel extends CustomerPanel {

    public LoginCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(3, 2)); // 3 baris, 2 kolom

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

        loginButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                //validate all field filled
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }
                //validate email and password
                if (app.validateEmailAndPassword(email,password, "Customer") == null) {
                    JOptionPane.showMessageDialog(null, "Email or password is wrong!");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    cardLayout.show(mainPanel, "MainMenu"); // Kembali ke menu utama setelah login
                    
                } 


               
                
            }
        });
    }
}

