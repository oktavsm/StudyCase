package gui.Customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import java.awt.event.*;
import domain.order.*;
import domain.user.*;
import infra.maps.GoogleMap;

public class ChooseServices extends CustomerPanel {
    String info[] = null;
    ImageIcon mapImage = null;
    JLabel labelMap = new JLabel();

    public ChooseServices(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer, String location,
            String destination) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("Choose Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(0, 10, 400, 30);

        try {
            info = GoogleMap.getRouteInfo(location, destination);
            mapImage = GoogleMap.getRouteMap(location, destination);

            labelMap = new JLabel(mapImage);
            labelMap.setBounds(10, 30, 340, 266);
            add(labelMap);
            JLabel labelInfo = new JLabel("Distance: " + info[0] + " | Estimated Time: " + info[1]);
            labelInfo.setBounds(10, 306, 340, 30);
            add(labelInfo);
        } catch (Exception exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching route information. Please try again later.");
        }
        String time = info[1];
        String distanceS = info[0].replace(" km", "").replace(",", ".");
        double distance = Double.parseDouble(distanceS);
        double rateSepedah = 8000 * Math.max(1, distance);
        double rateMontor = 14000 * Math.max(1, distance);

        String labelButtonSepedah = "Tetenger Sepedah Rp" + rateSepedah;

        JButton btnSepedah = new JButton(labelButtonSepedah);
        btnSepedah.setBounds(10, 346, 340, 50);
        String labelButtonMontor = "Tetenger Montor Rp" + rateMontor;
        JButton btnMontor = new JButton(labelButtonMontor);
        btnMontor.setBounds(10, 406, 340, 50);
        JButton btnBack = new JButton("Back to Menu");
        btnBack.setBounds(10, 466, 340, 50);
        add(btnSepedah);
        add(btnMontor);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(customerMenuPanel, "CustomerMenu");
                cardLayout.show(mainPanel, "CustomerMenu");
            }
        });
        btnSepedah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = customer.newOrder(destination, location, distance, time, "Motorcycle");
                if (order != null) {
                    order.processOrder();
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.remove(customerMenuPanel);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    JPanel orderInfo = new OrderInfo(app, cardLayout, mainPanel, order, labelMap, customer);
                    order.setOrderInfoPanel(orderInfo);
                    order.initPanel(app, cardLayout, mainPanel);
                } else {
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    cardLayout.show(mainPanel, "CustomerMenu");
                }
            }
        });
        btnMontor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = customer.newOrder(destination, location, distance, time, "Car");
                if (order != null) {
                    order.processOrder();
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.remove(customerMenuPanel);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    JPanel orderInfo = new OrderInfo(app, cardLayout, mainPanel, order, labelMap, customer);
                    order.setOrderInfoPanel(orderInfo);
                    order.initPanel(app, cardLayout, mainPanel);
                } else {
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    cardLayout.show(mainPanel, "CustomerMenu");

                }
            }
        });
    }

}
