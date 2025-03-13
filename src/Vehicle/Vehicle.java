package Vehicle;

public abstract class Vehicle {
    String plateNumber;
    String color;
    String brand;
    double baseRate;

    Vehicle(String plateNumber, String color, String brand, double baseRate) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.brand = brand;
        this.baseRate = baseRate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public String getName() {
        return brand;
    }

    public abstract String getType();
    public abstract double calculateRate(double distance);
    public abstract void showVehicle();
}
