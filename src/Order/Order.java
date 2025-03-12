package Order;
import User.*;
public class Order {
    Driver driver;
    Customer customer;
    String location;
    String destination;
    int distance;
    int rate;
    public Order(Customer customer,Driver driver,String location,String destination,int distance) {
        this.driver = driver;
        this.customer = customer;
        this.location = location;
        this.destination = destination;
        this.distance = distance;
        this.rate = driver.getVehicle().calculateRate(distance);
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
