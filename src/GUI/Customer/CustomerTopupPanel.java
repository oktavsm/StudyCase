package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;
import domain.user.*;

public class CustomerTopupPanel extends JPanel {
    private final Application app;
    private final Customer customer;
    private JPanel topupPanel;
    private JPanel paymentPanel;

    public CustomerTopupPanel(Application app, CardLayout cardLayout, Customer customer) {
        this.app = app;
        this.customer = customer;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(854, 834));
        setBackground(new Color(30, 30, 30));

        buildTopupPanel(cardLayout);
        add(topupPanel, BorderLayout.CENTER);
    }

    private void buildTopupPanel(CardLayout cardLayout) {
        topupPanel = new JPanel(null);
        topupPanel.setPreferredSize(new Dimension(854, 834));
        topupPanel.setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Top Up Balance", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(277, 40, 300, 40);
        topupPanel.add(titleLabel);

        JLabel paymentLabel = new JLabel("Choose payment method:");
        paymentLabel.setForeground(Color.WHITE);
        paymentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        paymentLabel.setBounds(277, 100, 300, 30);
        topupPanel.add(paymentLabel);

        String[] paymentMethods = { "Bank Jatim", "Bank BCA" };
        JComboBox<String> paymentComboBox = new JComboBox<>(paymentMethods);
        paymentComboBox.setBounds(277, 130, 300, 35);
        paymentComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        paymentComboBox.setBackground(new Color(50, 50, 50));
        paymentComboBox.setForeground(Color.WHITE);
        topupPanel.add(paymentComboBox);

        JLabel amountLabel = new JLabel("Amount (Rp):");
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountLabel.setBounds(277, 180, 300, 30);
        topupPanel.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(277, 210, 300, 35);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        amountField.setBackground(new Color(50, 50, 50));
        amountField.setForeground(Color.WHITE);
        amountField.setCaretColor(Color.WHITE);
        topupPanel.add(amountField);

        JButton topUpButton = new JButton("Top Up");
        topUpButton.setBounds(277, 270, 300, 40);
        topUpButton.setBackground(new Color(204, 102, 0));
        topUpButton.setForeground(Color.WHITE);
        topUpButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        topUpButton.setFocusPainted(false);
        topUpButton.setBorderPainted(false);
        topupPanel.add(topUpButton);

        add(topupPanel, BorderLayout.CENTER);

        topUpButton.addActionListener(e -> {
            String paymentMethod = (String) paymentComboBox.getSelectedItem();
            String amountText = amountField.getText().trim();

            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an amount!");
                return;
            }

            int amount;
            try {
                amount = Integer.parseInt(amountText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format!");
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount!");
                return;
            }

            if (amount > 1000000) {
                JOptionPane.showMessageDialog(this, "Maximum top-up amount is Rp1.000.000!");
                return;
            }

            int choice = paymentMethod.equals("Bank Jawir") ? 1 : 2;
            showPaymentGateway(paymentMethod, amount, choice);
        });
    }

    private void showPaymentGateway(String paymentMethod, int amount, int choice) {
        remove(topupPanel);

        paymentPanel = new JPanel(null);
        paymentPanel.setPreferredSize(new Dimension(854, 834));
        paymentPanel.setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Payment Gateway", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(277, 40, 300, 40);
        paymentPanel.add(titleLabel);

        JLabel amountLabel = new JLabel("Amount: Rp" + amount);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountLabel.setBounds(277, 100, 300, 30);
        paymentPanel.add(amountLabel);

        String virtualAccount = app.createPayment(choice, customer, amount);
        JLabel vaLabel = new JLabel("Virtual Account: " + virtualAccount);
        vaLabel.setForeground(Color.WHITE);
        vaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        vaLabel.setBounds(277, 140, 400, 30);
        paymentPanel.add(vaLabel);

        JLabel instruction = new JLabel("<html>Please transfer to the VA and click 'Done' once paid.</html>");
        instruction.setForeground(Color.LIGHT_GRAY);
        instruction.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        instruction.setBounds(277, 180, 400, 40);
        paymentPanel.add(instruction);

        JButton doneButton = new JButton("Done");
        doneButton.setBounds(277, 240, 140, 40);
        doneButton.setBackground(new Color(204, 102, 0));
        doneButton.setForeground(Color.WHITE);
        doneButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        doneButton.setFocusPainted(false);
        doneButton.setBorderPainted(false);
        paymentPanel.add(doneButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(437, 240, 140, 40);
        backButton.setBackground(new Color(100, 100, 100));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        paymentPanel.add(backButton);

        add(paymentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        doneButton.addActionListener(e -> {
            app.topupBalance(amount, customer);
            JOptionPane.showMessageDialog(this, "Top Up successful! New balance: Rp" + customer.getBalance());
            remove(paymentPanel);
            add(topupPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        backButton.addActionListener(e -> {
            remove(paymentPanel);
            add(topupPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });
    }
}
