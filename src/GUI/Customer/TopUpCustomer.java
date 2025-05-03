package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import User.*;
public class TopUpCustomer extends CustomerPanel {
    public TopUpCustomer(Application app, CardLayout cardLayout, JPanel mainPanel, User customer) {
        super(app, cardLayout, mainPanel);
        /*  
         preview layout
         _____________________________
         |Top Up Balance
         |
         |Choose payment method : ____  (combobox : Bank Jawir , Bank CBA)
         |
         |Amount : Rp. __________
         |____________________________
        
        payment getway panel 
         _____________________________
         |Top Up Balance
         |Amount : Rpxxxxxx

         Virtual Account : xxxxxxx
         |
         |Please transfer to the virtual account above etc
         |
         |____________________________
        
         */

        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Top Up Balance", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        add(titleLabel, BorderLayout.NORTH);
        JPanel topUpPanel = new JPanel(new GridLayout(3, 2)); // 3 baris, 2 kolom
        JLabel paymentLabel = new JLabel("Choose payment method: ");
        String[] paymentMethods = {"Bank Jawir", "Bank CBA"};
        JComboBox<String> paymentComboBox = new JComboBox<>(paymentMethods);
        JLabel amountLabel = new JLabel("Amount: Rp. ");
        JTextField amountField = new JTextField();
        JButton topUpButton = new JButton("Top Up");
        topUpPanel.add(paymentLabel);
        topUpPanel.add(paymentComboBox);
        topUpPanel.add(amountLabel);
        topUpPanel.add(amountField);
        topUpPanel.add(topUpButton);
        add(topUpPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back to Menu");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Action listener for top up button
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
                // Process top up logic here
                // For example, update customer balance and show confirmation message
                // go to payment getway panel
                int choice = (paymentMethod.equals("Bank Jawir")) ? 1 : 2; // 1 for Bank Jawir, 2 for Bank CBA
                JPanel paymentPanel = paymentGetwayPanel(customer, paymentMethod, amount, choice, app, cardLayout, mainPanel);
                mainPanel.add(paymentPanel, "PaymentGetwayPanel");
                cardLayout.show(mainPanel, "PaymentGetwayPanel");
            }
        });
}

JPanel paymentGetwayPanel(User customer, String paymentMethod, int amount,int choice,Application app, CardLayout cardLayout, JPanel mainPanel) {
    JPanel paymentPanel = new JPanel(new GridLayout(3, 1));
    JLabel titleLabel = new JLabel("Top Up Balance", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
    paymentPanel.add(titleLabel);
    JLabel amountLabel = new JLabel("Amount: Rp. " + amount);
    paymentPanel.add(amountLabel);
    String virtualAccount = app.createPayment(choice, (Customer)customer, amount); // create virtual account
    JLabel virtualAccountLabel = new JLabel("Virtual Account: " + virtualAccount);
    virtualAccountLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Set font size
    paymentPanel.add(virtualAccountLabel);
    JLabel instructionLabel = new JLabel("Please transfer to the virtual account above and confirm your payment.");
    instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Set font size
    //done button : return to main menu and set balance
    JButton doneButton = new JButton("Done");
    doneButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Update customer balance and show confirmation message
            app.topupBalance(amount, (Customer)customer);
            JOptionPane.showMessageDialog(null, "Top Up successful! New balance: Rp. " + customer.getBalance());
            //refresh menuCustomer panel
            JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, (Customer)customer);
            mainPanel.remove(customerMenuPanel);
            mainPanel.add(customerMenuPanel, "CustomerMenu");
            cardLayout.show(mainPanel, "CustomerMenu");
        }
    });
    paymentPanel.add(instructionLabel);
    paymentPanel.add(doneButton);


    return paymentPanel;
}
}