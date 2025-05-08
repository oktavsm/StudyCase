package GUI.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import User.*;

public class MenuDriver extends DriverPanel{
    public MenuDriver(Application app, CardLayout cardLayout, JPanel mainPanel, Driver driver) {
        super(app, cardLayout, mainPanel);
        //menu preview
        /*
        Welcome, name      (Profile) | Logout
        -------------------------------
        |Rp.xxx.xxx       Rating :    |
        -------------------------------
        
        Vehicle Information field


        
        
        -------------------------------------
        Incoming Order info if have order (Open Button)
        ------------------------------------- 
         */

         setLayout(new GridLayout(5, 1)); // 5 baris, 1 kolom
        JLabel welcomeLabel = new JLabel("Welcome, " + driver.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        JLabel balanceLabel = new JLabel("Balance: Rp. " + driver.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        //rating
        JLabel ratingLabel = new JLabel("Rating: " + (driver.getRating()!=0?driver.getRating():"No Review Yet"), SwingConstants.CENTER);
        ratingLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size




        // tombol Service Tetenger Sepedah

        //Tombol Service Tetenger Montor
       
        
        JButton btnOrderInfo = new JButton("Order Info");
        JButton btnLogout = new JButton("Logout");
        JButton btnProfile = new JButton("Profile");

        add(welcomeLabel);
        add(balanceLabel);
        add(ratingLabel);
        
      
        add(btnOrderInfo);
        add(btnProfile);
        add(btnLogout);
    }
}