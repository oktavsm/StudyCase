package GUI.Customer;

import App.*;
import User.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProfileCustomer extends CustomerPanel {
    public ProfileCustomer(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("=== Customer Profile ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel nameLabel = new JLabel("Name: " + customer.getName());

        JLabel emailLabel = new JLabel("Email: " + customer.getEmail());

        JLabel phoneLabel = new JLabel("Phone: " + customer.getPhone());

        JButton backButton = new JButton("Back");

        titleLabel.setBounds(30, 20, 300, 30);
        nameLabel.setBounds(30, 60, 300, 30);
        emailLabel.setBounds(30, 100, 300, 30);
        phoneLabel.setBounds(30, 140, 300, 30);
        backButton.setBounds(30, 200, 150, 30);

        add(titleLabel);
        add(nameLabel);
        add(emailLabel);
        add(phoneLabel);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CustomerMenu");
            }
        });
    }
}