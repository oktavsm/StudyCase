package gui.driver;

import app.Application;
import domain.order.Order;
import domain.user.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DriverMenuPanel extends DriverDashboardPanel { // Assuming DriverDashboardPanel is a JPanel or similar
    private final Application app;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final Driver driver;
    private final JPanel leftPanel;
    private JPanel rightPanel; // This will hold different content panels
    private JButton nowPressed; // Tracks the currently selected button in the left panel

    public DriverMenuPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Driver driver) {
        super(app, cardLayout, mainPanel); // Call to super constructor
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.driver = driver;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1243, 834)); // Overall panel size
        setBackground(new Color(30, 30, 30)); // Match rightPanel's default background

        // Build the left navigation panel
        this.leftPanel = buildLeftPanel();

        // Build the right content panel
        this.rightPanel = new JPanel();
        this.rightPanel.setPreferredSize(new Dimension(854, 834));
        this.rightPanel.setBackground(new Color(30, 30, 30));
        // Using BorderLayout for rightPanel to allow content to fill,
        // or stick to BoxLayout if DriverProfilePanel and OrderInfoPanel are designed
        // for it.
        // For simplicity with switchToRightPanel, BorderLayout is often easier.
        this.rightPanel.setLayout(new BorderLayout());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // --- Default View Setup ---
        // Show profile panel by default
        showProfilePanel(); // This will also set the 'Profile' button as pressed if found

        // Set the "Profile" button as initially pressed
        // This needs to be done AFTER leftPanel is built and buttons are added to it.
        for (Component comp : leftPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if ("Profile".equals(button.getText())) {
                    // Ensure showProfilePanel itself doesn't rely on nowPressed being set before
                    // this
                    // If showProfilePanel calls setButtonPressed, this might be redundant or need
                    // coordination
                    setButtonPressed(button, this.nowPressed); // nowPressed is initially null or last active
                    this.nowPressed = button;
                    break;
                }
            }
        }
        // If profile wasn't the first one to call setButtonPressed via its action:
        if (this.nowPressed == null) {
            // Fallback or error, ideally the loop above finds 'Profile' button
            // For robustness, find the profile button and set it.
            // This logic is a bit tricky if showProfilePanel also calls setButtonPressed.
            // A cleaner way is to have showXPanel methods NOT call setButtonPressed,
            // and have the button's action listener solely responsible.
            // For now, let's assume showProfilePanel sets up the panel,
            // and we explicitly mark the button here.
        }
    }

    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(389, 834));
        panel.setBackground(new Color(44, 44, 44));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Tetenger Dalan");
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(new Color(64, 64, 64));
        titleLabel.setOpaque(true);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 57)); // Span full width
        titleLabel.setPreferredSize(new Dimension(389, 57));

        // Navigation Buttons
        // The default pressed button ("Profile") will be handled in the constructor
        JButton btnOrderInfo = createLeftButton("Order Info", this::showOrderInfoPanel);
        JButton btnProfile = createLeftButton("Profile", this::showProfilePanel); // This will be set as default
        JButton btnLogout = createLeftButton("Logout", this::logout);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(btnOrderInfo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnProfile);
        panel.add(Box.createVerticalGlue()); // Pushes logout to the bottom
        panel.add(btnLogout);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Bottom padding

        return panel;
    }

    private JButton createLeftButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(287, 50));
        button.setPreferredSize(new Dimension(287, 50)); // Explicit preferred size
        button.setBackground(new Color(77, 120, 204)); // Default background
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            if (button != nowPressed) { // Only act if a *different* button is clicked
                setButtonPressed(button, nowPressed);
                nowPressed = button; // Update the currently pressed button
                action.run(); // Execute the associated action
            }
        });
        return button;
    }

    // Sets the visual state of a newly pressed button and resets the previously
    // pressed one
    private void setButtonPressed(JButton currentButton, JButton previousButton) {
        // Style for the currently pressed button
        if (currentButton != null) {
            currentButton.setBackground(new Color(117, 133, 163)); // Pressed color
            currentButton.setForeground(Color.WHITE); // Ensure foreground is still white
        }

        // Reset style for the previously pressed button (if one exists and is not the
        // current one)
        if (previousButton != null && previousButton != currentButton) {
            previousButton.setBackground(new Color(77, 120, 204)); // Default background
            previousButton.setForeground(Color.WHITE);
        }

        // It's generally better to repaint the specific components or their immediate
        // parent
        if (currentButton != null)
            currentButton.repaint();
        if (previousButton != null)
            previousButton.repaint();
        // SwingUtilities.updateComponentTreeUI can be heavy; use repaint for simple
        // color changes.
    }

    private void switchToRightPanel(JPanel newPanel) {
        rightPanel.removeAll();
        rightPanel.add(newPanel, BorderLayout.CENTER); // Add to center if rightPanel is BorderLayout
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void showProfilePanel() {
        // Assuming DriverProfilePanel is a JPanel
        JPanel profilePanel = new DriverProfilePanel(driver); // Create new instance or get existing
        switchToRightPanel(profilePanel);
    }

    private void showOrderInfoPanel() {
        if (driver.getOrder() == null || !driver.isHaveOrder()) {
            // Show a message or a default panel if no order
            JPanel noOrderPanel = new JPanel(new GridBagLayout()); // Centering content
            noOrderPanel.setBackground(new Color(30, 30, 30));
            JLabel messageLabel = new JLabel("You don't have any active order yet.");
            messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            messageLabel.setForeground(Color.WHITE);
            noOrderPanel.add(messageLabel);
            switchToRightPanel(noOrderPanel);
            return;
        }

        // Assuming getOrderInfoPanel() returns a JPanel, likely a
        // CustomerOrderDetailPanel instance
        // It's crucial that this panel uses null layout if we are setting bounds
        // manually for new buttons
        JPanel orderInfoPanel = driver.getOrder().getOrderInfoPanel();
        if (orderInfoPanel.getLayout() != null) {
            // If it's not null layout, adding components with setBounds won't work as
            // expected.
            // For this example, we proceed assuming it's null layout or we adapt.
            // If it's, say, BorderLayout, we'd add to SOUTH, etc.
            // For now, we clear it and rebuild or selectively remove.
        }

        // --- Remove unwanted components (Review label and original Chat button) ---
        List<Component> componentsToRemove = new ArrayList<>();
        for (Component comp : orderInfoPanel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText() != null && label.getText().startsWith("Review:")) {
                    componentsToRemove.add(comp);
                }
            } else if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                // Assuming the original button is "Chat with Driver"
                if (button.getText() != null && button.getText().equals("Chat with Driver")) {
                    componentsToRemove.add(comp);
                }
            }
        }
        for (Component comp : componentsToRemove) {
            orderInfoPanel.remove(comp);
        }

        // --- Add new driver-specific buttons ---
        // Helper for styling these action buttons
        ActionListener buttonStyler = e -> {
            JButton source = (JButton) e.getSource();
            source.setBackground(new Color(100, 100, 100)); // Example style
            source.setForeground(Color.WHITE);
            source.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            source.setFocusPainted(false);
            source.setBorderPainted(false);
        };

        // Define a consistent styling method for these action buttons
        // This could also be a separate private method like
        // styleDriverActionButton(JButton button)
        Font actionButtonFont = new Font("Segoe UI Semibold", Font.PLAIN, 14);
        Color actionButtonBg = new Color(60, 60, 60); // Slightly different from left panel for distinction
        Color actionButtonFg = Color.WHITE;

        int buttonY = 680; // Adjusted Y position for buttons to be clearly visible
                           // This might need tweaking based on the actual content height of orderInfoPanel
        int buttonHeight = 35;
        int buttonSpacing = 10;

        JPanel buttonPanel = new JPanel(); // Use a sub-panel for buttons for easier layout
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, buttonSpacing, 0)); // Center buttons in a row
        buttonPanel.setBackground(orderInfoPanel.getBackground()); // Match background
        // buttonPanel.setOpaque(false); // If orderInfoPanel background should show
        // through

        JButton chatButton = new JButton("Chat with Customer");
        styleDriverActionButton(chatButton, actionButtonFont, actionButtonBg, actionButtonFg);
        chatButton.addActionListener(e -> {
            if (driver.getOrder() != null)
                driver.getOrder().showChat(driver);
        });
        // chatButton.setBounds(100, buttonY, 150, buttonHeight); // Manual bounds if
        // orderInfoPanel is null layout
        // orderInfoPanel.add(chatButton);
        buttonPanel.add(chatButton);

        // backButton.setBounds(10, buttonY, 120, buttonHeight); // Manual bounds
        // orderInfoPanel.add(backButton);

        JButton dropOffButton = new JButton("Drop Off");
        styleDriverActionButton(dropOffButton, actionButtonFont, actionButtonBg, actionButtonFg);
        dropOffButton.addActionListener(e -> {
            if (driver.getOrder() != null) {
                // Confirmation dialog for critical action
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to complete this order?", "Confirm Drop Off",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    driver.getOrder().dropOff(); // Perform drop off
                    JOptionPane.showMessageDialog(this, "Order Completed Successfully!", "Order Status",
                            JOptionPane.INFORMATION_MESSAGE);
                    driver.getOrder().closeChat(); // Close chat window if open
                    // remove drop off button
                    orderInfoPanel.remove(dropOffButton); // Remove the drop off button
                    orderInfoPanel.revalidate(); // Refresh the panel to reflect changes
                    orderInfoPanel.repaint(); // Repaint to ensure UI updates
                    SwingUtilities.updateComponentTreeUI(orderInfoPanel); // Update UI tree

                    // Refresh the entire DriverMenuPanel to reflect changes (e.g., no active order)
                    // This is a bit heavy but ensures UI consistency with the current app structure
                    // DriverMenuPanel newMenu = new DriverMenuPanel(this.app, this.cardLayout,
                    // this.mainPanel, this.driver);
                    // this.mainPanel.remove(DriverMenuPanel.this); // Remove current instance
                    // this.mainPanel.add(newMenu, "DriverMenu"); // Add new instance
                    // this.cardLayout.show(this.mainPanel, "DriverMenu"); // Show it
                }
            }
        });
        // dropOffButton.setBounds(260, buttonY, 100, buttonHeight); // Manual bounds
        // orderInfoPanel.add(dropOffButton);
        // Add buttons to the buttonPanel

        // If orderInfoPanel uses null layout, you'd set bounds for buttonPanel or add
        // buttons directly.
        // If orderInfoPanel uses a layout manager (e.g., BorderLayout), add buttonPanel
        // to a region.
        // Assuming orderInfoPanel might not be null layout, or we want more control:
        // We can add the buttonPanel to the SOUTH of the orderInfoPanel if it's
        // BorderLayout.
        // Or, if orderInfoPanel is complex, we might need to ensure it has space.

        // For now, if orderInfoPanel is null layout, we need to set bounds for
        // buttonPanel
        // buttonPanel.setBounds(0, buttonY, orderInfoPanel.getWidth(), buttonHeight +
        // 20); // Example bounds for buttonPanel
        // orderInfoPanel.add(buttonPanel);

        // A common approach: make orderInfoPanel use BorderLayout.
        // Then add the actual details to CENTER and buttonPanel to SOUTH.
        // If orderInfoPanel is from getOrderInfoPanel() and we can't change its layout:
        // We have to use setBounds for each button as originally intended by user.
        // Reverting to direct add with setBounds if that's the expectation for
        // orderInfoPanel:
        // Remove buttonPanel creation and adding, uncomment setBounds for individual
        // buttons.
        // For this example, I'll assume orderInfoPanel can have a button panel added to
        // its south or uses null layout.
        // Let's stick to the user's original intent of adding buttons directly with
        // setBounds,
        // assuming orderInfoPanel uses null layout.

        int yPosButtons = 650; // Let's try to position them lower, assuming details take up space
                               // This needs to be tested with the actual height of order details.
                               // The CustomerOrderDetailPanel's content ended around y=530-550.
        chatButton.setBounds((854 - 150 - 80 - 100 - 20) / 2 + 10, yPosButtons, 150, 35); // Centered-ish row

        dropOffButton.setBounds((854 - 150 - 80 - 100 - 20) / 2 + 150 + 20, yPosButtons, 100, 35);

        orderInfoPanel.add(chatButton);
        if (!(driver.getOrder().isDrop())) {
            orderInfoPanel.add(dropOffButton);
        }

        orderInfoPanel.revalidate();
        orderInfoPanel.repaint();

        switchToRightPanel(orderInfoPanel);
    }

    // Helper to style action buttons in the order info panel
    private void styleDriverActionButton(JButton button, Font font, Color bgColor, Color fgColor) {
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false); // Or a subtle border: BorderFactory.createLineBorder(Color.DARK_GRAY)
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Add padding inside the button
        button.setMargin(new Insets(5, 10, 5, 10));
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // Perform any actual logout logic if needed (e.g., clearing session, notifying
            // app)
            // app.performLogout(driver); // Example
            cardLayout.show(mainPanel, "MainFrame"); // Switch to login or main welcome screen
        }
    }

    // Dummy DriverDashboardPanel for compilation, replace with your actual class
    // static class DriverDashboardPanel extends JPanel {
    // public DriverDashboardPanel(Application app, CardLayout cardLayout, JPanel
    // mainPanel) {}
    // }
    // Dummy Application class
    // static class Application { public void performLogout(Driver d){} }
    // Dummy DriverProfilePanel class
    // static class DriverProfilePanel extends JPanel { public
    // DriverProfilePanel(Driver d){ setBackground(Color.CYAN); add(new
    // JLabel("Profile of " + d.getName()));} }
}
