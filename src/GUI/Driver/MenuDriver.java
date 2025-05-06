package GUI.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import User.*;

public class menuDriver extends DriverPanel{
    public menuDriver(Application app, CardLayout cardLayout, JPanel mainPanel, Driver driver) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("=== Driver Menu ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        add(titleLabel);  // Menambahkan titleLabel ke window

        JButton btnOrderHistory = new JButton("Order History");
        JButton btnChat = new JButton("Chat with Customer");
        JButton btnReview = new JButton("Review Customer");
        JButton btnLogout = new JButton("Logout");

        add(btnOrderHistory);
        add(btnChat);
        add(btnReview);
        add(btnLogout);

        // Menambahkan aksi untuk tombol
        btnOrderHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "OrderHistoryDriver"); // Menampilkan panel order history driver
            }
        });
        
        btnChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "ChatDriver"); // Menampilkan panel chat driver
            }
        });
        
        btnReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "ReviewDriver"); // Menampilkan panel review driver
            }
        });
        
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.logout(driver); // Logout driver
                cardLayout.show(mainPanel, "MainMenu"); // Kembali ke menu utama
            }
        });
    }
}