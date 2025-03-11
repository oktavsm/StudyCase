package User;

import Vehicle.Vehicle;

public class Driver extends User {
    private Vehicle vehicle;
    private boolean isAvailable = true;
    private double rating = 0.0;
    private int orderCount = 0;

    public Driver(int id, String name, String email, String phone, Vehicle vehicle) {
        super(id, name, email, phone);
        this.vehicle = vehicle;
    }

    public Driver(int id, String name, String email, String phone, Vehicle vehicle, double rating, int orderCount) {
        super(id, name, email, phone);
        this.vehicle = vehicle;
        this.rating = rating;
        this.orderCount = orderCount;
    }

    public boolean getAvailability() {
        return this.isAvailable;
    }

    @Override
    public void showProfile() {
        System.out.println("Your Profile");
        System.out.println("Name       : " + super.getName());
        System.out.println("Email      : " + super.getEmail());
        System.out.println("Phone      : " + super.getPhone());
        System.out.println("Vehicle    : " + this.vehicle);
        System.out.println("Rating     : " + this.rating);
        System.out.println("Order Count: " + this.orderCount);
    }
}
