package Vehicle;

public abstract class Vehicle {
    String plateNumber;
    String color;
    String brand;
    int baseRate;

    Vehicle(String plateNumber, String color, String brand, int baseRate) {
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

    public int getBaseRate() {
        return baseRate;
    }
    public String getName(){
        return brand;
    }
    public abstract String getType();
    public abstract int calculateRate(int distance);
    public abstract void showVehicle();
}
