package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import Service.GoogleMapService;
import User.*;

public class ChooseServices extends CustomerPanel {
    public ChooseServices(Application app, CardLayout cardLayout, JPanel mainPanel, Customer customer, String jemput, String tujuan) {
        super(app, cardLayout, mainPanel);


        //preview
        /*
        _________________________
         |
         |image of map
         |_______________________
            |Jarak: 2.5 km | Estimasi waktu: 15 menit

            Tetenger Sepedah Rp. 10.000 (Button)
            Tetenger Montor Rp. 20.000 (Button)
         
         */
        setLayout(new GridLayout(3, 1)); // 3 baris, 1 kolom
        JLabel titleLabel = new JLabel("Choose Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size

        try {
            String[] info = GoogleMapService.getRouteInfo(jemput, tujuan);
            ImageIcon mapImage = GoogleMapService.getStaticMapImage(jemput, tujuan);
        
            JLabel labelMap = new JLabel(mapImage);
            add(labelMap);
            JLabel labelInfo = new JLabel("Jarak: " + info[0] + " | Estimasi waktu: " + info[1]);
            add(labelInfo); 
        } catch (Exception exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengambil data dari Google Maps API.");
        }
        // Tetenger Sepedah
        JButton btnSepedah = new JButton("Tetenger Sepedah Rp. 10.000");
        // Tetenger Montor
        JButton btnMontor = new JButton("Tetenger Montor Rp. 20.000");
        // Back Button
        JButton btnBack = new JButton("Back to Menu");
        add(btnSepedah);
        add(btnMontor);
        add(btnBack);
        // Action listener for back button
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show customer menu panel
                JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                mainPanel.add(customerMenuPanel, "CustomerMenu");
                cardLayout.show(mainPanel, "CustomerMenu"); // Menampilkan panel order
            }
        });
    }
    
}
