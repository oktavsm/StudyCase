package App;

import java.util.*;
import Interface.Topup;
import Order.Order;
import User.*;
import Vehicle.*;
import java.io.*;
import GUI.*;

public class Application implements Topup {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Scanner in = new Scanner(System.in);
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
                fileWriter = new FileWriter("src/Database/Customer/Customer.txt", true);
                BufferedWriter = new BufferedWriter(fileWriter);
                BufferedWriter.write(name + "," + email + "," + password + "," + phoneNumber);
                BufferedWriter.newLine();
                BufferedWriter.close();
                users.add(new Customer(name, email, password, phoneNumber, 0, this));
                System.out.println("Register successful");
            } catch (IOException e) {

            }
        }
    }

    public boolean validateEmailCustomer(String email) {
        boolean isExist = false;
        try {
            FileReader fileInput = new FileReader("src/Database/Customer/Customer.txt");
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

        }
        return isExist;

    }

    public boolean validateEmailDriver(String email) {
        boolean isExist = false;
        try {
            FileReader fileInput = new FileReader("src/Database/Driver/Driver.txt");
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

        }
        return isExist;

    }

    public boolean validateVehicleDriver(String plateNumber) {
        boolean isExist = false;
        try {
            FileReader fileInput = new FileReader("src/Database/Driver/Vehicle.txt");
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
        fileReader = new FileReader("src/Database/Customer/Customer.txt");
        bufferedReader = new BufferedReader(fileReader);
        String data = bufferedReader.readLine();

        while (data != null) {
            String[] check = data.split(",");
            users.add(new Customer(check[0], check[1], check[2], check[3], 0, this));
            data = bufferedReader.readLine();
        }
        bufferedReader.close();

    }

    public void loadDriver() throws IOException {
        fileReader = new FileReader("src/Database/Driver/Driver.txt");
        bufferedReader = new BufferedReader(fileReader);
        String data = bufferedReader.readLine();
        while (data != null && !data.isEmpty()) {
            String[] check = data.split(",");
            fileReader = new FileReader("src/Database/Driver/Vehicle.txt");
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

    public void addDriver(String email, String password, String name, String phoneNumber, Vehicle vehicle) {
        try {
            fileWriter = new FileWriter("src/Database/Driver/Driver.txt", true);
            BufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter.write(email + "," + password + "," + name + "," + phoneNumber);
            BufferedWriter.newLine();
            BufferedWriter.close();
        } catch (IOException e) {
        }

        users.add(new Driver(email, password, name, phoneNumber, vehicle, this));
    }

    public Vehicle addVehicle(String email, String type, String plateNumber, String color, String brand) {
        try {
            fileWriter = new FileWriter("src/Database/Driver/Vehicle.txt", true);
            BufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter.write(email + "," + type + "," + plateNumber + "," + color + "," + brand);
            BufferedWriter.newLine();
            BufferedWriter.close();
        } catch (IOException e) {
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

    public void removeCustomer() {
        System.out.print("Enter customer email: ");
        String email = in.nextLine();

        User toRemove = findUserByEmail(email, "Customer");
        if (toRemove != null) {
            users.remove(toRemove);
            System.out.println("Customer removed successfully");
        } else {
            System.out.println("Customer not found");
        }
    }

    public void removeDriver() {
        System.out.print("Enter driver email: ");
        String email = in.nextLine();

        User toRemove = findUserByEmail(email, "Driver");
        if (toRemove != null) {
            Vehicle vehicle = ((Driver) toRemove).getVehicle();
            vehicles.remove(vehicle);
            users.remove(toRemove);
            System.out.println("Driver removed successfully");
        } else {
            System.out.println("Driver not found");
        }
    }

    public void showAllUsers() {
        System.out.println("=== Customer List ===");
        for (User user : users) {
            if (user instanceof Customer) {
                user.showProfile();
            }
        }
        System.out.println("=== Driver List ===");
        for (User user : users) {
            if (user instanceof Driver) {
                user.showProfile();
            }
        }
    }

    public String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    public User findUserByEmail(String email, String type) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getClass().getSimpleName().equals(type)) {
                return user;
            }
        }
        return null;
    }

    public User validateEmailAndPassword(String email, String password, String type) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)
                    && user.getClass().getSimpleName().equals(type)) {
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
