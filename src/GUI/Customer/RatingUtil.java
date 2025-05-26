package gui.Customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import domain.order.*;

public class RatingUtil extends CustomerPanel {
    public RatingUtil(Application app, CardLayout cardLayout, JPanel mainPanel, Order order) {
        super(app, cardLayout, mainPanel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Rate the driver:");
        panel.add(label);

        String[] ratings = { "★", "★★", "★★★", "★★★★", "★★★★★" };
        JComboBox<String> comboBox = new JComboBox<>(ratings);
        panel.add(comboBox);

        JTextField commentField = new JTextField();
        commentField.setBorder(BorderFactory.createTitledBorder("Commentar (optional)"));
        panel.add(commentField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Rating Driver",
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
