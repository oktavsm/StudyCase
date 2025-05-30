package domain.vehicle;

public class Car extends Vehicle {
    public Car(String plateNumber, String color, String brand) {
        super(plateNumber, color, brand, 14000);
    }

    public String getType() {
        return "Car";
    }

    @Override
    public double calculateRate(double distance) {
        return baseRate * distance;
    }

    @Override
    public void showVehicle() {
        System.out.println("Vehicle Type: Car");
        System.out.println("Plate Number  : " + plateNumber);
        System.out.println("Color         : " + color);
        System.out.println("Brand         : " + brand);
        System.out.println("Base Rate     : " + baseRate);
    }
}
