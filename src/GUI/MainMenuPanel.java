package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("=== Online Transportation ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        add(titleLabel);  // Menambahkan titleLabel ke window

        JButton btnRegisterCustomer = new JButton("Register as a Customer");
        JButton btnRegisterDriver = new JButton("Register as a Driver");
        JButton btnLoginCustomer = new JButton("Login as a Customer");
        JButton btnLoginDriver = new JButton("Login as a Driver");
        JButton btnExit = new JButton("Exit Application");

        add(btnRegisterCustomer);
        add(btnRegisterDriver);
        add(btnLoginCustomer);
        add(btnLoginDriver);
        add(btnExit);

        // Menambahkan aksi untuk tombol
        btnRegisterCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegisterCustomer"); // Menampilkan panel registrasi customer
            }
        });
        btnLoginCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginCustomer"); // Menampilkan panel login customer
            }
        });


    }
}
