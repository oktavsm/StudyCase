package User;

import Order.Order;
import App.Application;
import java.util.*;

public class Customer extends User {
    private double balance;
    private Scanner in = new Scanner(System.in);
    private Order nowOrder;
    private boolean isOrdering = false;

    public Customer(String name, String email, String phone, double balance, Application app) {
        super(name, email, phone, app);
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

        System.out.print("Enter the distance: ");
        double distance = in.nextDouble();
        in.nextLine();

        nowOrder = new Order(this, driver, location, destination, distance);
        return nowOrder;
    }

    public void pay(Order order, double amount) {
        if(this.balance < amount){
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
        System.out.println("Payment Details");
        System.out.println("Customer: " + this.getName());
        System.out.println("Driver  : " + order.getDriver().getName());
        System.out.println("Amount  : " + order.getPayment());
    }

    @Override
    public void showProfile() {
        System.out.println("--- Customer Profile ---");
        System.out.println("ID     : " + super.getId());
        System.out.println("Name   : " + super.getName());
        System.out.println("Email  : " + super.getEmail());
        System.out.println("Phone  : " + super.getPhone());
        System.out.println("Balance: " + this.balance);
    }
}
