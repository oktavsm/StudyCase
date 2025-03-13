package Order;

import java.util.*;
import Interface.*;
import User.*;

public class Order implements Chat, Payment, Review {
    private ArrayList<String> chatHistory = new ArrayList<String>();
    Driver driver;
    Customer customer;
    String location;
    String destination;
    double distance;
    double rate;
    boolean donePayment = false;

    public void giveReview(double rating) {
        this.driver.giveReview(rating);
    }

    public Order(Customer customer, Driver driver, String location, String destination, double distance) {
        this.driver = driver;
        this.customer = customer;
        this.location = location;
        this.destination = destination;
        this.distance = distance;
        this.rate = driver.getVehicle().calculateRate(distance);
    }

    public void sendChat(User sender, String message) {
        String chat = sender.getName() + ": " + message;
        this.chatHistory.add(chat);
    }

    public void showChat() {
        System.out.println("Chat History");
        for(String chat: this.chatHistory){
            System.out.println(chat);
        }
    }

    public void pay(double amount) {
        if(!donePayment) {
            this.customer.pay(this, amount);
            this.driver.receivedPayment(amount);
            this.donePayment = true;
        }
    }

    public void showPayment() {
        this.customer.showPayment(this);
    }

    public void processOrder() {
        this.driver.takeOrder(this);
    }

    public Driver getDriver() {
        return this.driver;
    }

    public double getPayment() {
        return this.rate;
    }

    public void finishOrder() {
        this.customer.finishOrder();
        this.driver.finishOrder();
    }

    public boolean getPaymentStatus() {
        return donePayment;
    }

    public void showOrder() {
        System.out.println("Order Details");
        System.out.println("Customer: " + this.customer.getName());
        System.out.println("Driver  : " + this.driver.getName());
        System.out.println("Vehicle : " + this.driver.getVehicle().getName());
        System.out.println("From    : " + this.location);
        System.out.println("To      : " + this.destination);
        System.out.println("Distance: " + this.distance + " KM");
        System.out.println("Rate    : " + this.rate);
        String donePay = (getPaymentStatus()) ? "Done" : "Not Yet";
        System.out.println("Payment : " + donePay);

        if(this.donePayment) {
            System.out.println("Review  : " + this.rate);
        }
    }
}
