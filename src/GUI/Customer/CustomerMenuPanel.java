package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import domain.user.*;

public class CustomerMenuPanel extends CustomerDashboardPanel {
    private final Application app;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final Customer customer;
    private final JPanel leftPanel;
    private JPanel rightPanel;
    private JButton nowPressed;

    public CustomerMenuPanel(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.customer = customer;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1243, 834));

        this.leftPanel = buildLeftPanel();
        this.rightPanel = new JPanel();
        this.rightPanel.setPreferredSize(new Dimension(854, 834));
        this.rightPanel.setBackground(new Color(30, 30, 30));
        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));

        this.rightPanel.add(new CustomerProfilePanel(customer));
        //set default pressed button
        this.nowPressed = createLeftButton("Profile", this::showProfilePanel);
        setButtonPressed(nowPressed, null);



        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        handleRatingIfAvailable();
    }

    private JPanel buildLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(389, 834));
        leftPanel.setBackground(new Color(44, 44, 44));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(new Color(64, 64, 64));
        titleLabel.setOpaque(true);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(389, 57));

        JButton btnTopUp = createLeftButton("Top Up Balance", this::showTopUpPanel);
        JButton btnOrder = createLeftButton("Tetenger Services", this::showOrderPanel);
        JButton btnOrderDetail = createLeftButton("Order Info", this::showOrderInfoPanel);
        JButton btnProfile = createLeftButton("Profile", this::showProfilePanel);
        JButton btnLogout = createLeftButton("Logout", this::logout);

        leftPanel.add(titleLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 140)));
        leftPanel.add(btnTopUp);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(btnOrder);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(btnOrderDetail);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(btnProfile);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(btnLogout);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        return leftPanel;
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
        button.addActionListener(e -> {
            if (nowPressed != null) {
                setButtonPressed(button, nowPressed);
            } else {
                setButtonPressed(button, null);
            }
            nowPressed = button;
            action.run();
        });
        return button;
    }

    private void switchToRightPanel(JPanel newPanel) {
        rightPanel.removeAll();
        rightPanel.add(newPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void showTopUpPanel() {
        JPanel topUpPanel = new CustomerTopupPanel(app, cardLayout, customer);
        switchToRightPanel(topUpPanel);
    }

    private void showOrderPanel() {
        JPanel orderPanel = new CustomerOrderPanel(app, customer, () -> {
            JPanel orderInfoPanel = customer.getOrder().getOrderInfoPanel();
            switchToRightPanel(orderInfoPanel);
        });
        switchToRightPanel(orderPanel);
    }

    private void showOrderInfoPanel() {
        if (!customer.isOrdering()) {
            JOptionPane.showMessageDialog(null, "You are not ordering any service!");
            return;
        }

        CustomerOrderDetailPanel orderInfoPanel = (CustomerOrderDetailPanel) customer.getOrder().getOrderInfoPanel();
        //remove
        orderInfoPanel.refreshOrderDetail();
        switchToRightPanel(orderInfoPanel);
    }

    private void showProfilePanel() {
        JPanel profilePanel = new CustomerProfilePanel(customer);
        switchToRightPanel(profilePanel);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            cardLayout.show(mainPanel, "MainFrame");
        }
    }

    // pressed button method (change color)
    private void setButtonPressed(JButton button, JButton previousButton) {
        button.setBackground(new Color(117,133,163));
        button.setForeground(Color.WHITE);
        if (previousButton != null) {
            previousButton.setBackground(new Color(77, 120, 204));
            
        }
        //refresh
        SwingUtilities.updateComponentTreeUI(button);
        if (previousButton != null) {
            SwingUtilities.updateComponentTreeUI(previousButton);
        }

    }
    private void handleRatingIfAvailable() {
        if (customer.isOrdering() && customer.getOrder().isDrop()) {
            JPanel ratePanel = new CustomerRatingPanel(app, cardLayout, mainPanel, customer.getOrder());
            mainPanel.remove(ratePanel);
            mainPanel.add(ratePanel, "RatingUtil");
            cardLayout.show(mainPanel, "RatingUtil");
        }
    }
}
