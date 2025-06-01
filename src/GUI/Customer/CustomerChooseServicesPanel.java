package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import domain.order.*;
import domain.user.*;
import infra.maps.GoogleMap;

public class CustomerChooseServicesPanel extends JPanel {
    private final Application app;
    private final Customer customer;
    private final String location;
    private final String destination;
    private final Runnable onBack;
    private JLabel labelMap = new JLabel();
    private final Runnable onOrderCreated;

    public CustomerChooseServicesPanel(Application app, Customer customer,
            String location, String destination,
            Runnable onBack, Runnable onOrderCreated) {
        this.app = app;
        this.customer = customer;
        this.location = location;
        this.destination = destination;
        this.onBack = onBack;
        this.onOrderCreated = onOrderCreated;

        setLayout(null);
        setPreferredSize(new Dimension(854, 834));
        setBackground(new Color(30, 30, 30));

        buildChooseServicePanel();
    }

    private void buildChooseServicePanel() {
        JLabel titleLabel = new JLabel("Choose Your Service", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(277, 30, 300, 40);
        add(titleLabel);

        String[] info = null;
        ImageIcon mapImage = null;

        try {
            info = GoogleMap.getRouteInfo(location, destination);
            mapImage = GoogleMap.getRouteMap(location, destination);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching route info.");
            return;
        }

        labelMap = new JLabel(mapImage);
        labelMap.setBounds(277, 90, 400, 326);
        add(labelMap);

        JLabel labelInfo = new JLabel("Distance: " + info[0] + " | Estimated Time: " + info[1]);
        labelInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelInfo.setForeground(Color.LIGHT_GRAY);
        labelInfo.setBounds(277, 366, 340, 25);
        add(labelInfo);

        double distance = Double.parseDouble(info[0].replace(" km", "").replace(",", "."));
        String time = info[1];
        double rateSepedah = 8000 * Math.max(1, distance);
        double rateMontor = 14000 * Math.max(1, distance);

        JButton btnSepedah = new JButton("Tetenger Sepedah - Rp" + (int) rateSepedah);
        btnSepedah.setBounds(277, 410, 300, 45);
        btnSepedah.setBackground(new Color(204, 102, 0));
        btnSepedah.setForeground(Color.WHITE);
        btnSepedah.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        btnSepedah.setFocusPainted(false);
        btnSepedah.setBorderPainted(false);
        add(btnSepedah);

        JButton btnMontor = new JButton("Tetenger Montor - Rp" + (int) rateMontor);
        btnMontor.setBounds(277, 470, 300, 45);
        btnMontor.setBackground(new Color(204, 102, 0));
        btnMontor.setForeground(Color.WHITE);
        btnMontor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        btnMontor.setFocusPainted(false);
        btnMontor.setBorderPainted(false);
        add(btnMontor);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(277, 530, 300, 40);
        btnBack.setBackground(new Color(80, 80, 80));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        add(btnBack);

        btnSepedah.addActionListener(
                e -> handleOrder(app, customer, destination, location, distance, time, "Motorcycle"));

        btnMontor.addActionListener(
                e -> handleOrder(app, customer, destination, location, distance, time, "Car"));

        btnBack.addActionListener(e -> {
            if (onBack != null)
                onBack.run();
        });
    }

    private void handleOrder(Application app, Customer customer,
            String destination, String location,
            double distance, String time, String type) {
        Order order = customer.newOrder(destination, location, distance, time, type);
        if (order != null) {
            order.processOrder();
            JPanel orderInfoPanel = new CustomerOrderDetailPanel(app, order, labelMap, customer);
            order.setOrderInfoPanel(orderInfoPanel);

            if (onOrderCreated != null)
                onOrderCreated.run();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create order.", "Order Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
