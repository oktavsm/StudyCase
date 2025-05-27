package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import domain.order.*;

public class CustomerRatingPanel extends CustomerDashboardPanel {
    public CustomerRatingPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Order order) {
        super(app, cardLayout, mainPanel);

        JPanel customerRatingPanel = new JPanel();
        customerRatingPanel.setLayout(new BoxLayout(customerRatingPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Rate the driver:");
        customerRatingPanel.add(label);

        String[] ratings = { "★", "★★", "★★★", "★★★★", "★★★★★" };
        JComboBox<String> comboBox = new JComboBox<>(ratings);
        customerRatingPanel.add(comboBox);

        JTextField commentField = new JTextField();
        commentField.setBorder(BorderFactory.createTitledBorder("Commentar (optional)"));
        customerRatingPanel.add(commentField);

        int result = JOptionPane.showConfirmDialog(null, customerRatingPanel, "Rating Driver",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            double rating = comboBox.getSelectedIndex() + 1;
            String comment = commentField.getText().trim();
            order.setRating(rating);

            JOptionPane.showMessageDialog(null, "Thank you for your rating!");
            order.finishOrder();
            cardLayout.show(mainPanel, "CustomerMenu");
        }
    }
}
