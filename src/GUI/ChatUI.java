package gui;

import javax.swing.*;
import java.awt.*;
import domain.order.*;
import domain.user.*;

public class ChatUI extends JFrame {
    private JPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JScrollPane scrollPane;
    private Box verticalBox;
    private Order order;
    private User currentUser;
    private User chatPartner;

    public ChatUI(Order order, User currentUser) {
        this.order = order;
        this.currentUser = currentUser;

        if (currentUser instanceof Customer) {
            chatPartner = order.getDriver();
        } else if (currentUser instanceof Driver) {
            chatPartner = order.getCustomer();
        } else {
            JOptionPane.showMessageDialog(null, "Unknown user type.");
            dispose();
            return;
        }

        setTitle("Chat with " + chatPartner.getName());
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        chatPanel = new JPanel();
        verticalBox = Box.createVerticalBox();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(verticalBox, BorderLayout.NORTH);

        scrollPane = new JScrollPane(chatPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendChat());
        inputField.addActionListener(e -> sendChat());

        loadMessages();
    }

    private void sendChat() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty()) {
            sendMessage("You", msg);
            order.saveChat(currentUser, msg);
        }
    }

    private void loadMessages() {
        for (String chat : order.getChat()) {
            String[] parts = chat.split("###\\*\\*\\*###");
            if (parts.length < 2)
                continue;

            String senderName = parts[0];
            String message = parts[1];

            String displaySender = senderName.equals(currentUser.getName()) ? "You" : chatPartner.getName();
            sendMessage(displaySender, message);
        }
    }

    private void sendMessage(String sender, String message) {
        JPanel bubbleWrapper = new JPanel(new FlowLayout(sender.equals("You") ? FlowLayout.RIGHT : FlowLayout.LEFT));
        bubbleWrapper.setOpaque(false);

        JTextArea chatBubble = new JTextArea(message);
        chatBubble.setLineWrap(true);
        chatBubble.setWrapStyleWord(true);
        chatBubble.setEditable(false);
        chatBubble.setBackground(sender.equals("You") ? new Color(0xD0F0FD) : new Color(0xE6E6E6));
        chatBubble.setForeground(Color.BLACK);
        chatBubble.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        chatBubble.setFont(new Font("Arial", Font.PLAIN, 14));
        chatBubble.setMaximumSize(new Dimension(250, Short.MAX_VALUE));

        bubbleWrapper.add(chatBubble);
        bubbleWrapper.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        verticalBox.add(bubbleWrapper);
        verticalBox.add(Box.createVerticalStrut(5));

        chatPanel.revalidate();
        chatPanel.repaint();
        inputField.setText("");

        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar()
                .setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }
}
