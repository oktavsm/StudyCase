package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import domain.order.*;
import domain.user.*;

public class CustomerOrderDetailPanel extends JPanel {
    private final Application app;
    private final Order order;
    private final JLabel imageMap;
    private final Customer customer;

    // Define constants for layout to make adjustments easier
    private static final int PANEL_WIDTH = 854;
    private static final int TITLE_Y = 30;
    private static final int TITLE_HEIGHT = 40;
    private static final int IMAGE_Y = 90;
    private static final int IMAGE_HEIGHT = 326;
    private static final int IMAGE_WIDTH = 400;

    private static final int DETAILS_START_Y = IMAGE_Y + IMAGE_HEIGHT + 14; // Start details below image
    private static final int DETAILS_ROW_HEIGHT = 25; // Height of a single label row
    private static final int DETAILS_ROW_SPACING = 10; // Vertical spacing between rows of details
    private static final int DETAILS_Y_STEP = DETAILS_ROW_HEIGHT + DETAILS_ROW_SPACING; // Total step for next row

    private static final int DETAILS_BLOCK_WIDTH = 600; // Width of the entire details block (two columns)
    private static final int DETAILS_COLUMN_SPACING = 20; // Horizontal spacing between the two columns
    private static final int DETAILS_COLUMN_WIDTH = (DETAILS_BLOCK_WIDTH - DETAILS_COLUMN_SPACING) / 2;
    private static final int DETAILS_BLOCK_START_X = (PANEL_WIDTH - DETAILS_BLOCK_WIDTH) / 2;
    private static final int DETAILS_COLUMN_1_X = DETAILS_BLOCK_START_X;
    private static final int DETAILS_COLUMN_2_X = DETAILS_BLOCK_START_X + DETAILS_COLUMN_WIDTH + DETAILS_COLUMN_SPACING;

    private static final int CHAT_BUTTON_WIDTH = 145;
    private static final int CHAT_BUTTON_HEIGHT = 40;
    private static final int CHAT_BUTTON_TOP_MARGIN = 20; // Margin above the chat button

    public CustomerOrderDetailPanel(Application app, Order order, JLabel imageMap, Customer customer) {
        this.app = app;
        this.order = order;
        this.imageMap = imageMap;
        this.customer = customer;

        setLayout(null);
        setPreferredSize(new Dimension(PANEL_WIDTH, 834)); // Keep preferred size
        setBackground(new Color(30, 30, 30)); // Keep background color

        buildOrderDetail();
        buildChatButton();
    }

    public void refreshOrderDetail() {
        // Clear existing components
        removeAll();
        revalidate();
        repaint();

        // Rebuild the order detail panel
        buildOrderDetail();
        buildChatButton();
    }
    private void buildOrderDetail() {
        // Title Label
        JLabel titleLabel = new JLabel("Order Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds((PANEL_WIDTH - 300) / 2, TITLE_Y, 300, TITLE_HEIGHT);
        add(titleLabel);

        // Image Map
        imageMap.setBounds((PANEL_WIDTH - IMAGE_WIDTH) / 2, IMAGE_Y, IMAGE_WIDTH, IMAGE_HEIGHT);
        add(imageMap);

        // Order Details - Two Column Layout
        int currentY = DETAILS_START_Y;

        // Row 1
        addLabel("Customer: " + order.getCustomer().getName(), DETAILS_COLUMN_1_X+50, currentY, DETAILS_COLUMN_WIDTH);
        if (order.getDriver() != null) { // Check if driver exists
            addLabel("Driver: " + order.getDriver().getName(), DETAILS_COLUMN_2_X+50, currentY, DETAILS_COLUMN_WIDTH);
        } else {
            addLabel("Driver: Not Assigned", DETAILS_COLUMN_2_X+50, currentY, DETAILS_COLUMN_WIDTH);
        }
        currentY += DETAILS_Y_STEP;

        // Row 2
        if (order.getDriver() != null && order.getDriver().getVehicle() != null) { // Check for vehicle
            addLabel("Vehicle: " + order.getDriver().getVehicle().getType(), DETAILS_COLUMN_1_X+50, currentY, DETAILS_COLUMN_WIDTH);
            addLabel("Plate: " + order.getDriver().getVehicle().getPlateNumber(), DETAILS_COLUMN_2_X+50, currentY, DETAILS_COLUMN_WIDTH);
        } else {
            addLabel("Vehicle: N/A", DETAILS_COLUMN_1_X+50, currentY, DETAILS_COLUMN_WIDTH);
            addLabel("Plate: N/A", DETAILS_COLUMN_2_X+50, currentY, DETAILS_COLUMN_WIDTH);
        }
        currentY += DETAILS_Y_STEP;

        // Row 3
        addLabel("From: " + order.getPickupLocation(), DETAILS_COLUMN_1_X+50, currentY, DETAILS_COLUMN_WIDTH);
        addLabel("Destination: " + order.getDestinationLocation(), DETAILS_COLUMN_2_X+50, currentY, DETAILS_COLUMN_WIDTH);
        currentY += DETAILS_Y_STEP;

        // Row 4
        addLabel("Distance: " + order.getDistance() + " km", DETAILS_COLUMN_1_X+50, currentY, DETAILS_COLUMN_WIDTH);
        addLabel("Estimation: " + order.getEstimationTime() + " minutes", DETAILS_COLUMN_2_X+50, currentY, DETAILS_COLUMN_WIDTH);
        currentY += DETAILS_Y_STEP;

        // Row 5
        addLabel("Rate: Rp " + order.getPayment(), DETAILS_COLUMN_1_X+50, currentY, DETAILS_COLUMN_WIDTH);
        addLabel("Payment: " + (order.getPaymentStatus() ? "Done" : "Not Yet"), DETAILS_COLUMN_2_X+50, currentY, DETAILS_COLUMN_WIDTH);
        currentY += DETAILS_Y_STEP;
        
        // Row 6 - Review (Spans full width of the details block and is centered)
        String rating = "No Review Yet";
        if (order.getDriver() != null && order.getDriver().getRating() != 0.0) {
            rating = String.valueOf(order.getDriver().getRating());
        }
        addLabel("Review: " + rating, DETAILS_BLOCK_START_X, currentY, DETAILS_BLOCK_WIDTH, SwingConstants.CENTER);
    }

    private void buildChatButton() {
        // Calculate Y position for the chat button based on the last detail item
        // Number of paired rows = 5, plus one row for the centered review
        int lastDetailY = DETAILS_START_Y + (5 * DETAILS_Y_STEP) + DETAILS_ROW_HEIGHT; // End of the review label
        int chatButtonY = lastDetailY + CHAT_BUTTON_TOP_MARGIN;

        JButton chatButton = new JButton("Chat with Driver");
        chatButton.setBounds((PANEL_WIDTH - CHAT_BUTTON_WIDTH) / 2, chatButtonY, CHAT_BUTTON_WIDTH, CHAT_BUTTON_HEIGHT); // Centered
        chatButton.setBackground(new Color(100, 100, 100));
        chatButton.setForeground(Color.WHITE);
        chatButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        chatButton.setFocusPainted(false);
        chatButton.setBorderPainted(false);
        add(chatButton);

        chatButton.addActionListener(e -> {
            if (order.getDriver() != null) { // Ensure driver exists before trying to chat
                order.showChat(customer);
            } else {
                JOptionPane.showMessageDialog(this, "No driver assigned to this order yet.", "Chat Unavailable", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // Helper method to add labels, now with width and optional horizontal alignment
    private void addLabel(String text, int x, int y, int width) {
        addLabel(text, x, y, width, SwingConstants.LEFT); // Default to LEFT alignment
    }

    private void addLabel(String text, int x, int y, int width, int horizontalAlignment) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.LIGHT_GRAY);
        label.setBounds(x, y, width, DETAILS_ROW_HEIGHT);
        label.setHorizontalAlignment(horizontalAlignment);
        add(label);
    }
}
