package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import GUI.ChatUI;
import User.*;

public class MenuCustomerPanel extends CustomerPanel {
    public MenuCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        // menu preview
        /*
         * Welcome, name (Profile) | Logout
         * -------------------------------
         * |Rp.xxx.xxx TopUp Button|
         * -------------------------------
         * 
         * Services
         * (Tetenger Sepedah) (Tetenger Montor)
         * 
         * -------------------------------------
         * Order info if ordering (Open Button)
         * -------------------------------------
         */
        setLayout(new GridLayout(5, 1)); // 5 baris, 1 kolom
        JLabel welcomeLabel = new JLabel("Welcome, " + customer.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        JLabel balanceLabel = new JLabel("Balance: Rp. " + customer.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size

        // tombol Service Tetenger Sepedah

        // Tombol Service Tetenger Montor
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

        if(customer.isOrdering()&&customer.getOrder().isDrop()){
                    //rate panel
                    JPanel ratePanel = new RatingUtil(app, cardLayout, mainPanel, customer.getOrder());
                    mainPanel.remove(ratePanel);
                    mainPanel.add(ratePanel, "RatingUtil");
                    cardLayout.show(mainPanel, "RatingUtil");
                    
                }
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show order panel for Sepedah
                JPanel orderPanel = new CustomerOrderPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(orderPanel, "CustomerOrderPanel");
                cardLayout.show(mainPanel, "CustomerOrderPanel"); // Menampilkan panel order
            }
        });

        btnTopUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel topUpPanel = new TopUpCustomer(app, cardLayout, mainPanel, customer);
                mainPanel.add(topUpPanel, "TopUpCustomer");
                cardLayout.show(mainPanel, "TopUpCustomer"); // Menampilkan panel top up
            }
        });

        // logout
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cardLayout.show(mainPanel, "MainMenu"); // Kembali ke menu utama setelah logout
                }
            }
        });

        // info order
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

                // Tambahkan tombol baru
                JButton chatButton = new JButton("Chat with Driver");
                chatButton.setBounds(120, 640, 150, 30); // Set position and size
                chatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // open chat window
                        
                        customer.getOrder().showChat((User) customer);
                    }
                });
                orderInfoPanel.add(chatButton);
                JButton backButton = new JButton("Back");
                backButton.setBounds(10, 640, 100, 30); // Set position and size
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

        // profile info
        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show profile panel
                JPanel profilePanel = new ProfileCustomer(app, cardLayout, mainPanel, customer);
                mainPanel.add(profilePanel, "ProfileCustomer");
                cardLayout.show(mainPanel, "ProfileCustomer"); // Menampilkan panel profile
            }
        });
    }

}
