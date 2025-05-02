package GUI.Driver;

import App.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import User.Driver;
import Vehicle.*;

public class RegisterDriverPanel extends DriverPanel {
    public RegisterDriverPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        super(app, cardLayout, mainPanel);
        setLayout(new GridLayout(6, 2)); // 6 baris, 2 kolom

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel("Phone Number: ");
        JTextField phoneField = new JTextField();


        JButton nextButton = new JButton("Next");

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
             
                // Validate all fields filled
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                // Validate email first
                if (app.validateEmailDriver(email)) {
                    JOptionPane.showMessageDialog(null, "Email already exists!");
                    return;
                } else {
                    Driver driver = new Driver(name, email, password, phoneNumber, null, app);
                    // Show vehicle registration panel
                    JPanel vehiclePanel = vehicleRegistPanel(driver);
                    mainPanel.add(vehiclePanel, "VehicleRegistPanel");
                    cardLayout.show(mainPanel, "VehicleRegistPanel"); // Show vehicle registration panel

                    
                }
            }
        });
    }

    private JPanel vehicleRegistPanel(Driver driver){
        JPanel vehiclePanel = new JPanel(new GridLayout(5, 2)); // 5 rows, 2 columns

        JLabel vehicleTypeLabel = new JLabel("Vehicle Type: ");
        JTextField vehicleTypeField = new JTextField();
        JLabel vehicleBrandLabel = new JLabel("Vehicle Brand: ");
        JTextField vehicleBrandField = new JTextField();
        JLabel vehicleColorLabel = new JLabel("Vehicle Color: ");
        JTextField vehicleColorField = new JTextField();
        JLabel vehiclePlateLabel = new JLabel("Vehicle Plate: ");
        JTextField vehiclePlateField = new JTextField();

        JButton registerButton = new JButton("Register Vehicle");

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
                String type = vehicleTypeField.getText();
                String brand = vehicleBrandField.getText();
                String color = vehicleColorField.getText();
                String plate = vehiclePlateField.getText();

                // Validate all fields filled
                if (type.isEmpty() || brand.isEmpty() || color.isEmpty() || plate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }
                //validate vehicle
                if (app.validateVehicleDriver(plate)) {
                    JOptionPane.showMessageDialog(null, "Vehicle already exists!");
                    return;
                }
                // Create a Vehicle object and assign it to the driver
                Vehicle vehicle = null;
                if(type.equals("Motorcycle")){
                    vehicle = new Motorcycle(plate,color,brand);
                } else{
                    vehicle = new Car(plate,color,brand);
                }
                driver.setVehicle(vehicle);

                // Show success message and go back to main menu
                app.addDriver(driver.getEmail(), driver.getName(), driver.getPassword(), driver.getPhoneNumber(), vehicle);
                app.addVehicle(driver.getEmail(),type,plate,color,brand);
                JOptionPane.showMessageDialog(null, "Driver registered successfully!");
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        return vehiclePanel;
    



    }
}
