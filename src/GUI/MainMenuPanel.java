package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(null); // Set layout menjadi null untuk absolute positioning

        JLabel titleLabel = new JLabel("=== Online Transportation ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        titleLabel.setBounds(30, 20, 300, 30); // Set posisi dan ukuran
        add(titleLabel); // Menambahkan titleLabel ke panel

        JButton btnRegisterCustomer = new JButton("Register as a Customer");
        JButton btnRegisterDriver = new JButton("Register as a Driver");
        JButton btnLoginCustomer = new JButton("Login as a Customer");
        JButton btnLoginDriver = new JButton("Login as a Driver");
        JButton btnExit = new JButton("Exit Application");

        // Menentukan posisi dan ukuran masing-masing tombol
        btnRegisterCustomer.setBounds(80, 70, 200, 30);
        btnRegisterDriver.setBounds(80, 110, 200, 30);
        btnLoginCustomer.setBounds(80, 150, 200, 30);
        btnLoginDriver.setBounds(80, 190, 200, 30);
        btnExit.setBounds(80, 230, 200, 30);

        // Menambahkan tombol ke panel
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
        btnRegisterDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegisterDriver"); // Menampilkan panel registrasi driver
            }
        });
        btnLoginDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginDriver"); // Menampilkan panel login driver
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Keluar dari aplikasi
            }
        });
    }
}
