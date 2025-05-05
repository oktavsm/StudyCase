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
        setLayout(null); // 3 baris, 1 kolom

        //frame size 600*800
        JLabel titleLabel = new JLabel("Choose Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        titleLabel.setBounds(0, 10, 400, 30); // Set position and size

        try {
            String[] info = GoogleMapService.getRouteInfo(jemput, tujuan);
            ImageIcon mapImage = GoogleMapService.getRouteMap(jemput, tujuan);
        
            JLabel labelMap = new JLabel(mapImage);
            //set map possition
            labelMap.setBounds(10, 30, 580, 380); // Set position and size
            add(labelMap);
            JLabel labelInfo = new JLabel("Jarak: " + info[0] + " | Estimasi waktu: " + info[1]);
            labelInfo.setBounds(10, 420, 580, 30); // Set position and size
            add(labelInfo); 
        } catch (Exception exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengambil data dari Google Maps API.");
        }
        // Tetenger Sepedah
        JButton btnSepedah = new JButton("Tetenger Sepedah Rp. 10.000");
        btnSepedah.setBounds(10, 470, 580, 50); // Set position and size
        // Tetenger Montor
        JButton btnMontor = new JButton("Tetenger Montor Rp. 20.000");
        btnMontor.setBounds(10, 530, 580, 50); // Set position and size
        // Back Button
        JButton btnBack = new JButton("Back to Menu");
        btnBack.setBounds(10, 580, 580, 50); // Set position and size
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
