package App;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Interface.*;
import User.*;
import Vehicle.*;
import Order.*;

public class Menu {
    Application app;
    Scanner scanner = new Scanner(System.in);

    Menu(Application app) {
        this.app = app;
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

    

    // Menu Driver
    void menuDriver() {
        System.out.println("=== Login Driver ===");
        System.out.print("Masukkan ID Driver: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Driver driver = (Driver) app.findUserById(id, "Driver");

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
            System.out.println("3. Lihat Semua User");
            System.out.println("4. Logout");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    app.tambahCustomer();
                    break;
                case 2:
                    app.tambahDriver();
                    break;
                case 3:
                    app.lihatSemuaUser();
                    break;
                case 4:
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
        Customer customer = (Customer) app.findUserById(id, "Customer");

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
                    Order order = customer.newOrder();
                    if(order==null){
                        break;
                    }
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
}
