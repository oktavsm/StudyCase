package App;
import java.util.ArrayList;
import java.util.Scanner;
import Interface.*;
import Order.Order;
import User.*;
import Vehicle.*;

public class Application{
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    Menu menu = new Menu(this);

    public void showMenu(){
        menu.mainMenu();
    }

    void tambahCustomer() {
        System.out.print("Masukkan ID Customer: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.print("Masukkan Nama Customer: ");
        String nama = in.nextLine();
        System.out.print("Masukkan Email Customer: ");
        String email = in.nextLine();
        System.out.print("Masukkan Nomor HP: ");
        String nomorHP = in.nextLine();
        users.add(new Customer(id, nama, email, nomorHP, 100000, this));
        System.out.println("Customer berhasil ditambahkan!");
    }

    void tambahDriver() {
        System.out.print("Masukkan Nama Driver: ");
        String nama = in.nextLine();
        System.out.print("Masukkan ID Driver: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.print("Masukkan Email: ");
        String email = in.nextLine();
        System.out.print("Masukkan Nomor HP: ");
        String nomorHP = in.nextLine();
        Vehicle kendaraan = tambahKendaraan();
        users.add(new Driver(id, nama, email, nomorHP, kendaraan, this));
        System.out.println("Driver berhasil ditambahkan!");
    }

    Vehicle tambahKendaraan() {
        System.out.print("Masukkan Jenis Kendaraan (Motor/Mobil): ");
        String jenis = in.nextLine();
        System.out.print("Masukkan Plat Nomor: ");
        String platNomor = in.nextLine();
        System.out.print("Masukkan Warna: ");
        String warna = in.nextLine();
        System.out.print("Masukkan Merk: ");
        String brand = in.nextLine();

        if (jenis.equalsIgnoreCase("Motor")) {
            vehicles.add(new Motocycle(platNomor, warna, brand, 4000));
            return new Motocycle(platNomor, warna, brand, 4000);
        } else if (jenis.equalsIgnoreCase("Mobil")) {
            vehicles.add(new Car(platNomor, warna, brand, 8000));
            return new Car(platNomor, warna, brand, 8000);
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

    //Search available driver
    public Driver findAvailableDriver(String type) {
        for (User user : users) {
            if (user instanceof Driver ) {
                Driver driver = (Driver) user;
                if (driver.getAvailability() && driver.getVehicle().getType().equals(type)) {
                    return driver;
                }
            }
        }
        return null;
    }
}
