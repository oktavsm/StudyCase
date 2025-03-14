package User;

import Vehicle.*;
import Order.Order;
import java.util.*;
import App.Application;

public class Driver extends User {
    private Order nowOrder;
    private ArrayList<Order> ordersHistory = new ArrayList<Order>();
    private Vehicle vehicle;
    private boolean isAvailable = true;
    private double rating = 0.0;
    private int orderCount = 0;
    private double balance = 0.0;

    public Driver(String name, String email, String password, String phone, Vehicle vehicle, Application app) {
        super(name, email, password, phone, app);
        this.vehicle = vehicle;
    }

    public void receivedPayment(double amount) {
        this.balance += amount;
    }

    public void showBalance() {
        System.out.println("Balance: " + this.balance);
    }

    public void takeOrder(Order order) {
        this.nowOrder = order;
        this.isAvailable = false;
    }

    public void showOrder() {
        if (this.nowOrder == null) {
            System.out.println("No order");
        } else {
            this.nowOrder.showOrder();
        }
    }

    public Order getOrder() {
        return this.nowOrder;
    }

    public void giveReview(double rating) {
        this.rating = (this.rating * this.orderCount + rating) / (this.orderCount + 1);
    }

    public void finishOrder() {
        this.ordersHistory.add(this.nowOrder);
        this.nowOrder = null;
        this.isAvailable = true;
        this.orderCount++;
    }

    public Driver(String name, String email, String password, String phone, Vehicle vehicle, double rating, int orderCount, Application app) {
        super(name, email, password, phone, app);
        this.vehicle = vehicle;
        this.rating = rating;
        this.orderCount = orderCount;
    }

    public boolean getAvailability() {
        return this.isAvailable;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setAvailability(boolean availability) {
        this.isAvailable = availability;
    }

    public void showOrderHistory() {
        for(Order order: this.ordersHistory) {
            order.showOrder();
        }
    }

    @Override
    public void showProfile() {
        System.out.println("--- Driver Profile ---");
        System.out.println("ID          : " + super.getId());
        System.out.println("Name        : " + super.getName());
        System.out.println("Email       : " + super.getEmail());
        System.out.print("Password    :");
        String password = super.getPassword();
        for (char c : password.toCharArray()) {
            c = '*';
            System.out.print(c);
        }
        System.out.println();
        System.out.println("Phone       : " + super.getPhone());
        System.out.println("Vehicle   : ");
        this.vehicle.showVehicle();
        System.out.println("Availability: " + this.isAvailable);
        System.out.println("Rating      : " + this.rating);
        System.out.println("Order Count : " + this.orderCount);
        System.out.println("Balance     : " + this.balance);
    }
}
