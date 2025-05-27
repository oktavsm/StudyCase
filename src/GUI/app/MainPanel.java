package gui.app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import app.Application;
import java.awt.*;

public class MainPanel extends JPanel {
    private Color primaryColor = new Color(0x4A90E2);
    private Color bgColor = new Color(0xF5F7FA);

    public MainPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(bgColor);

        JLabel titleLabel = new JLabel("Tetenger Dalan", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(20, 80, 20, 80));
        buttonPanel.setBackground(bgColor);

        JButton btnRegisterCustomer = createButton("Register as a Customer");
        JButton btnRegisterDriver = createButton("Register as a Driver");
        JButton btnLoginCustomer = createButton("Login as a Customer");
        JButton btnLoginDriver = createButton("Login as a Driver");
        JButton btnExit = createButton("Exit Application");

        buttonPanel.add(btnRegisterCustomer);
        buttonPanel.add(btnRegisterDriver);
        buttonPanel.add(btnLoginCustomer);
        buttonPanel.add(btnLoginDriver);
        buttonPanel.add(btnExit);

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        btnRegisterCustomer.addActionListener(e -> cardLayout.show(mainPanel, "RegisterCustomer"));
        btnLoginCustomer.addActionListener(e -> cardLayout.show(mainPanel, "LoginCustomer"));
        btnRegisterDriver.addActionListener(e -> cardLayout.show(mainPanel, "RegisterDriver"));
        btnLoginDriver.addActionListener(e -> cardLayout.show(mainPanel, "LoginDriver"));
        btnExit.addActionListener(e -> System.exit(0));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(primaryColor);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createLineBorder(primaryColor));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
