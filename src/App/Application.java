package App;

import java.util.*;
import Interface.Topup;
import Order.Order;
import User.*;
import Vehicle.*;

public class Application implements Topup {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    Menu menu = new Menu(this);

    public void showMenu(){
        menu.mainMenu();
    }

    public void tambahCustomer() {
        System.out.print("Masukkan Nama Customer: ");
        String nama = in.nextLine();
        System.out.print("Masukkan Email Customer: ");
        String email = in.nextLine();
        System.out.print("Masukkan Nomor HP: ");
        String nomorHP = in.nextLine();
        users.add(new Customer(nama, email, nomorHP, 0, this));
        System.out.println("Register berhasil!");
    }

    public void tambahDriver() {
        System.out.print("Masukkan Nama Driver: ");
        String nama = in.nextLine();
        System.out.print("Masukkan Email: ");
        String email = in.nextLine();
        System.out.print("Masukkan Nomor HP: ");
        String nomorHP = in.nextLine();
        Vehicle kendaraan = tambahKendaraan();
        users.add(new Driver(nama, email, nomorHP, kendaraan, this));
        System.out.println("Register berhasil!");
    }

    public Vehicle tambahKendaraan() {
        System.out.print("Masukkan Jenis Kendaraan (Motor/Mobil): ");
        String jenis = in.nextLine();
        System.out.print("Masukkan Plat Nomor: ");
        String platNomor = in.nextLine();
        System.out.print("Masukkan Warna: ");
        String warna = in.nextLine();
        System.out.print("Masukkan Merk: ");
        String brand = in.nextLine();

        if (jenis.equalsIgnoreCase("Motor")) {
            vehicles.add(new Motorcycle(platNomor, warna, brand, 4000));
            return new Motorcycle(platNomor, warna, brand, 4000);
        } else if (jenis.equalsIgnoreCase("Mobil")) {
            vehicles.add(new Car(platNomor, warna, brand, 8000));
            return new Car(platNomor, warna, brand, 8000);
        } else {
            System.out.println("Jenis kendaraan tidak valid.");
            return null;
        }
    }

    public void lihatSemuaUser() {
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

    public String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    public User findUserByEmail(String email, String type) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getClass().getSimpleName().equals(type)) {
                return user;
            }
        }
        return null;
    }

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

    public void registerCustomer() {
        tambahCustomer();
    }

    public void registerDriver() {
        tambahDriver();
    }

    public String topupBalance(Customer customer) {
        double balance = customer.getBalance();
        boolean status = true;

        System.out.println("Pilih metode pembayaran");
        System.out.println("1. Bank Jatim");
        System.out.println("2. Bank BRI");
        System.out.print("Pilih: " );
        int choice = in.nextInt();
        in.nextLine();
        double amount = createPayment(choice, customer);

        customer.setBalance(balance += amount);

        if (choice != 1 && choice != 2) {
            status = false;
        }

        return (status ? "Topup saldo berhasil" : "Topup gagal"); 
    }

    private double createPayment(int choice, Customer customer) {
        double amount = 0;

        if (choice == 1) {
            String virtualAccount = "114" + customer.getPhone();
            System.out.print("Transfer ke nomor virtual account berikut: " + virtualAccount);
            System.out.print("\nMasukkan nominal topup: ");
            amount = in.nextDouble();
        } else {
            String virtualAccount = "002" + customer.getPhone();
            System.out.print("Transfer ke nomor virtual account berikut: " + virtualAccount);
            System.out.print("\nMasukkan nominal topup: ");
            amount = in.nextDouble();
        }

        return amount;
    }
}
