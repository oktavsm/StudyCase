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
    Menu menu = new Menu(this);
    FileReader fileReader;
    BufferedReader bufferedReader;
    FileWriter fileWriter;
    BufferedWriter BufferedWriter;

    public void showMenu() throws IOException {
        AppGUI appGUI = new AppGUI(this);
        appGUI.setVisible(true);
        // menu.mainMenu();
    }

    public void addCustomer(String email, String name, String password, String phoneNumber) {

        // check if the customer exst
        if (validateEmailCustomer(email)) {
            // register failed, email already registered
            System.out.println("Register failed, email already registered");
        } else {
            // add customer data to "src/Database/Customer.txt"
            try {
                fileWriter = new FileWriter("src/Database/Customer.txt", true);
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
            FileReader fileInput = new FileReader("src/Database/Customer.txt");
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
        fileReader = new FileReader("src/Database/Customer.txt");
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
        // from "src/Database/Driver/Driver.txt and the vehicle on
        // src/Database/Driver/Vehicle.txt"
        // driver : email,password,name,phoneNumber
        // vehicle : email,type,plateNumber,color,brand
        fileReader = new FileReader("src/Database/Driver/Driver.txt");
        bufferedReader = new BufferedReader(fileReader);
        String data = bufferedReader.readLine();
        while (data != null && !data.isEmpty()) {
            String[] check = data.split(",");
            // load vehicle
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

        // add driver data to "src/Database/Driver.txt"
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
        // write to database
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

    public void registerCustomer() throws IOException {
        // addCustomer();
    }

    public void registerDriver() {
        // addDriver();
    }

    public String topupBalance(Customer customer) {
        double balance = customer.getBalance();
        boolean status = true;

        System.out.println("Choose payment method");
        System.out.println("1. Bank Jatim");
        System.out.println("2. Bank BRI");
        System.out.print("Choose: ");
        int choice = in.nextInt();
        in.nextLine();
        double amount = createPayment(choice, customer);

        customer.setBalance(balance += amount);

        if (choice != 1 && choice != 2) {
            status = false;
        }

        return (status ? "Topup successful" : "Topup failed");
    }

    private double createPayment(int choice, Customer customer) {
        double amount = 0;

        if (choice == 1) {
            String virtualAccount = "114" + customer.getPhone();
            System.out.print("Transfer to following virtual account: " + virtualAccount);
            System.out.print("\nEnter topup amount: ");
            amount = in.nextDouble();
            in.nextLine();
        } else {
            String virtualAccount = "002" + customer.getPhone();
            System.out.print("Transfer to following virtual account: " + virtualAccount);
            System.out.print("\n Enter topup amount: ");
            amount = in.nextDouble();
            in.nextLine();
        }

        return amount;
    }
}
