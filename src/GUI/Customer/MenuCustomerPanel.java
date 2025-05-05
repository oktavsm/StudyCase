package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import User.*;


public class MenuCustomerPanel extends CustomerPanel {
    public MenuCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        //menu preview
        /*
        Welcome, name      (Profile) | Logout
        -------------------------------
        |Rp.xxx.xxx       TopUp Button|
        -------------------------------
            
        Services
        (Tetenger Sepedah) (Tetenger Montor)
            
        -------------------------------------
        Order info if ordering (Open Button)
        ------------------------------------- 
         */
        setLayout(new GridLayout(5, 1)); // 5 baris, 1 kolom
        JLabel welcomeLabel = new JLabel("Welcome, " + customer.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        JLabel balanceLabel = new JLabel("Balance: Rp. " + customer.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size


        JButton btnTopUp = new JButton("Top Up Balance");
        JButton btnOrder = new JButton("Order Service");
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

        if(customer instanceof Customer) {
            System.out.println("Customer: " + customer.getName());
        } else{
            System.out.println("Bukan customer");
        }

        btnTopUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel topUpPanel = new TopUpCustomer(app, cardLayout, mainPanel, customer);
                mainPanel.add(topUpPanel, "TopUpCustomer");
                cardLayout.show(mainPanel, "TopUpCustomer"); // Menampilkan panel top up
            }
        });

        //logout
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cardLayout.show(mainPanel, "MainMenu"); // Kembali ke menu utama setelah logout
                }
            }
        });

        //info order
        btnOrderInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!customer.isOrdering()){
                    JOptionPane.showMessageDialog(null, "You are not ordering any service!");
                    return;
                }


                // Show order info panel
                // JPanel orderInfoPanel = new OrderInfoCustomer(app, cardLayout, mainPanel, customer);
                // mainPanel.add(orderInfoPanel, "OrderInfoCustomer");
                // cardLayout.show(mainPanel, "OrderInfoCustomer"); // Menampilkan panel order info
            }
        });

        //profile info
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
