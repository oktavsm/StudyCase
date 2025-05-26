package gui.Customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import java.awt.event.*;
import domain.user.*;

public class MenuCustomerPanel extends CustomerPanel {
    public MenuCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(5, 1));
        JLabel welcomeLabel = new JLabel("Welcome, " + customer.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel balanceLabel = new JLabel("Balance: Rp. " + customer.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnTopUp = new JButton("Top Up Balance");
        JButton btnOrder = new JButton("Tetenger Services");
        JButton btnOrderInfo = new JButton("Order Info");
        JButton btnLogout = new JButton("Logout");
        JButton btnProfile = new JButton("Profile");

        add(welcomeLabel);
        add(balanceLabel);
        add(btnTopUp);
        add(btnOrder);
        add(btnOrderInfo);
        add(btnProfile);
        add(btnLogout);

        if (customer.isOrdering() && customer.getOrder().isDrop()) {
            JPanel ratePanel = new RatingUtil(app, cardLayout, mainPanel, customer.getOrder());
            mainPanel.remove(ratePanel);
            mainPanel.add(ratePanel, "RatingUtil");
            cardLayout.show(mainPanel, "RatingUtil");

        }
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel orderPanel = new CustomerOrderPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(orderPanel, "CustomerOrderPanel");
                cardLayout.show(mainPanel, "CustomerOrderPanel");
            }
        });

        btnTopUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel topUpPanel = new TopUpCustomer(app, cardLayout, mainPanel, customer);
                mainPanel.add(topUpPanel, "TopUpCustomer");
                cardLayout.show(mainPanel, "TopUpCustomer");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cardLayout.show(mainPanel, "MainMenu");
                }
            }
        });

        btnOrderInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!customer.isOrdering()) {
                    JOptionPane.showMessageDialog(null, "You are not ordering any service!");
                    return;
                }

                JPanel orderInfoPanel = customer.getOrder().getOrderInfoPanel();
                for (Component comp : orderInfoPanel.getComponents()) {
                    if (comp instanceof JButton) {
                        JButton button = (JButton) comp;
                        if (button.getText().equals("Chat with Customer")) {
                            orderInfoPanel.remove(button);
                        }
                        if (button.getText().equals("Back")) {
                            orderInfoPanel.remove(button);
                        }
                        if (button.getText().equals("Drop Off")) {
                            orderInfoPanel.remove(button);
                        }
                    }
                }

                JButton chatButton = new JButton("Chat with Driver");
                chatButton.setBounds(120, 586, 150, 30);
                chatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        customer.getOrder().showChat((User) customer);
                    }
                });
                orderInfoPanel.add(chatButton);
                JButton backButton = new JButton("Back");
                backButton.setBounds(10, 586, 100, 30);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        customer.getOrder().closeChat();
                        cardLayout.show(mainPanel, "CustomerMenu");
                    }
                });
                orderInfoPanel.add(backButton);
                orderInfoPanel.revalidate();
                orderInfoPanel.repaint();

                customer.getOrder().setOrderInfoPanel(orderInfoPanel);
                customer.getOrder().initPanel(app, cardLayout, mainPanel);
            }
        });

        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel profilePanel = new ProfileCustomer(app, cardLayout, mainPanel, customer);
                mainPanel.add(profilePanel, "ProfileCustomer");
                cardLayout.show(mainPanel, "ProfileCustomer");
            }
        });
    }

}
