package Order;
import User.*;
public class Order {
    Driver driver;
    Customer customer;
    String location;
    String destination;
    int distance;
    int rate;
    public Order(Driver driver, Customer customer) {
        this.driver = driver;
        this.customer = customer;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void showOrder() {
        System.out.println("Order Details");
        System.out.println("Customer: " + this.customer.getName());
        System.out.println("Driver  : " + this.driver.getName());
        System.out.println("From    : " + this.location);
        System.out.println("To      : " + this.destination);
        System.out.println("Distance: " + this.distance + " KM");
        System.out.println("Rate    : " + this.rate);
    }
}
