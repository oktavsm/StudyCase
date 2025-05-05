package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import Service.GoogleMapService;
import User.*;

public class CustomerOrderPanel extends CustomerPanel {
    public CustomerOrderPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        // choice : 1. Sepedah (Bike) 2. Montor (Car)
        //preview layout
        /*
         Where To Go? : ....input destination


         Your Location? : ....input customer location
         Order Button
         */
        setLayout(new GridLayout(4, 2)); // 4 baris, 2 kolom
        JLabel titleLabel = new JLabel("Order Service", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size

        JLabel locationLabel = new JLabel("Where To Go? : ");
        JTextField locationField = new JTextField();
        JLabel yourLocationLabel = new JLabel("Your Location? : ");
        JTextField yourLocationField = new JTextField();
        JButton orderButton = new JButton("Order");
        JButton backButton = new JButton("Back to Menu");
        add(titleLabel);
        add(new JLabel()); // empty label for spacing
        add(locationLabel);
        add(locationField);
        add(yourLocationLabel);
        add(yourLocationField);
        add(orderButton);
        add(backButton);
        // Action listener for order button
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //choose destination
                String jemput = yourLocationField.getText();
        String tujuan = locationField.getText();

                //validate all field filled
                if (jemput.isEmpty() || tujuan.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }
               
                // Show choose services panel
                JPanel chooseServicesPanel = new ChooseServices(app, cardLayout, mainPanel, customer, jemput, tujuan);
                mainPanel.add(chooseServicesPanel, "ChooseServices");
                cardLayout.show(mainPanel, "ChooseServices"); // Menampilkan panel order
        
        
            
            }
        });

        // Action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show customer menu panel
                JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(customerMenuPanel, "CustomerMenu");
                cardLayout.show(mainPanel, "CustomerMenu");
            }
        });




    }

}
