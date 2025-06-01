package domain.user;

import domain.vehicle.*;
import domain.order.*;
import java.util.*;
import app.Application;

public class Driver extends User {
    private Order nowOrder;
    private ArrayList<Order> ordersHistory = new ArrayList<Order>();
    private Vehicle vehicle;
    private boolean isAvailable = true;
    private double rating = 0.0;
    private int orderCount = 0;
    private double balance = 0.0;

    public Driver(String email, String password, String name, String phone, Vehicle vehicle, Application app) {
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

    public Order getOrder() {
        return this.nowOrder;
    }

    public double getBalance() {
        return this.balance;
    };

    public boolean isHaveOrder() {
        return this.nowOrder != null;
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

    public boolean getAvailability() {
        return this.isAvailable;
    }

    public String getPhoneNumber() {
        return super.getPhone();
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public double getRating() {
        return this.rating;
    }

    public void setAvailability(boolean availability) {
        this.isAvailable = availability;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setOrder(Order order) {
        this.nowOrder = order;
    }

    public void setRating(double rating) {
        this.rating = (this.rating * this.orderCount + rating) / (this.orderCount + 1);
        this.orderCount++;
    }
}
