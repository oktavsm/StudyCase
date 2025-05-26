package gui;

import javax.swing.*;
import app.Application;
import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(null);

        JLabel titleLabel = new JLabel("=== Tetenger Dalan ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(30, 20, 300, 30);
        add(titleLabel);

        JButton btnRegisterCustomer = new JButton("Register as a Customer");
        JButton btnRegisterDriver = new JButton("Register as a Driver");
        JButton btnLoginCustomer = new JButton("Login as a Customer");
        JButton btnLoginDriver = new JButton("Login as a Driver");
        JButton btnExit = new JButton("Exit Application");

        btnRegisterCustomer.setBounds(80, 70, 200, 30);
        btnRegisterDriver.setBounds(80, 110, 200, 30);
        btnLoginCustomer.setBounds(80, 150, 200, 30);
        btnLoginDriver.setBounds(80, 190, 200, 30);
        btnExit.setBounds(80, 230, 200, 30);

        add(btnRegisterCustomer);
        add(btnRegisterDriver);
        add(btnLoginCustomer);
        add(btnLoginDriver);
        add(btnExit);

        btnRegisterCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegisterCustomer");
            }
        });
        btnLoginCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginCustomer");
            }
        });
        btnRegisterDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegisterDriver");
            }
        });
        btnLoginDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginDriver");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
