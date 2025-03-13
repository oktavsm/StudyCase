package App;

import java.util.*;
import Interface.Topup;
import Order.Order;
import User.*;
import Vehicle.*;

public class Application implements Topup {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    Menu menu = new Menu(this);

    public void showMenu(){
        menu.mainMenu();
    }

    public void addCustomer() {
        System.out.print("Enter name         : ");
        String name = in.nextLine();
        System.out.print("Enter email        : ");
        String email = in.nextLine();
        System.out.print("Enter phone number : ");
        String phoneNumber = in.nextLine();
        users.add(new Customer(name, email, phoneNumber, 0, this));
        System.out.println("Register successful");
    }

    public void addDriver() {
        System.out.print("Enter name         : ");
        String name = in.nextLine();
        System.out.print("Enter email        : ");
        String email = in.nextLine();
        System.out.print("Enter phone number : ");
        String phoneNumber = in.nextLine();
        Vehicle vehicle = addVehicle();
        if (vehicle == null) {
            System.out.println("Register failed");
            return;
        }

        users.add(new Driver(name, email, phoneNumber, vehicle, this));
        System.out.println("Register successful");
    }

    public Vehicle addVehicle() {
        System.out.print("Enter vehicle type (Motorcycle/Car): ");
        String type = in.nextLine();
        if (!type.equalsIgnoreCase("Motorcycle") && !type.equalsIgnoreCase("Car")) {
            return null;
        }

        System.out.print("Enter plate number                 : ");
        String plateNumber = in.nextLine();
        System.out.print("Enter color                        : ");
        String color = in.nextLine();
        System.out.print("Enter brand                        : ");
        String brand = in.nextLine();

        if (type.equalsIgnoreCase("Motorcycle")) {
            Vehicle vehicle = new Motorcycle(plateNumber, color, brand, 4000);
            vehicles.add(vehicle);
            return vehicle;
        } else if (type.equalsIgnoreCase("Car")) {
            Vehicle vehicle = new Car(plateNumber, color, brand, 8000);
            vehicles.add(vehicle);
            return vehicle;
        } else {
            System.out.println("Invalid vehicle type");
            return null;
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

    public Driver findAvailableDriver(String type) {
        for (User user : users) {
            if (user instanceof Driver ) {
                Driver driver = (Driver) user;
                if (driver.getAvailability() && driver.getVehicle().getType().equals(type)) {
                    return driver;
                }
            }
        }
        return null;
    }

    public void registerCustomer() {
        addCustomer();
    }

    public void registerDriver() {
        addDriver();
    }

    public String topupBalance(Customer customer) {
        double balance = customer.getBalance();
        boolean status = true;

        System.out.println("Choose payment method");
        System.out.println("1. Bank Jatim");
        System.out.println("2. Bank BRI");
        System.out.print("Choose: " );
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
        } else {
            String virtualAccount = "002" + customer.getPhone();
            System.out.print("Transfer to following virtual account: " + virtualAccount);
            System.out.print("\n Enter topup amount: ");
            amount = in.nextDouble();
        }

        return amount;
    }
}
