package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import java.awt.event.*;
import domain.order.*;
import domain.user.*;

public class CustomerOrderDetail extends CustomerDashboardPanel {
    public CustomerOrderDetail(Application app, CardLayout cardLayout, JPanel mainPanel, Order order, JLabel imageMap,
            Customer customer) {
        super(app, cardLayout, mainPanel);
        setLayout(null);
        JLabel titleLabel = new JLabel("Order Info", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(0, 10, 340, 30);
        JButton backButton = new JButton("Back");
        imageMap.setBounds(10, 30, 340, 226);

        JLabel customerLabel = new JLabel("Customer: " + order.getCustomer().getName());
        customerLabel.setBounds(10, 256, 250, 20);
        JLabel driverLabel = new JLabel("Driver: " + order.getDriver().getName());
        driverLabel.setBounds(10, 286, 250, 20);
        JLabel vehicleLabel = new JLabel("Vehicle: " + order.getDriver().getVehicle().getType() + " "
                + order.getDriver().getVehicle().getPlateNumber());
        vehicleLabel.setBounds(10, 316, 250, 20);
        JLabel plateLabel = new JLabel("Plate: " + order.getDriver().getVehicle().getPlateNumber());
        plateLabel.setBounds(10, 346, 250, 20);
        JLabel fromLabel = new JLabel("From: " + order.getPickupLocation());
        fromLabel.setBounds(10, 376, 250, 20);
        JLabel destinationLabel = new JLabel("Destination: " + order.getDestinationLocation());
        destinationLabel.setBounds(10, 406, 250, 20);
        JLabel distanceLabel = new JLabel("Distance: " + order.getDistance() + " km");
        distanceLabel.setBounds(10, 436, 250, 20);
        JLabel estimationLabel = new JLabel("Estimation: " + order.getEstimationTime() + " minutes");
        estimationLabel.setBounds(10, 466, 250, 20);
        JLabel rateLabel = new JLabel("Rate: Rp. " + order.getPayment());
        rateLabel.setBounds(10, 496, 250, 20);
        JLabel paymentLabel = new JLabel("Payment: " + (order.getPaymentStatus() ? "Done" : "Not Yet"));
        paymentLabel.setBounds(10, 526, 250, 20);

        JLabel reviewLabel = new JLabel(
                "Review: " + (order.getDriver().getRating() != 0.0 ? order.getDriver().getRating() : "No Review Yet"));
        reviewLabel.setBounds(10, 556, 250, 20);

        backButton.setBounds(10, 586, 100, 30);

        add(customerLabel);
        add(driverLabel);
        add(vehicleLabel);
        add(plateLabel);
        add(fromLabel);
        add(destinationLabel);
        add(distanceLabel);
        add(estimationLabel);
        add(rateLabel);
        add(paymentLabel);
        add(reviewLabel);
        add(backButton);
        add(titleLabel);
        add(imageMap);

        JButton chatButton = new JButton("Chat with Driver");
        chatButton.setBounds(120, 586, 150, 30);
        add(chatButton);
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.showChat((User) customer);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CustomerMenu");
                customer.getOrder().closeChat();
            }
        });
    }
}
