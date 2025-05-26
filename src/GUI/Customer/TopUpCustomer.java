package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import User.*;

public class TopUpCustomer extends CustomerPanel {
    public TopUpCustomer(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("Top Up Balance", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(30, 20, 300, 30);
        add(titleLabel);

        JLabel paymentLabel = new JLabel("Choose payment method: ");

        String[] paymentMethods = { "Bank Jawir", "Bank CBA" };
        JComboBox<String> paymentComboBox = new JComboBox<>(paymentMethods);

        JLabel amountLabel = new JLabel("Amount: Rp. ");
        JTextField amountField = new JTextField();

        JButton topUpButton = new JButton("Top Up");
        JButton backButton = new JButton("Back to Menu");

        paymentLabel.setBounds(30, 60, 200, 30);
        paymentComboBox.setBounds(30, 90, 300, 30);
        amountLabel.setBounds(30, 130, 100, 30);
        amountField.setBounds(30, 160, 300, 30);
        topUpButton.setBounds(30, 200, 100, 30);
        backButton.setBounds(140, 200, 150, 30);

        add(paymentLabel);
        add(paymentComboBox);
        add(amountLabel);
        add(amountField);
        add(topUpButton);
        add(backButton);

        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paymentMethod = (String) paymentComboBox.getSelectedItem();
                String amountText = amountField.getText();

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an amount!");
                    return;
                }

                int amount = Integer.parseInt(amountText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount!");
                    return;
                }
                int choice = (paymentMethod.equals("Bank Jawir")) ? 1 : 2;
                JPanel paymentPanel = paymentGetwayPanel(customer, paymentMethod, amount, choice, app, cardLayout,
                        mainPanel);
                mainPanel.add(paymentPanel, "PaymentGetwayPanel");
                cardLayout.show(mainPanel, "PaymentGetwayPanel");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(customerMenuPanel, "CustomerMenu");
                cardLayout.show(mainPanel, "CustomerMenu");
            }
        });
    }

    JPanel paymentGetwayPanel(Customer customer, String paymentMethod, int amount, int choice, Application app,
            CardLayout cardLayout, JPanel mainPanel) {
        JPanel paymentPanel = new JPanel(null);
        paymentPanel.setPreferredSize(new Dimension(400, 300));

        JLabel titleLabel = new JLabel("Top Up Balance", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel amountLabel = new JLabel("Amount: Rp. " + amount);
        String virtualAccount = app.createPayment(choice, customer, amount);

        JLabel virtualAccountLabel = new JLabel("Virtual Account: " + virtualAccount);
        virtualAccountLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel instructionLabel = new JLabel("Please transfer to the virtual account above and confirm your payment.");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton doneButton = new JButton("Done");

        titleLabel.setBounds(30, 20, 340, 30);
        amountLabel.setBounds(30, 60, 300, 30);
        virtualAccountLabel.setBounds(30, 100, 300, 30);
        instructionLabel.setBounds(30, 140, 340, 30);
        doneButton.setBounds(30, 200, 100, 30);

        paymentPanel.add(titleLabel);
        paymentPanel.add(amountLabel);
        paymentPanel.add(virtualAccountLabel);
        paymentPanel.add(instructionLabel);
        paymentPanel.add(doneButton);

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.topupBalance(amount, customer);
                JOptionPane.showMessageDialog(null, "Top Up successful! New balance: Rp. " + customer.getBalance());

                JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(customerMenuPanel, "CustomerMenu");
                cardLayout.show(mainPanel, "CustomerMenu");
            }
        });

        return paymentPanel;
    }
}
