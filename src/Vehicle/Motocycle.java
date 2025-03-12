package Vehicle;

public class Motocycle extends Vehicle {

    public Motocycle(String plateNumber, String color, String brand, int baseRate) {
        super(plateNumber, color, brand, baseRate);
    }
    public String getType(){
        return "Motocycle";
    }
    @Override
    public int calculateRate(int distance) {
        return baseRate * distance;
    }
    @Override
    public void showVehicle() {
        System.out.println("Vehicle Type: Motocycle");
        System.out.println("Plate Number: " + plateNumber);
        System.out.println("Color       : " + color);
        System.out.println("Brand       : " + brand);
        System.out.println("Base Rate   : " + baseRate);
    }

}
