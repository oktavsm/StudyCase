package User;

import Order.Order;
import App.Application;
import java.util.*;

public class Customer extends User {
    private double balance;
    private Order nowOrder;
    private boolean isOrdering = false;

    private Scanner in = new Scanner(System.in);

    public Customer(String name, String email, String password, String phone, double balance, Application app) {
        super(name, email, password, phone, app);
        this.balance = balance;
    }

    public Order newOrder() {
        if (isOrdering) {
            System.out.println("You still have an active order");
            return null;
        }

        System.out.print("Choose your vehicle type (1. Motorcycle, 2. Car): ");
        int choice = in.nextInt();
        in.nextLine();

        String type = (choice == 1) ? "Motorcycle" : "Car";

        Driver driver = app.findAvailableDriver(type);
        if(driver == null){
            System.out.println("No driver available at the moment");
            return null;
        }

        isOrdering = true;
        System.out.print("Enter your location: ");
        String location = in.nextLine();

        System.out.print("Enter your destination: ");
        String destination = in.nextLine();

        System.out.print("Enter the distance (km): ");
        double distance = in.nextDouble();
        in.nextLine();

        nowOrder = new Order(this, driver, location, destination, distance);
        if (nowOrder.getPayment() > this.balance) {
            System.out.println("Insufficient balance");
            nowOrder = null;
            isOrdering = false;
            return null;
        }
        return nowOrder;
    }

    public void pay(Order order, double amount) {
        if (this.balance < amount) {
            System.out.println("Insufficient balance");
            return;
        }

        this.balance -= amount;
    }

    public Order getOrder() {
        return this.nowOrder;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public void finishOrder() {
        this.nowOrder = null;
        isOrdering = false;
    }

    public void showPayment(Order order) {
        System.out.println("--- Payment Details ---");
        System.out.println("Customer: " + this.getName());
        System.out.println("Driver  : " + order.getDriver().getName());
        System.out.println("Amount  : " + order.getPayment());
    }

    @Override
    public void showProfile() {
        System.out.println("--- Customer Profile ---");
        System.out.println("ID       : " + super.getId());
        System.out.println("Name     : " + super.getName());
        System.out.println("Email    : " + super.getEmail());
        System.out.println("Password : " + super.getPassword());
        String password = super.getPassword();
        // for (char c : password.toCharArray()) {
        //     c = '*';
        //     System.out.print(c);
        // }
        System.out.println();
        System.out.println("Phone    : " + super.getPhone());
        System.out.println("Balance  : " + this.balance);
    }
}
