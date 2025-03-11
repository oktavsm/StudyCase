package Vehicle;

public class Car extends Vehicle {
      public Car(String plateNumber, String color, String brand, int baseRate) {
         super(plateNumber, color, brand, baseRate);
      }
      @Override
      public int calculateRate(int distance) {
         return baseRate * distance;
      }
    
}
