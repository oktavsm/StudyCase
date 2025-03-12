import java.util.ArrayList;
import java.util.Scanner;
import User.*;
import Vehicle.*;

public class Application {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application app = new Application();
        app.mainMenu();
    }

    void mainMenu() {
        while (true) {
            System.out.println("\n=== Transportasi Online ===");
            System.out.println("1. Login sebagai Customer");
            System.out.println("2. Login sebagai Driver");
            System.out.println("3. Login sebagai Admin");
            System.out.println("4. Keluar Aplikasi");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    menuCustomer();
                    break;
                case 2:
                    menuDriver();
                    break;
                case 3:
                    menuAdmin();
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan aplikasi!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Menu Customer
    void menuCustomer() {
        System.out.println("=== Login Customer ===");
        System.out.print("Masukkan ID Customer: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Customer customer = (Customer) findUserById(id, "Customer");

        if (customer == null) {
            System.out.println("Customer tidak ditemukan.");
            return;
        }

        while (true) {
            System.out.println("\n=== Menu Customer ===");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Pesan Kendaraan");
            System.out.println("3. Chat dengan Driver");
            System.out.println("4. Beri Rating ke Driver");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    customer.showProfile();
                    break;
                case 2:
                    pesanKendaraan(customer);
                    break;
                case 3:
                    System.out.println("Fitur chat belum tersedia.");
                    break;
                case 4:
                    System.out.println("Fitur rating belum tersedia.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Menu Driver
    void menuDriver() {
        System.out.println("=== Login Driver ===");
        System.out.print("Masukkan ID Driver: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Driver driver = (Driver) findUserById(id, "Driver");

        if (driver == null) {
            System.out.println("Driver tidak ditemukan.");
            return;
        }

        while (true) {
            System.out.println("\n=== Menu Driver ===");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Cek Pesanan Masuk");
            System.out.println("3. Chat dengan Customer");
            System.out.println("4. Logout");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    driver.showProfile();
                    break;
                case 2:
                    System.out.println("Fitur cek pesanan belum tersedia.");
                    break;
                case 3:
                    System.out.println("Fitur chat belum tersedia.");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Menu Admin
    void menuAdmin() {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Customer");
            System.out.println("2. Tambah Driver");
            System.out.println("3. Tambah Kendaraan");
            System.out.println("4. Lihat Semua User");
            System.out.println("5. Lihat Semua Kendaraan");
            System.out.println("6. Logout");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahCustomer();
                    break;
                case 2:
                    tambahDriver();
                    break;
                case 3:
                    tambahKendaraan();
                    break;
                case 4:
                    lihatSemuaUser();
                    break;
                case 5:
                    lihatSemuaKendaraan();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
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
        for (User user : users) {
            user.showProfile();
        }
    }

    // Lihat Semua Kendaraan
    void lihatSemuaKendaraan() {
        for (Vehicle vehicle : vehicles) {
            vehicle.showVehicle();
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
