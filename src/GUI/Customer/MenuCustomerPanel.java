package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import User.*;


public class MenuCustomerPanel extends CustomerPanel {
    public MenuCustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel, User customer) {
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

    }
    
}
