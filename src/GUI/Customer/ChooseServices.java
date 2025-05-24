package GUI.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.Application;
import Order.Order;
import Service.GoogleMapService;
import User.*;

public class ChooseServices extends CustomerPanel {
    String info[] = null;
    ImageIcon mapImage= null;
    JLabel labelMap = new JLabel();

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
           info = GoogleMapService.getRouteInfo(jemput, tujuan);
            mapImage = GoogleMapService.getRouteMap(jemput, tujuan);
        
            labelMap = new JLabel(mapImage);
            //set map possition
            labelMap.setBounds(10, 30, 340, 266); // Set position and size
            add(labelMap);
            JLabel labelInfo = new JLabel("Jarak: " + info[0] + " | Estimasi waktu: " + info[1]);
            labelInfo.setBounds(10, 306, 340, 30); // Set position and size
            add(labelInfo); 
        } catch (Exception exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengambil data dari Google Maps API.");
        }
        //parsing distance to double
        String time = info[1];
        String distanceS = info[0].replace(" km", "").replace(",", ".");
        double distance = Double.parseDouble(distanceS);
        //set rate sepedah = 8000/km min 8000 montor 14000/km min 14000
        double rateSepedah = 8000 * Math.max(1, distance);
        double rateMontor = 14000 * Math.max(1, distance);

        // Tetenger Sepedah
        String labelButtonSedah = "Tetenger Sepedah Rp. " + rateSepedah;

        JButton btnSepedah = new JButton(labelButtonSedah);
        btnSepedah.setBounds(10, 346, 340, 50); // Set position and size
        // Tetenger Montor
        String labelButtonMontor = "Tetenger Montor Rp. " + rateMontor;
        JButton btnMontor = new JButton(labelButtonMontor);
        btnMontor.setBounds(10, 406, 340, 50); // Set position and size
        // Back Button
        JButton btnBack = new JButton("Back to Menu");
        btnBack.setBounds(10, 466, 340, 50); // Set position and size
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
        btnSepedah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // make order first
                // make order first
                Order order = customer.newOrder(tujuan, jemput, distance, time, "Motorcycle");
                if(order != null){
                    order.processOrder();
                    //go to order info panel
                    //refresh main menu
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.remove(customerMenuPanel);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    JPanel orderInfo = new OrderInfo(app, cardLayout, mainPanel, order, labelMap, customer);
                    order.setOrderInfoPanel(orderInfo);
                    order.initPanel(app,cardLayout, mainPanel);
                } else {
                    //back to main menu
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    cardLayout.show(mainPanel, "CustomerMenu"); // Menampilkan panel order
                }
            }
        });
        btnMontor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // make order first
                Order order = customer.newOrder(tujuan, jemput, distance,time, "Car");
                if(order != null){
                    order.processOrder();
                    //go to order info panel
                    //refresh main menu
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.remove(customerMenuPanel);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    JPanel orderInfo = new OrderInfo(app, cardLayout, mainPanel, order, labelMap, customer);
                    order.setOrderInfoPanel(orderInfo);
                    order.initPanel(app,cardLayout, mainPanel);
                } else {
                    //back to main menu
                    JPanel customerMenuPanel = new MenuCustomerPanel(app, cardLayout, mainPanel, customer);
                    mainPanel.add(customerMenuPanel, "CustomerMenu");
                    cardLayout.show(mainPanel, "CustomerMenu"); // Menampilkan panel order

                }
            }
        });
    }
    
}
