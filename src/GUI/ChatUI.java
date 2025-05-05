package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatUI extends JFrame {
    private JPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JScrollPane scrollPane;

    public ChatUI() {
        setTitle("Chat");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel buat chat bubble
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(chatPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel bawah buat input
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Listener tombol Send
        sendButton.addActionListener(e -> sendMessage("You", inputField.getText()));
        
        setVisible(true);
    }

    private void sendMessage(String sender, String message) {
        JLabel chatBubble = new JLabel(sender + ": " + message);
        chatBubble.setOpaque(true);
        chatBubble.setBackground(sender.equals("You") ? Color.CYAN : Color.LIGHT_GRAY);
        chatBubble.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        chatPanel.add(chatBubble);
        chatPanel.revalidate();
        inputField.setText("");
    }

    public static void main(String[] args) {
        new ChatUI();
    }
}
