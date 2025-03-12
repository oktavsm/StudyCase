import java.util.ArrayList;
import java.util.Scanner;

import Order.Order;
import User.*;
import Vehicle.*;

public class Application {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu(this);

    void showMenu(){
        menu.mainMenu();
    }

    // Tambah Customer
    void tambahCustomer() {
        System.out.print("Masukkan ID Customer: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan Nama Customer: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Email Customer: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Nomor HP: ");
        String nomorHP = scanner.nextLine();
        users.add(new Customer(id, nama, email, nomorHP, 100000));
        System.out.println("Customer berhasil ditambahkan!");
    }

    // Tambah Driver
    void tambahDriver() {
        System.out.print("Masukkan Nama Driver: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan ID Driver: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Nomor HP: ");
        String nomorHP = scanner.nextLine();
        Vehicle kendaraan = tambahKendaraan();
        users.add(new Driver(id,nama,email, nomorHP, kendaraan));
        System.out.println("Driver berhasil ditambahkan!");
    }

    // Tambah Kendaraan
    Vehicle tambahKendaraan() {
        System.out.print("Masukkan Jenis Kendaraan (Motor/Mobil): ");
        String jenis = scanner.nextLine();
        System.out.print("Masukkan Plat Nomor: ");
        String platNomor = scanner.nextLine();
        System.out.print("Masukkan Warna: ");
        String warna = scanner.nextLine();
        System.out.print("Masukkan Merk: ");
        String brand = scanner.nextLine();

        if (jenis.equalsIgnoreCase("Motor")) {
            vehicles.add(new Motocycle(platNomor,warna,brand,4000));
            return new Motocycle(platNomor,warna,brand,4000);
        } else if (jenis.equalsIgnoreCase("Mobil")) {
            vehicles.add(new Car(platNomor,warna,brand,8000));
            return new Car(platNomor,warna,brand,8000);
        } else {
            System.out.println("Jenis kendaraan tidak valid.");
            return null;
        }
    }

    // Lihat Semua User
    void lihatSemuaUser() {
        System.out.println("=== List Customer ===");
        for (User user : users) {
            if(user instanceof Customer) {
                user.showProfile();
            } 
        }
        System.out.println("=== List Driver ===");
        for (User user : users) {
            if(user instanceof Driver) {
                user.showProfile();
            } 
        }
    }

 

    // Mencari User berdasarkan ID
    User findUserById(int id, String type) {
        for (User user : users) {
            if (user.getId() == id && user.getClass().getSimpleName().equals(type)) {
                return user;
            }
        }
        return null;
    }
}
