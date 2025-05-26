package gui.Driver;

import javax.swing.*;
import app.Application;
import java.awt.*;
import java.awt.event.*;
import domain.user.*;

public class MenuDriver extends DriverPanel {
    public MenuDriver(Application app, CardLayout cardLayout, JPanel mainPanel, Driver driver) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(5, 1));
        JLabel welcomeLabel = new JLabel("Welcome, " + driver.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel balanceLabel = new JLabel("Balance: Rp. " + driver.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel ratingLabel = new JLabel("Rating: " + (driver.getRating() != 0 ? driver.getRating() : "No Review Yet"),
                SwingConstants.CENTER);
        ratingLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnOrderInfo = new JButton("Order Info");
        JButton btnLogout = new JButton("Logout");
        JButton btnProfile = new JButton("Profile");

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

                    JButton chatButton = new JButton("Chat with Customer");
                    chatButton.setBounds(100, 586, 150, 30);
                    chatButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            driver.getOrder().showChat((User) driver);
                        }
                    });
                    orderInfoPanel.add(chatButton);
                    JButton backButton = new JButton("Back");
                    backButton.setBounds(10, 586, 80, 30);
                    orderInfoPanel.add(backButton);
                    backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "DriverMenu");
                            driver.getOrder().closeChat();
                        }
                    });

                    JButton dropOffButton = new JButton("Drop Off");
                    dropOffButton.setBounds(260, 586, 80, 30);
                    orderInfoPanel.add(dropOffButton);
                    dropOffButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            driver.getOrder().dropOff();
                            JOptionPane.showMessageDialog(null, "Order Completed");
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