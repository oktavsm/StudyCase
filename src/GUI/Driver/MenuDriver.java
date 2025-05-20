package GUI.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import GUI.ChatUI;
import User.*;

public class MenuDriver extends DriverPanel {
    public MenuDriver(Application app, CardLayout cardLayout, JPanel mainPanel, Driver driver) {
        super(app, cardLayout, mainPanel);
        // menu preview
        /*
         * Welcome, name (Profile) | Logout
         * -------------------------------
         * |Rp.xxx.xxx Rating : |
         * -------------------------------
         * 
         * Vehicle Information field
         * 
         * 
         * 
         * 
         * -------------------------------------
         * Incoming Order info if have order (Open Button)
         * -------------------------------------
         */

        setLayout(new GridLayout(5, 1)); // 5 baris, 1 kolom
        JLabel welcomeLabel = new JLabel("Welcome, " + driver.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        JLabel balanceLabel = new JLabel("Balance: Rp. " + driver.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        // rating
        JLabel ratingLabel = new JLabel("Rating: " + (driver.getRating() != 0 ? driver.getRating() : "No Review Yet"),
                SwingConstants.CENTER);
        ratingLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size

        // tombol Service Tetenger Sepedah

        // Tombol Service Tetenger Montor

        JButton btnOrderInfo = new JButton("Order Info");
        JButton btnLogout = new JButton("Logout");
        JButton btnProfile = new JButton("Profile");

        // listener
        btnOrderInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (driver.isHaveOrder()) {
                    JPanel orderInfoPanel = driver.getOrder().getOrderInfoPanel();
                    for (Component comp : orderInfoPanel.getComponents()) {
                        if (comp instanceof JButton) {
                            JButton button = (JButton) comp;
                            if (button.getText().equals("Chat with Driver")) {
                                orderInfoPanel.remove(button);
                            }
                            if (button.getText().equals("Back")) {
                                orderInfoPanel.remove(button);
                            }
                        }
                    }

                    // Tambahkan tombol baru
                    JButton chatButton = new JButton("Chat with Customer");
                    chatButton.setBounds(120, 640, 150, 30); // Set positi
                    chatButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // open chat window
                            
                            driver.getOrder().showChat((User) driver);
                            
                        }
                    });
                    orderInfoPanel.add(chatButton);
                    JButton backButton = new JButton("Back");
                    backButton.setBounds(10, 640, 100, 30); // Set position and size
                    orderInfoPanel.add(backButton);
                    backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "DriverMenu");
                            driver.getOrder().closeChat();
                        }
                    });

                    //driver Drop off Button
                    JButton dropOffButton = new JButton("Drop Off");
                    dropOffButton.setBounds(10, 680, 100, 30); // Set position and size
                    orderInfoPanel.add(dropOffButton);
                    dropOffButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            driver.getOrder().dropOff();
                            JOptionPane.showMessageDialog(null, "Order Completed");
                            //refresh main menu
                            JPanel driverMenuPanel = new MenuDriver(app, cardLayout, mainPanel, driver);
                            mainPanel.remove(driverMenuPanel);
                            mainPanel.add(driverMenuPanel, "DriverMenu");
                            cardLayout.show(mainPanel, "DriverMenu");
                            driver.getOrder().closeChat();
                        }
                    });
                    orderInfoPanel.revalidate();
                    orderInfoPanel.repaint();
                    driver.getOrder().setOrderInfoPanel(orderInfoPanel);
                    driver.getOrder().initPanel(app, cardLayout, mainPanel);
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have any order yet");
                }

            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logout
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        add(welcomeLabel);
        add(balanceLabel);
        add(ratingLabel);

        add(btnOrderInfo);
        add(btnProfile);
        add(btnLogout);
    }
}