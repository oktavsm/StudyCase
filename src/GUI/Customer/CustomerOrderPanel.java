package gui.Customer;

import domain.user.Customer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import app.Application;

public class CustomerOrderPanel extends CustomerPanel {
    public CustomerOrderPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("=== Order Service ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(30, 20, 300, 30);
        add(titleLabel);

        JLabel locationLabel = new JLabel("Where To Go?");
        locationLabel.setBounds(30, 60, 100, 30);
        JTextField locationField = new JTextField();
        locationField.setBounds(30, 90, 300, 30);

        JLabel yourLocationLabel = new JLabel("Your Location?");
        yourLocationLabel.setBounds(30, 130, 100, 30);
        JTextField yourLocationField = new JTextField();
        yourLocationField.setBounds(30, 160, 300, 30);

        JButton orderButton = new JButton("Order");
        orderButton.setBounds(30, 200, 150, 30);

        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(190, 200, 150, 30);

        add(locationLabel);
        add(locationField);
        add(yourLocationLabel);
        add(yourLocationField);
        add(orderButton);
        add(backButton);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jemput = yourLocationField.getText();
                String tujuan = locationField.getText();

                if (jemput.isEmpty() || tujuan.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                JPanel chooseServicesPanel = new ChooseServices(app, cardLayout, mainPanel, customer, jemput, tujuan);
                mainPanel.add(chooseServicesPanel, "ChooseServices");
                cardLayout.show(mainPanel, "ChooseServices");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(customerMenuPanel, "CustomerMenu");
                cardLayout.show(mainPanel, "CustomerMenu");
            }
        });
    }
}
