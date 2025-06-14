package domain.user;

import domain.order.*;
import javax.swing.JOptionPane;
import app.Application;

public class Customer extends User {
    private double balance;
    private Order nowOrder;
    private boolean isOrdering = false;

    public Customer(String name, String email, String password, String phone, double balance, Application app) {
        super(name, email, password, phone, app);
        this.balance = balance;
    }

    public Order newOrder(String destination, String location, double distance, String time, String type) {
        if (isOrdering) {
            JOptionPane.showMessageDialog(null, "You already have an order in progress.");
            return null;
        }

        Driver driver = app.findAvailableDriver(type);
        if (driver == null) {
            JOptionPane.showMessageDialog(null, "No available driver found.");
            return null;
        }

        isOrdering = true;

        nowOrder = new Order(this, driver, location, destination, distance, time);
        if (nowOrder.getPayment() > this.balance) {
            JOptionPane.showMessageDialog(null, "Insufficient balance for this order.");
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

    public boolean isOrdering() {
        return this.isOrdering;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public void finishOrder() {
        isOrdering = false;
        nowOrder = null;
    }

    public void showPayment(Order order) {
        System.out.println("--- Payment Details ---");
        System.out.println("Customer: " + this.getName());
        System.out.println("Driver  : " + order.getDriver().getName());
        System.out.println("Amount  : " + order.getPayment());
    }
}
