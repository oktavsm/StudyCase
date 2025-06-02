package gui.driver;

import app.Application;
import domain.order.Order;
import domain.user.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DriverMenuPanel extends DriverDashboardPanel {
    private final Application app;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final Driver driver;
    private final JPanel leftPanel;
    private JPanel rightPanel;
    private JButton nowPressed;

    public DriverMenuPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Driver driver) {
        super(app, cardLayout, mainPanel);
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.driver = driver;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1243, 834));
        setBackground(new Color(30, 30, 30));

        this.leftPanel = buildLeftPanel();

        this.rightPanel = new JPanel();
        this.rightPanel.setPreferredSize(new Dimension(854, 834));
        this.rightPanel.setBackground(new Color(30, 30, 30));
        this.rightPanel.setLayout(new BorderLayout());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        showProfilePanel();

        for (Component comp : leftPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if ("Profile".equals(button.getText())) {
                    setButtonPressed(button, this.nowPressed);
                    this.nowPressed = button;
                    break;
                }
            }
        }

        if (this.nowPressed == null) {
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
        titleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 57));
        titleLabel.setPreferredSize(new Dimension(389, 57));

        JButton btnOrderInfo = createLeftButton("Order Info", this::showOrderInfoPanel);
        JButton btnProfile = createLeftButton("Profile", this::showProfilePanel);
        JButton btnLogout = createLeftButton("Logout", this::logout);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(btnOrderInfo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnProfile);
        panel.add(Box.createVerticalGlue());
        panel.add(btnLogout);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        return panel;
    }

    private JButton createLeftButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(287, 50));
        button.setPreferredSize(new Dimension(287, 50));
        button.setBackground(new Color(77, 120, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            if (button != nowPressed) {
                setButtonPressed(button, nowPressed);
                nowPressed = button;
                action.run();
            }
        });
        return button;
    }

    private void setButtonPressed(JButton currentButton, JButton previousButton) {
        if (currentButton != null) {
            currentButton.setBackground(new Color(117, 133, 163));
            currentButton.setForeground(Color.WHITE);
        }

        if (previousButton != null && previousButton != currentButton) {
            previousButton.setBackground(new Color(77, 120, 204));
            previousButton.setForeground(Color.WHITE);
        }

        if (currentButton != null)
            currentButton.repaint();
        if (previousButton != null)
            previousButton.repaint();
    }

    private void switchToRightPanel(JPanel newPanel) {
        rightPanel.removeAll();
        rightPanel.add(newPanel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void showProfilePanel() {
        JPanel profilePanel = new DriverProfilePanel(driver);
        switchToRightPanel(profilePanel);
    }

    private void showOrderInfoPanel() {
        if (driver.getOrder() == null || !driver.isHaveOrder()) {
            JPanel noOrderPanel = new JPanel(new GridBagLayout());
            noOrderPanel.setBackground(new Color(30, 30, 30));
            JLabel messageLabel = new JLabel("You don't have any active order yet.");
            messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            messageLabel.setForeground(Color.WHITE);
            noOrderPanel.add(messageLabel);
            switchToRightPanel(noOrderPanel);
            return;
        }

        JPanel orderInfoPanel = driver.getOrder().getOrderInfoPanel();
        if (orderInfoPanel.getLayout() != null) {
        }

        List<Component> componentsToRemove = new ArrayList<>();
        for (Component comp : orderInfoPanel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText() != null && label.getText().startsWith("Review:")) {
                    componentsToRemove.add(comp);
                }
            } else if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText() != null && button.getText().equals("Chat with Driver")) {
                    componentsToRemove.add(comp);
                }
            }
        }
        for (Component comp : componentsToRemove) {
            orderInfoPanel.remove(comp);
        }

        ActionListener buttonStyler = e -> {
            JButton source = (JButton) e.getSource();
            source.setBackground(new Color(100, 100, 100));
            source.setForeground(Color.WHITE);
            source.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
            source.setFocusPainted(false);
            source.setBorderPainted(false);
        };

        Font actionButtonFont = new Font("Segoe UI Semibold", Font.PLAIN, 14);
        Color actionButtonBg = new Color(60, 60, 60);
        Color actionButtonFg = Color.WHITE;

        int buttonY = 680;
        int buttonHeight = 35;
        int buttonSpacing = 10;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, buttonSpacing, 0));
        buttonPanel.setBackground(orderInfoPanel.getBackground());

        JButton chatButton = new JButton("Chat with Customer");
        styleDriverActionButton(chatButton, actionButtonFont, actionButtonBg, actionButtonFg);
        chatButton.addActionListener(e -> {
            if (driver.getOrder() != null)
                driver.getOrder().showChat(driver);
        });
        buttonPanel.add(chatButton);

        JButton dropOffButton = new JButton("Drop Off");
        styleDriverActionButton(dropOffButton, actionButtonFont, actionButtonBg, actionButtonFg);
        dropOffButton.addActionListener(e -> {
            if (driver.getOrder() != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to complete this order?", "Confirm Drop Off",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    driver.getOrder().dropOff();
                    JOptionPane.showMessageDialog(this, "Order Completed Successfully!", "Order Status",
                            JOptionPane.INFORMATION_MESSAGE);
                    driver.getOrder().closeChat();
                    orderInfoPanel.remove(dropOffButton);
                    orderInfoPanel.revalidate();
                    orderInfoPanel.repaint();
                    SwingUtilities.updateComponentTreeUI(orderInfoPanel);
                }
            }
        });

        int yPosButtons = 650;
        chatButton.setBounds((854 - 150 - 80 - 100 - 20) / 2 + 10, yPosButtons, 150, 35);

        dropOffButton.setBounds((854 - 150 - 80 - 100 - 20) / 2 + 150 + 20, yPosButtons, 100, 35);

        orderInfoPanel.add(chatButton);
        if (!(driver.getOrder().isDrop())) {
            orderInfoPanel.add(dropOffButton);
        }

        orderInfoPanel.revalidate();
        orderInfoPanel.repaint();

        switchToRightPanel(orderInfoPanel);
    }

    private void styleDriverActionButton(JButton button, Font font, Color bgColor, Color fgColor) {
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(5, 10, 5, 10));
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            cardLayout.show(mainPanel, "MainFrame");
        }
    }
}