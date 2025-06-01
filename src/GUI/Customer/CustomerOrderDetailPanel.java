package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import domain.order.*;
import domain.user.*;

public class CustomerOrderDetailPanel extends JPanel {
    public CustomerOrderDetailPanel(Application app, Order order, JLabel imageMap, Customer customer) {
        setLayout(null);
        setPreferredSize(new Dimension(854, 834));
        setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Order Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(277, 30, 300, 40);
        add(titleLabel);

        imageMap.setBounds(277, 90, 300, 226);
        add(imageMap);

        int yStart = 330;
        int yStep = 30;

        addLabel("Customer: " + order.getCustomer().getName(), 277, yStart + 0 * yStep);
        addLabel("Driver: " + order.getDriver().getName(), 277, yStart + 1 * yStep);
        addLabel("Vehicle: " + order.getDriver().getVehicle().getType(), 277, yStart + 2 * yStep);
        addLabel("Plate: " + order.getDriver().getVehicle().getPlateNumber(), 277, yStart + 3 * yStep);
        addLabel("From: " + order.getPickupLocation(), 277, yStart + 4 * yStep);
        addLabel("Destination: " + order.getDestinationLocation(), 277, yStart + 5 * yStep);
        addLabel("Distance: " + order.getDistance() + " km", 277, yStart + 6 * yStep);
        addLabel("Estimation: " + order.getEstimationTime() + " minutes", 277, yStart + 7 * yStep);
        addLabel("Rate: Rp " + order.getPayment(), 277, yStart + 8 * yStep);
        addLabel("Payment: " + (order.getPaymentStatus() ? "Done" : "Not Yet"), 277, yStart + 9 * yStep);

        String rating = order.getDriver().getRating() != 0.0
                ? String.valueOf(order.getDriver().getRating())
                : "No Review Yet";
        addLabel("Review: " + rating, 277, yStart + 10 * yStep);

        JButton chatButton = new JButton("Chat with Driver");
        chatButton.setBounds(432, yStart + 12 * yStep, 145, 40);
        styleButton(chatButton);
        add(chatButton);

        chatButton.addActionListener(e -> order.showChat(customer));
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(Color.LIGHT_GRAY);
        label.setBounds(x, y, 340, 25);
        add(label);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(100, 100, 100));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
}
