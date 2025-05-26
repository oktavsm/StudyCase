package domain.vehicle;

import java.io.*;

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
        writeToFile();
    }

    private void writeToFile() {
        String fileName = "src/database/driver/vehicle.txt";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getType()).append(",")
                .append(plateNumber).append(",")
                .append(color).append(",")
                .append(brand).append(",")
                .append(baseRate).append("\n");
        String data = new String(stringBuilder);
        try {
            FileWriter fileOutput = new FileWriter(fileName, true);
            fileOutput.write(data);
            fileOutput.close();
        } catch (IOException e) {
            System.exit(1);
        }
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