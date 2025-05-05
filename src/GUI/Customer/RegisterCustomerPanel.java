package GUI.Customer;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;

public class RegisterCustomerPanel extends CustomerPanel {
    public RegisterCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(5, 2)); // 5 baris, 2 kolom

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel("Phone Number: ");
        JTextField phoneField = new JTextField();
        
        JButton registerButton = new JButton("Register");
        
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(phoneLabel);
        add(phoneField);
        
        add(registerButton);

        registerButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String phoneNumber = phoneField.getText();

                
                //validate all field filled
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }
                //validate email first
                if (app.validateEmailCustomer(email) == true) {
                    JOptionPane.showMessageDialog(null, "Email already exists!");
                    return;
                } else {
                    app.addCustomer(email, name, password, phoneNumber);
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    cardLayout.show(mainPanel, "MainMenu"); // Kembali ke menu utama setelah registrasi

                }


               
                
            }
        });
    }
}
