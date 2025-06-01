package domain.order;

import java.util.*;
import app.Application;
import domain.user.*;
import gui.common.ChatPanel;
import interfaces.*;
import javax.swing.*;
import java.awt.*;

public class Order implements Chat, Payment, Review {
    private ArrayList<String> chatHistory = new ArrayList<String>();
    Driver driver;
    Customer customer;
    String location;
    String destination;
    double distance;
    double rate;
    boolean donePayment = false;
    boolean isDrop = false;
    JPanel orderInfoPanel;
    String time;
    private ChatPanel chatUI;

    public void giveReview(double rating) {
        this.driver.giveReview(rating);
    }

    public void setRating(double rating) {
        this.driver.setRating(rating);
    }

    public Order(Customer customer, Driver driver, String location, String destination, double distance, String time) {
        this.driver = driver;
        this.customer = customer;
        this.location = location;
        this.destination = destination;
        this.distance = distance;
        this.rate = driver.getVehicle().calculateRate(distance);
        this.time = time;
        chargeCustommer(this.rate);
    }

    public void showChat(User current) {
        this.chatUI = new ChatPanel(this, current);
        this.chatUI.setVisible(true);
    }

    public void closeChat() {
        this.chatUI.setVisible(false);
        this.chatUI.dispose();
    }

    public String getPickupLocation() {
        return this.location;
    }

    public String getDestinationLocation() {
        return this.destination;
    }

    public double getDistance() {
        return this.distance;
    }

    public String getEstimationTime() {
        return this.time;
    }

    public void saveChat(User sender, String message) {
        String chat = sender.getName() + "###***###" + message;
        this.chatHistory.add(chat);
    }

    public ArrayList<String> getChat() {
        return this.chatHistory;
    }

    public void pay(double amount) {
        if (!donePayment) {
            this.driver.receivedPayment(amount);
            this.donePayment = true;
        }
    }

    public void chargeCustommer(double amount) {
        this.customer.pay(this, amount);
    }

    public void showPayment() {
        this.customer.showPayment(this);
    }

    public void dropOff() {
        this.isDrop = true;
        pay(rate);
    }

    public boolean isDrop() {
        return this.isDrop;
    }

    public void processOrder() {
        this.driver.takeOrder(this);
        JOptionPane.showMessageDialog(null, "Payment Success! \n" +
                "Rate: Rp" + this.rate + "\n" +
                "Driver: " + driver.getName() + "\n" +
                "Vehicle: " + driver.getVehicle().getType() + "\n" +
                "From: " + location + "\n" +
                "To: " + destination + "\n" +
                "Distance: " + distance + " KM\n" +
                "Estimation Time: " + time);
    }

    public Driver getDriver() {
        return this.driver;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getPayment() {
        return this.rate;
    }

    public void finishOrder() {
        this.customer.finishOrder();
        this.driver.finishOrder();
    }

    public Order getOrder() {
        return this;
    }

    public boolean getPaymentStatus() {
        return donePayment;
    }

    public JPanel getOrderInfoPanel() {
        return this.orderInfoPanel;
    }

    public void setOrderInfoPanel(JPanel orderInfoPanel) {
        this.orderInfoPanel = orderInfoPanel;
    }
}
