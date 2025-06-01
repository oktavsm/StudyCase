package gui.driver;

import app.Application;
import domain.user.*;
import java.awt.*;
import javax.swing.*;

public class DriverMenuPanel extends DriverDashboardPanel {
    private final Application app;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final Driver driver;
    private final JPanel leftPanel;
    private JPanel rightPanel;

    public DriverMenuPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Driver driver) {
        super(app, cardLayout, mainPanel);
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.driver = driver;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1243, 834));

        this.leftPanel = buildLeftPanel();
        this.rightPanel = new JPanel();
        this.rightPanel.setPreferredSize(new Dimension(854, 834));
        this.rightPanel.setBackground(new Color(30, 30, 30));
        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));

        this.rightPanel.add(new DriverProfilePanel(driver));

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(389, 834));
        panel.setBackground(new Color(44, 44, 44));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome, " + driver.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBackground(new Color(64, 64, 64));
        welcomeLabel.setOpaque(true);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setMaximumSize(new Dimension(389, 57));

        JButton btnOrderInfo = createLeftButton("Order Info", this::showOrderInfoPanel);
        JButton btnProfile = createLeftButton("Profile", this::showProfilePanel);
        JButton btnLogout = createLeftButton("Logout", this::logout);

        panel.add(welcomeLabel);
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
        button.addActionListener(e -> action.run());
        return button;
    }

    private void switchToRightPanel(JPanel newPanel) {
        rightPanel.removeAll();
        rightPanel.add(newPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void showProfilePanel() {
        JPanel profilePanel = new DriverProfilePanel(driver);
        switchToRightPanel(profilePanel);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            cardLayout.show(mainPanel, "MainMenu");
        }
    }

    private void showOrderInfoPanel() {
        if (!driver.isHaveOrder()) {
            JOptionPane.showMessageDialog(null, "You don't have any order yet");
            return;
        }

        JPanel orderInfoPanel = driver.getOrder().getOrderInfoPanel();

        for (Component comp : orderInfoPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                String text = button.getText();
                if (text.equals("Chat with Driver") || text.equals("Back") || text.equals("Drop Off")) {
                    orderInfoPanel.remove(button);
                }
            }
        }

        JButton chatButton = new JButton("Chat with Customer");
        chatButton.setBounds(100, 586, 150, 30);
        chatButton.addActionListener(e -> driver.getOrder().showChat(driver));
        orderInfoPanel.add(chatButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 586, 80, 30);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "DriverMenu");
            driver.getOrder().closeChat();
        });
        orderInfoPanel.add(backButton);

        JButton dropOffButton = new JButton("Drop Off");
        dropOffButton.setBounds(260, 586, 80, 30);
        dropOffButton.addActionListener(e -> {
            driver.getOrder().dropOff();
            JOptionPane.showMessageDialog(null, "Order Completed");
            JPanel updatedPanel = new DriverMenuPanel(app, cardLayout, mainPanel, driver);
            mainPanel.remove(this);
            mainPanel.add(updatedPanel, "DriverMenu");
            cardLayout.show(mainPanel, "DriverMenu");
            driver.getOrder().closeChat();
        });
        orderInfoPanel.add(dropOffButton);

        orderInfoPanel.revalidate();
        orderInfoPanel.repaint();

        driver.getOrder().setOrderInfoPanel(orderInfoPanel);
        driver.getOrder().initPanel(app, cardLayout, mainPanel);
    }
}
