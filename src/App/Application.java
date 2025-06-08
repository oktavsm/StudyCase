package app;

import java.util.*;

import domain.order.*;
import domain.user.*;
import domain.vehicle.*;
import gui.app.AppGUI;
import interfaces.Topup;
import java.io.*;
import utils.HashPassword;

public class Application implements Topup {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    FileReader fileReader;
    BufferedReader bufferedReader;
    FileWriter fileWriter;
    BufferedWriter BufferedWriter;

    public void showMenu() throws IOException {
        AppGUI appGUI = new AppGUI(this);
        appGUI.setVisible(true);
    }

    public void addCustomer(String email, String name, String password, String phoneNumber) {
        if (validateEmailCustomer(email)) {
            System.out.println("Register failed, email already registered");
        } else {
            try {
                String hashedPassword = HashPassword.hashPassword(password);
                fileWriter = new FileWriter("database/customer/customer.txt", true);
                BufferedWriter = new BufferedWriter(fileWriter);
                BufferedWriter.write(name + "," + email + "," + hashedPassword + "," + phoneNumber + "," + 0);
                BufferedWriter.newLine();
                BufferedWriter.close();
                users.add(new Customer(name, email, hashedPassword, phoneNumber, 0, this));
                System.out.println("Register successful");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    public void addDriver(String email, String password, String name, String phoneNumber, Vehicle vehicle) {
        String hashedPassword = HashPassword.hashPassword(password);
        try {
            fileWriter = new FileWriter("database/driver/driver.txt", true);
            BufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter.write(email + "," + hashedPassword + "," + name + "," + phoneNumber);
            BufferedWriter.newLine();
            BufferedWriter.close();
            users.add(new Driver(email, hashedPassword, name, phoneNumber, vehicle, this));
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }

    public Vehicle addVehicle(String email, String type, String plateNumber, String color, String brand) {
        try {
            fileWriter = new FileWriter("database/driver/vehicle.txt", true);
            BufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter.write(email + "," + type + "," + plateNumber + "," + color + "," + brand);
            BufferedWriter.newLine();
            BufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (type.equalsIgnoreCase("Motorcycle")) {
            Vehicle vehicle = new Motorcycle(plateNumber, color, brand);
            vehicles.add(vehicle);
            return vehicle;
        } else if (type.equalsIgnoreCase("Car")) {
            Vehicle vehicle = new Car(plateNumber, color, brand);
            vehicles.add(vehicle);
            return vehicle;
        } else {
            System.out.println("Invalid vehicle type");
            return null;
        }
    }

    public boolean validateEmailCustomer(String email) {
        boolean isExist = false;
        try {
            FileReader fileInput = new FileReader("database/customer/customer.txt");
            BufferedReader bufferInput = new BufferedReader(fileInput);

            String data = bufferInput.readLine();

            while (data != null && !data.isEmpty()) {
                String check[] = data.split(",");
                if (check[1].equals(email)) {
                    isExist = true;
                    break;
                }
                data = bufferInput.readLine();
            }

            bufferInput.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return isExist;
    }

    public boolean validateEmailDriver(String email) {
        boolean isExist = false;
        try {
            FileReader fileInput = new FileReader("database/driver/driver.txt");
            BufferedReader bufferInput = new BufferedReader(fileInput);

            String data = bufferInput.readLine();

            while (data != null && !data.isEmpty()) {
                String check[] = data.split(",");
                if (check[1].equals(email)) {
                    isExist = true;
                    break;
                }
                data = bufferInput.readLine();
            }

            bufferInput.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return isExist;
    }

    public boolean validateVehicleDriver(String plateNumber) {
        boolean isExist = false;
        try {
            FileReader fileInput = new FileReader("database/driver/vehicle.txt");
            BufferedReader bufferInput = new BufferedReader(fileInput);

            String data = bufferInput.readLine();

            while (data != null && !data.isEmpty()) {
                String check[] = data.split(",");
                if (check[2].equals(plateNumber)) {
                    isExist = true;
                    break;
                }
                data = bufferInput.readLine();
            }

            bufferInput.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return isExist;
    }

    public void loadDatabase() {
        try {
            loadCustomers();
            loadDriver();
        } catch (IOException e) {
            System.out.println("Error loading database: " + e.getMessage());
        }
    }

    public void loadCustomers() throws IOException {
        fileReader = new FileReader("database/customer/customer.txt");
        bufferedReader = new BufferedReader(fileReader);
        String data = bufferedReader.readLine();

        while (data != null) {
            String[] check = data.split(",");
            users.add(new Customer(check[0], check[1], check[2], check[3], Double.parseDouble(check[4]), this));
            data = bufferedReader.readLine();
        }

        bufferedReader.close();
    }

    public void loadDriver() throws IOException {
        fileReader = new FileReader("database/driver/driver.txt");
        bufferedReader = new BufferedReader(fileReader);
        String data = bufferedReader.readLine();
        while (data != null && !data.isEmpty()) {
            String[] check = data.split(",");
            fileReader = new FileReader("database/driver/vehicle.txt");
            BufferedReader bufferedReader2 = new BufferedReader(fileReader);
            String data2 = bufferedReader2.readLine();
            while (data2 != null && !data2.isEmpty()) {
                String[] check2 = data2.split(",");
                if (check[0].equals(check2[0])) {
                    Vehicle vehicle;
                    if (check2[1].equals("Motorcycle")) {
                        vehicle = new Motorcycle(check2[2], check2[3], check2[4]);
                    } else {
                        vehicle = new Car(check2[2], check2[3], check2[4]);
                    }

                    users.add(new Driver(check[0], check[1], check[2], check[3], vehicle, this));
                }
                data2 = bufferedReader2.readLine();
            }
            bufferedReader2.close();
            data = bufferedReader.readLine();
        }

        bufferedReader.close();
    }

    public String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    public User validateEmailAndPassword(String email, String password) {

        String hashedInput = HashPassword.hashPassword(password);
        for (User user : users) {
            System.out.println(
                    email + " " + user.getEmail() + " " + password + " " + user.getPassword() + " " + hashedInput);
            if (user.getEmail().equals(email) && user.getPassword().equals(hashedInput)) {
                return user;
            }
        }
        return null;
    }

    public Driver findAvailableDriver(String type) {
        for (User user : users) {
            if (user instanceof Driver) {
                Driver driver = (Driver) user;
                if (driver.getAvailability() && driver.getVehicle().getType().equals(type)) {
                    return driver;
                }
            }
        }
        return null;
    }

    public void topupBalance(double amount, Customer customer) {
        customer.setBalance(customer.getBalance() + amount);

        try {
            File inputFile = new File("database/customer/customer.txt");
            File tempFile = new File("database/customer/temp_customer.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(customer.getEmail())) {
                    line = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + customer.getBalance();
                }
                writer.write(line);
                writer.newLine();
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            }

        } catch (IOException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

    public String createPayment(int choice, Customer customer, int amount) {

        String virtualAccount;
        if (choice == 1) {
            virtualAccount = "114" + customer.getPhone() + amount;
        } else {
            virtualAccount = "002" + customer.getPhone() + amount;
        }

        return virtualAccount;
    }
}
