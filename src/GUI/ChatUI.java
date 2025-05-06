// package GUI;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
// import App.Application;
// import Order.Order;
// import Service.GoogleMapService;
// import User.*;

// public class ChatUI extends JFrame {
//     private JPanel chatPanel;
//     private JTextField inputField;
//     private JButton sendButton;
//     private JScrollPane scrollPane;
//     Order order;

//     public ChatUI(Order order) {
//         this.order = order;
//         setTitle("Chat with driver - "+order.getDriver().getName()+" - "+order.getDriver().getVehicle().getName()+" "+order.getDriver().getVehicle().getPlateNumber()); 
//         setSize(400, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         // Panel buat chat bubble
//         chatPanel = new JPanel();
//         chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

//         scrollPane = new JScrollPane(chatPanel);
//         add(scrollPane, BorderLayout.CENTER);

//         // Panel bawah buat input
//         JPanel inputPanel = new JPanel(new BorderLayout());
//         inputField = new JTextField();
//         sendButton = new JButton("Send");

//         inputPanel.add(inputField, BorderLayout.CENTER);
//         inputPanel.add(sendButton, BorderLayout.EAST);
//         add(inputPanel, BorderLayout.SOUTH);

//         // Listener tombol Send
//         sendButton.addActionListener(e -> {
//             String msg = inputField.getText();
//             sendMessage("You", msg);
//             order.saveChat(order.getCustomer(), msg);
//         });
        


//         inputField.addActionListener(e -> {
//             sendMessage("You", inputField.getText());
//             order.saveChat(order.getCustomer(), inputField.getText());
//         });
//         // Load chat sebelumny
//         loadMessage();
//         // Set agar hanya menutup frame chat ui ketika di close
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         // Set agar chat ui tidak bisa di resize
//         setResizable(false);

        
//         setVisible(true);
//     }

//     private void loadMessage(){
//         for (String chat : order.getChat()) {
//             String[] parts = chat.split("###***###");
//             String sender = parts[0].equals(order.getCustomer().getName()) ? "You" : order.getDriver().getName();
//             String message = parts[1];
//             sendMessage(sender, message);
//         }
//     }

//     private void sendMessage(String sender, String message) {
//         // Panel wrapper per message
//         JPanel bubbleWrapper = new JPanel(new FlowLayout(sender.equals("You") ? FlowLayout.RIGHT : FlowLayout.LEFT));
//         bubbleWrapper.setOpaque(false); // biar transparan
    
//         // Chat bubble
//         JTextArea chatBubble = new JTextArea(message);
//         chatBubble.setLineWrap(true);
//         chatBubble.setWrapStyleWord(true);
//         chatBubble.setEditable(false);
//         chatBubble.setBackground(sender.equals("You") ? new Color(0xD0F0FD) : new Color(0xE6E6E6));
//         chatBubble.setForeground(Color.BLACK);
//         chatBubble.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
//         chatBubble.setFont(new Font("Arial", Font.PLAIN, 14));
//         chatBubble.setMaximumSize(new Dimension(250, Short.MAX_VALUE));
    
//         // Bungkus bubble ke panel, kasih padding antar bubble
//         bubbleWrapper.add(chatBubble);
//         bubbleWrapper.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // jarak antar bubble
    
//         chatPanel.add(bubbleWrapper);
//         chatPanel.revalidate();
//         chatPanel.repaint();
    
//         inputField.setText("");
    
//         // Auto scroll ke bawah
//         SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));

//     }
    

    
// }

package GUI;

import javax.swing.*;
import java.awt.*;
import App.Application;
import Order.Order;
import Service.GoogleMapService;
import User.*;

public class ChatUI extends JFrame {
    private JPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JScrollPane scrollPane;
    private Box verticalBox;
    Order order;
    User user;

    public ChatUI(Order order, User user) {
        this.order = order;
        setTitle(order.getDriver().getName() + " - " +
                order.getDriver().getVehicle().getName() + " " + order.getDriver().getVehicle().getPlateNumber());
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close khusus jendela ini
        setResizable(false);
        setLayout(new BorderLayout());

        // Panel chat
        chatPanel = new JPanel();
        verticalBox = Box.createVerticalBox();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(verticalBox, BorderLayout.NORTH); // Supaya bubble nempel di atas

        scrollPane = new JScrollPane(chatPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel input bawah
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Tombol kirim (klik)
        sendButton.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (!msg.isEmpty()) {
                sendMessage("You", msg);
                order.saveChat(order.getCustomer(), msg);
            }
        });

        // Tekan Enter = kirim juga
        inputField.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (!msg.isEmpty()) {
                sendMessage("You", msg);
                order.saveChat(order.getCustomer(), msg);
            }
        });

        loadMessage(); // Load chat lama
        setVisible(true);
    }

    private void loadMessage() {
        for (String chat : order.getChat()) {
            String[] parts = chat.split("###\\*\\*\\*###");
            String sender = parts[0].equals(user.getName()) ? "You" : order.getDriver().getName();
            String message = parts[1];
            sendMessage(sender, message);
        }
    }

    private void sendMessage(String sender, String message) {
        // Panel wrapper per chat bubble
        JPanel bubbleWrapper = new JPanel(new FlowLayout(sender.equals("You") ? FlowLayout.RIGHT : FlowLayout.LEFT));
        bubbleWrapper.setOpaque(false);

        // Chat bubble
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
        verticalBox.add(Box.createVerticalStrut(5)); // jarak antar bubble

        chatPanel.revalidate();
        chatPanel.repaint();
        inputField.setText("");

        // Scroll ke bawah otomatis
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }
}
