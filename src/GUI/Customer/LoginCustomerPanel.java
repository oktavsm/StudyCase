package GUI.Customer;

import App.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import User.Customer;
import User.User;

public class LoginCustomerPanel extends CustomerPanel {

    public LoginCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(3, 2)); // 3 baris, 2 kolom
        // set width and height 150, 30
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
                Customer customer = (Customer) app.validateEmailAndPassword(email,password, "Customer");
                if (customer == null) {
                    JOptionPane.showMessageDialog(null, "Email or password is wrong!");
                    //debug email and password
                    System.out.println("email: "+email);
                    System.out.println("password: "+password);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Show customer menu panel
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel,  customer);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");

                    cardLayout.show(mainPanel, "CustomerMenu");
                    
                } 


               
                
            }
        });
    }
}

