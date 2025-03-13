package Vehicle;

public class Motorcycle extends Vehicle {
    public Motorcycle(String plateNumber, String color, String brand, int baseRate) {
        super(plateNumber, color, brand, baseRate);
    }

    public String getType() {
        return "Motorcycle";
    }

    @Override
    public double calculateRate(double distance) {
        return baseRate * distance;
    }

    @Override
    public void showVehicle() {
        System.out.println("Vehicle Type: Motorcycle");
        System.out.println("Plate Number  : " + plateNumber);
        System.out.println("Color         : " + color);
        System.out.println("Brand         : " + brand);
        System.out.println("Base Rate     : " + baseRate);
    }
}
