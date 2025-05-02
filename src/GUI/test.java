package GUI;


import javax.swing.*;

import App.Application;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class test  extends JFrame {
    Application app;
    CardLayout cardLayout;
    JPanel mainPanel;

    public test(Application app) {
        this.app = app;    
        setTitle("Tetenger Dalan");
        setSize(400, 300);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBounds(0, 0, 400, 300);
        
        add(mainPanel);
        mainPanel.add(mainMenu(), "MainMenu"); // Menambahkan panel utama ke mainPanel
        mainPanel.add(registerCustomer(), "RegisterCustomer"); // Menambahkan panel registrasi customer ke mainPanel
        mainPanel.add(loginCustomer(), "LoginCustomer"); // Menambahkan panel login customer ke main
        cardLayout.show(mainPanel, "MainMenu"); // Menampilkan panel utama

    }

    public void addCard(String name, JPanel panel) {
        mainPanel.add(panel, name);
    }

    public JPanel mainMenu(){
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(5, 1)); // 5 baris, 1 kolom

        JLabel titleLabel = new JLabel("=== Online Transportation ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        mainMenuPanel.add(titleLabel);  // Menambahkan titleLabel ke window

        JButton btnRegisterCustomer = new JButton("Register as a Customer");
        JButton btnRegisterDriver = new JButton("Register as a Driver");
        JButton btnLoginCustomer = new JButton("Login as a Customer");
        JButton btnLoginDriver = new JButton("Login as a Driver");
        JButton btnExit = new JButton("Exit Application");

        mainMenuPanel.add(btnRegisterCustomer);
        mainMenuPanel.add(btnRegisterDriver);
        mainMenuPanel.add(btnLoginCustomer);
        mainMenuPanel.add(btnLoginDriver);
        mainMenuPanel.add(btnExit);

        // Menambahkan aksi untuk tombol
        btnRegisterCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegisterCustomer"); // Menampilkan panel registrasi customer
            }
        });
        btnLoginCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginCustomer"); // Menampilkan panel login customer
            }
        });

        return mainMenuPanel;
    }
    public JPanel loginCustomer(){
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2)); // 3 baris, 2 kolom

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        
        JButton loginButton = new JButton("Login");
        
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        
        loginPanel.add(loginButton);

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

        return loginPanel;
    }
    public JPanel registerCustomer()  {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(5, 2)); // 5 baris, 2 kolom

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel("Phone Number: ");
        JTextField phoneField = new JTextField();
        
        JButton registerButton = new JButton("Register");
        
        registerPanel.add(nameLabel);
        registerPanel.add(nameField);
        registerPanel.add(emailLabel);
        registerPanel.add(emailField);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordField);
        registerPanel.add(phoneLabel);
        registerPanel.add(phoneField);
        
        registerPanel.add(registerButton);

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
                if (app.validateEmail(email) == true) {
                    JOptionPane.showMessageDialog(null, "Email already exists!");
                    return;
                } else {
                    app.addCustomer(email, name, password, phoneNumber);
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    cardLayout.show(mainPanel, "MainMenu"); // Kembali ke menu utama setelah registrasi

                }


               
                
            }
        });

        return registerPanel;
    }



    
} 