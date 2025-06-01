package gui.app;

import javax.swing.*;
import java.awt.Color;
import domain.user.*;
import domain.vehicle.Car;
import domain.vehicle.Motorcycle;
import domain.vehicle.Vehicle;

public class RegistVehicle extends javax.swing.JPanel {

    private final MainFrame main;
    private String choice = "Motorcycle";
    private Driver driver;

    public RegistVehicle(MainFrame main, Driver driver) {
        initComponents();
        this.main = main;
        this.driver = driver;
        setDriver();
    }

    private void initComponents() {
        choiceButton = new javax.swing.JPanel();
        jChoiceButtonDriver = new javax.swing.JButton();
        jChoiceButtonCustomer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBrandField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPlateField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jColorField = new javax.swing.JTextField();
        jButtonRegister = new javax.swing.JButton();

        setBackground(new java.awt.Color(30, 30, 30));

        choiceButton.setBackground(new java.awt.Color(44, 44, 44));
        choiceButton.setLayout(new java.awt.GridLayout(1, 2, 0, 0));

        jChoiceButtonDriver.setBackground(new java.awt.Color(77, 120, 204));
        jChoiceButtonDriver.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jChoiceButtonDriver.setForeground(new java.awt.Color(255, 255, 255));
        jChoiceButtonDriver.setText("Motorcycle");
        jChoiceButtonDriver.setBorderPainted(false);
        jChoiceButtonDriver.setPreferredSize(new java.awt.Dimension(0, 50));
        jChoiceButtonDriver.addActionListener(evt -> {
            setDriver();
            choice = "Motorcycle";
        });

        jChoiceButtonCustomer.setBackground(new java.awt.Color(77, 120, 204));
        jChoiceButtonCustomer.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jChoiceButtonCustomer.setForeground(new java.awt.Color(255, 255, 255));
        jChoiceButtonCustomer.setText("Car");
        jChoiceButtonCustomer.setBorderPainted(false);
        jChoiceButtonCustomer.setPreferredSize(new java.awt.Dimension(0, 50));
        jChoiceButtonCustomer.addActionListener(evt -> {
            setCustomer();
            choice = "Car";
        });

        choiceButton.add(jChoiceButtonDriver);
        choiceButton.add(jChoiceButtonCustomer);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Driver Vehicle Detail");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("Brand");

        jBrandField.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jBrandField.setPreferredSize(new java.awt.Dimension(0, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setText("Plate");

        jPlateField.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jPlateField.setPreferredSize(new java.awt.Dimension(0, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jLabel4.setForeground(Color.WHITE);
        jLabel4.setText("Color");

        jColorField.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jColorField.setPreferredSize(new java.awt.Dimension(0, 50));

        jButtonRegister.setBackground(new java.awt.Color(204, 102, 0));
        jButtonRegister.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jButtonRegister.setForeground(Color.WHITE);
        jButtonRegister.setText("Register");
        jButtonRegister.setBorderPainted(false);
        jButtonRegister.setPreferredSize(new java.awt.Dimension(97, 50));
        jButtonRegister.addActionListener(evt -> register());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(100)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabel1, 400, 400, 400)
                                        .addComponent(choiceButton, 400, 400, 400)
                                        .addComponent(jLabel2)
                                        .addComponent(jBrandField, 400, 400, 400)
                                        .addComponent(jLabel3)
                                        .addComponent(jPlateField, 400, 400, 400)
                                        .addComponent(jLabel4)
                                        .addComponent(jColorField, 400, 400, 400)
                                        .addComponent(jButtonRegister, 400, 400, 400))
                                .addGap(100)));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(50)
                        .addComponent(jLabel1)
                        .addGap(20)
                        .addComponent(choiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(jLabel2)
                        .addComponent(jBrandField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(jLabel3)
                        .addComponent(jPlateField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(jLabel4)
                        .addComponent(jColorField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(jButtonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50));
    }

    private void register() {

        String choice = this.choice;
        String brand = jBrandField.getText().trim();
        String plate = jPlateField.getText().trim();
        String color = jColorField.getText().trim();

        if (brand.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brand is required");
            return;
        }
        if (plate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plate is required");
            return;
        }
        if (color.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Color is required");
            return;
        }

        if (main.app.validateVehicleDriver(plate)) {
            JOptionPane.showMessageDialog(null, "Vehicle already exists!");
            return;
        }

        Vehicle vehicle = null;
        if (choice.equals("Motorcycle")) {
            vehicle = new Motorcycle(plate, color, brand);
        } else {
            vehicle = new Car(plate, color, brand);
        }

        driver.setVehicle(vehicle);
        Vehicle registeredVehicle = main.app.addVehicle(driver.getEmail(), choice, plate, color, brand);

        main.app.addDriver(driver.getEmail(), driver.getPassword(), driver.getName(), driver.getPhoneNumber(),
                registeredVehicle);
        User user = main.app.validateEmailAndPassword(driver.getEmail(), driver.getPassword());
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Failed to register driver!");
            return;
        }
        main.setDriverMenu((Driver) user);
        JOptionPane.showMessageDialog(null, "Driver registered successfully!");

        // Vehicle vehicle = new Vehicle(brand, plate, color, choice);
        // driver.setVehicle(vehicle);

        JOptionPane.showMessageDialog(this, "Vehicle registered successfully!");

        // main.showPanel("HomeDriver"); // asumsi ada panel ini
    }

    private void setDriver() {
        jChoiceButtonDriver.setBackground(new Color(77, 120, 204));
        jChoiceButtonCustomer.setBackground(new Color(44, 44, 44));
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void setCustomer() {
        jChoiceButtonCustomer.setBackground(new Color(77, 120, 204));
        jChoiceButtonDriver.setBackground(new Color(44, 44, 44));
        SwingUtilities.updateComponentTreeUI(this);
    }

    private javax.swing.JPanel choiceButton;
    private javax.swing.JButton jChoiceButtonDriver;
    private javax.swing.JButton jChoiceButtonCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jBrandField;
    private javax.swing.JTextField jPlateField;
    private javax.swing.JTextField jColorField;
    private javax.swing.JButton jButtonRegister;
}
