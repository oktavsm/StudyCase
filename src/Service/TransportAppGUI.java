package Service;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TransportAppGUI extends JFrame {

    public TransportAppGUI() {
        // Set up JFrame
        setTitle("Online Transportation System");
        setSize(400, 300);  // Set size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on screen
        setLayout(new FlowLayout());  // Layout manager untuk menyusun komponen

        // Buat label judul
        JLabel titleLabel = new JLabel("=== Online Transportation ===");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        add(titleLabel);  // Menambahkan titleLabel ke window

        // Buat tombol pilihan menu
        JButton btnRegisterCustomer = new JButton("Register as a Customer");
        JButton btnRegisterDriver = new JButton("Register as a Driver");
        JButton btnLoginCustomer = new JButton("Login as a Customer");
        JButton btnLoginDriver = new JButton("Login as a Driver");
        JButton btnExit = new JButton("Exit Application");

        // Menambahkan aksi untuk tombol
        btnRegisterCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Register as a Customer");
                // Kamu bisa menambahkan logika registrasi disini
            }
        });

        btnRegisterDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Register as a Driver");
                // Logika pendaftaran driver
            }
        });

        btnLoginCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Login as a Customer");
                // Logika login customer
            }
        });

        btnLoginDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Login as a Driver");
                // Logika login driver
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit application
            }
        });

        // Menambahkan tombol ke window
        add(btnRegisterCustomer);
        add(btnRegisterDriver);
        add(btnLoginCustomer);
        add(btnLoginDriver);
        add(btnExit);
    }

    public static void main(String[] args) {
        // Membuat dan menampilkan GUI
        TransportAppGUI frame = new TransportAppGUI();
        frame.setVisible(true);
    }
}

