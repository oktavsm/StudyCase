package GUI.Customer;


import App.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import User.*;
public class ProfileCustomer extends CustomerPanel {
    
    public ProfileCustomer(Application app, CardLayout cardLayout, JPanel mainPanel,Customer customer){
        super(app,  cardLayout, mainPanel);
        setLayout(new GridLayout(3, 2)); // 3 baris, 2 kolom

        JLabel nameLabel = new JLabel("Name: " + customer.getName());
        JLabel emailLabel = new JLabel("Email: " + customer.getEmail());
        JLabel phoneLabel = new JLabel("Phone: " + customer.getPhone());

        JButton backButton = new JButton("Back");

        add(nameLabel);
        add(emailLabel);
        add(phoneLabel);
        
        add(backButton);

        backButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CustomerMenu");
            } 
        });

        

    }
}
