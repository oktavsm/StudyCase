package gui.driver;

import javax.swing.*;
import app.Application;
import java.awt.*;
import java.awt.event.*;
import domain.user.Driver;
import domain.vehicle.*;

public class DriverRegisterPanel extends DriverDashboardPanel {
    public DriverRegisterPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(null);

        JLabel titleLabel = new JLabel("=== Driver Register ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(30, 20, 300, 30);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel("Phone Number: ");
        JTextField phoneField = new JTextField();
        JButton nextButton = new JButton("Next");

        nameLabel.setBounds(30, 60, 100, 30);
        nameField.setBounds(30, 90, 300, 30);

        emailLabel.setBounds(30, 130, 100, 30);
        emailField.setBounds(30, 160, 300, 30);

        passwordLabel.setBounds(30, 200, 100, 30);
        passwordField.setBounds(30, 230, 300, 30);

        phoneLabel.setBounds(30, 270, 100, 30);
        phoneField.setBounds(30, 300, 300, 30);

        nextButton.setBounds(30, 350, 150, 30);

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(phoneLabel);
        add(phoneField);
        add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String phoneNumber = phoneField.getText();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                if (app.validateEmailDriver(email)) {
                    JOptionPane.showMessageDialog(null, "Email already exists!");
                } else {
                    Driver driver = new Driver(email, password, name, phoneNumber, null, app);
                    JPanel vehiclePanel = vehicleRegistPanel(driver);
                    mainPanel.add(vehiclePanel, "VehicleRegistPanel");
                    cardLayout.show(mainPanel, "VehicleRegistPanel");
                }
            }
        });
    }

    private JPanel vehicleRegistPanel(Driver driver) {
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setLayout(null);

        JLabel titleLabel = new JLabel("=== Vehicle Register ===", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(30, 20, 300, 30);
        vehiclePanel.add(titleLabel);

        JLabel vehicleTypeLabel = new JLabel("Vehicle Type: ");
        JComboBox<String> vehicleTypeField = new JComboBox<>(new String[] { "Car", "Motorcycle" });
        JLabel vehicleBrandLabel = new JLabel("Vehicle Brand: ");
        JTextField vehicleBrandField = new JTextField();
        JLabel vehicleColorLabel = new JLabel("Vehicle Color: ");
        JTextField vehicleColorField = new JTextField();
        JLabel vehiclePlateLabel = new JLabel("Vehicle Plate: ");
        JTextField vehiclePlateField = new JTextField();
        JButton registerButton = new JButton("Register Vehicle");

        vehicleTypeLabel.setBounds(30, 60, 100, 30);
        vehicleTypeField.setBounds(30, 90, 300, 30);

        vehicleBrandLabel.setBounds(30, 130, 100, 30);
        vehicleBrandField.setBounds(30, 160, 300, 30);

        vehicleColorLabel.setBounds(30, 200, 100, 30);
        vehicleColorField.setBounds(30, 230, 300, 30);

        vehiclePlateLabel.setBounds(30, 270, 100, 30);
        vehiclePlateField.setBounds(30, 300, 300, 30);

        registerButton.setBounds(30, 350, 150, 30);

        vehiclePanel.add(vehicleTypeLabel);
        vehiclePanel.add(vehicleTypeField);
        vehiclePanel.add(vehicleBrandLabel);
        vehiclePanel.add(vehicleBrandField);
        vehiclePanel.add(vehicleColorLabel);
        vehiclePanel.add(vehicleColorField);
        vehiclePanel.add(vehiclePlateLabel);
        vehiclePanel.add(vehiclePlateField);
        vehiclePanel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = vehicleTypeField.getSelectedItem().toString();
                String brand = vehicleBrandField.getText();
                String color = vehicleColorField.getText();
                String plate = vehiclePlateField.getText();

                if (type.isEmpty() || brand.isEmpty() || color.isEmpty() || plate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                if (app.validateVehicleDriver(plate)) {
                    JOptionPane.showMessageDialog(null, "Vehicle already exists!");
                    return;
                }

                Vehicle vehicle = null;
                if (type.equals("Motorcycle")) {
                    vehicle = new Motorcycle(plate, color, brand);
                } else {
                    vehicle = new Car(plate, color, brand);
                }

                driver.setVehicle(vehicle);
                Vehicle registeredVehicle = app.addVehicle(driver.getEmail(), type, plate, color, brand);

                app.addDriver(driver.getEmail(), driver.getPassword(), driver.getName(), driver.getPhoneNumber(), registeredVehicle);

                JOptionPane.showMessageDialog(null, "Driver registered successfully!");
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        return vehiclePanel;
    }
}
