package gui.customer;

import domain.user.Customer;
import java.awt.*;
import javax.swing.*;
import app.Application;

public class CustomerOrderPanel extends JPanel {
    private final Application app;
    private final Customer customer;
    private final Runnable onOrderCreated;
    private JPanel orderPanel;
    private JPanel currentPanel;

    public CustomerOrderPanel(Application app, Customer customer, Runnable onOrderCreated) {
        this.app = app;
        this.customer = customer;
        this.onOrderCreated = onOrderCreated;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(854, 834));
        setBackground(new Color(30, 30, 30));

        buildorderPanel();
        add(orderPanel, BorderLayout.CENTER);
        currentPanel = orderPanel;
    }

    private void buildorderPanel() {
        orderPanel = new JPanel(null);
        orderPanel.setPreferredSize(new Dimension(854, 834));
        orderPanel.setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Tetenger Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(277, 30, 300, 40);
        orderPanel.add(titleLabel);

        JLabel destinationLabel = new JLabel("Where To Go?");
        destinationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        destinationLabel.setForeground(Color.WHITE);
        destinationLabel.setBounds(277, 100, 300, 25);
        orderPanel.add(destinationLabel);

        JTextField destinationField = new JTextField();
        destinationField.setBounds(277, 130, 300, 35);
        destinationField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        destinationField.setBackground(new Color(50, 50, 50));
        destinationField.setForeground(Color.WHITE);
        destinationField.setCaretColor(Color.WHITE);
        destinationField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        orderPanel.add(destinationField);

        JLabel locationLabel = new JLabel("Your Location?");
        locationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        locationLabel.setForeground(Color.WHITE);
        locationLabel.setBounds(277, 180, 300, 25);
        orderPanel.add(locationLabel);

        JTextField locationField = new JTextField();
        locationField.setBounds(277, 210, 300, 35);
        locationField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        locationField.setBackground(new Color(50, 50, 50));
        locationField.setForeground(Color.WHITE);
        locationField.setCaretColor(Color.WHITE);
        locationField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        orderPanel.add(locationField);

        JButton orderButton = new JButton("Order Now");
        orderButton.setBounds(277, 270, 300, 40);
        orderButton.setBackground(new Color(204, 102, 0));
        orderButton.setForeground(Color.WHITE);
        orderButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        orderButton.setFocusPainted(false);
        orderButton.setBorderPainted(false);
        orderPanel.add(orderButton);

        orderButton.addActionListener(e -> {
            String pickup = locationField.getText().trim();
            String destination = destinationField.getText().trim();

            if (pickup.isEmpty() || destination.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            showChooseServicesPanel(pickup, destination);
        });
    }

    private void showChooseServicesPanel(String pickup, String destination) {
        remove(currentPanel);
        JPanel chooseServicesPanel = new CustomerChooseServicesPanel(app, customer, pickup, destination, this::goBack,
                onOrderCreated);
        add(chooseServicesPanel, BorderLayout.CENTER);
        currentPanel = chooseServicesPanel;
        revalidate();
        repaint();
    }

    public void goBack() {
        remove(currentPanel);
        add(orderPanel, BorderLayout.CENTER);
        currentPanel = orderPanel;
        revalidate();
        repaint();
    }
}
