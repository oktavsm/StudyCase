package User;

import Vehicle.Vehicle;
import Order.Order;
import java.util.ArrayList;
import App.Application;
public class Driver extends User {
    private ArrayList<Order> orders = new ArrayList<Order>();
    private Vehicle vehicle;
    private boolean isAvailable = true;
    private double rating = 0.0;
    private int orderCount = 0;

    public Driver(int id, String name, String email, String phone, Vehicle vehicle, Application app) {
        super(id, name, email, phone,app);
        this.vehicle = vehicle;
    }

    public Driver(int id, String name, String email, String phone, Vehicle vehicle, double rating, int orderCount,Application app) {
        super(id, name, email, phone,app);
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

    @Override
    public void showProfile() {
        System.out.println("--- Driver Profile ---");
        System.out.println("ID         : " + super.getId());
        System.out.println("Name       : " + super.getName());
        System.out.println("Email      : " + super.getEmail());
        System.out.println("Phone      : " + super.getPhone());
        System.out.println("Vehicle    : ");
        this.vehicle.showVehicle();
        System.out.println("Availability: " + this.isAvailable);
        System.out.println("Rating     : " + this.rating);
        System.out.println("Order Count: " + this.orderCount);
    }
}
