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
    Scanner in = new Scanner(System.in);

    Menu(Application app) {
        this.app = app;
    }

    void mainMenu() {
        while (true) {
            System.out.println("\n=== Transportasi Online ===");
            System.out.println("1. Register sebagai Customer");
            System.out.println("2. Register sebagai Driver");
            System.out.println("3. Login sebagai Customer");
            System.out.println("4. Login sebagai Driver");
            System.out.println("5. Login sebagai Admin");
            System.out.println("6. Keluar Aplikasi");
            System.out.print("Pilih: ");
            int pilihan = in.nextInt();
            in.nextLine();

            switch (pilihan) {
                case 1:
                    app.registerCustomer();
                    break;
                case 2:
                    app.registerDriver();
                    break;
                case 3:
                    menuCustomer();
                    break;
                case 4:
                    menuDriver();
                    break;
                case 5:
                    menuAdmin();
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan aplikasi!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    void menuAdmin() {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Customer");
            System.out.println("2. Tambah Driver");
            System.out.println("3. Lihat Semua User");
            System.out.println("4. Logout");
            System.out.print("Pilih: ");
            int pilihan = in.nextInt();
            in.nextLine();

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

    void menuDriver() {
        System.out.println("=== Login Driver ===");
        System.out.print("Masukkan Email Driver: ");
        String email = in.nextLine();
        Driver driver = (Driver) app.findUserByEmail(email, "Driver");

        if (driver == null) {
            System.out.println("Driver tidak ditemukan.");
            return;
        }

        while (true) {
            System.out.println("\n=== Menu Driver ===");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Cek Pesanan Masuk");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            int pilihan = in.nextInt();
            in.nextLine();

            switch (pilihan) {
                case 1:
                    driver.showProfile();
                    break;
                case 2:
                if(driver.getOrder()==null){
                    System.out.println("No order");
                    break;
                }
                    driver.showOrder();
                    System.out.println("1. Chat Customer");
                    System.out.println("2. Finish Order");
                    System.out.print("Pilih: ");
                    int choice = in.nextInt();
                    in.nextLine();
                    if (choice == 1) {
                        driver.getOrder().showChat();
                        System.out.print("Tulis pesan: ");
                        String message = in.nextLine();
                        driver.getOrder().sendChat(driver,message);
                    } else if (choice == 2) {
                        if(driver.getOrder().getPaymentStatus()==false){
                            System.out.println("Payment not yet done");
                            break;
                        }
                        System.out.println("Order Success");
                        driver.getOrder().finishOrder();
                        driver.showProfile();
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    void menuCustomer() {
        System.out.println("=== Login Customer ===");
        System.out.print("Masukkan Email Customer: ");
        String email = in.nextLine();
        Customer customer = (Customer) app.findUserByEmail(email, "Customer");

        if (customer == null) {
            System.out.println("Customer tidak ditemukan.");
            return;
        }

        while (true) {
            System.out.println("\n=== Menu Customer ===");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Pesan Kendaraan");
            System.out.println("3. Cek Pesanan");
            System.out.println("4. Topup Saldo");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            int pilihan = in.nextInt();
            in.nextLine();

            switch (pilihan) {
                case 1:
                    customer.showProfile();
                    break;
                case 2:
                    Order order = customer.newOrder();
                    if (order == null) {
                        break;
                    }

                    order.processOrder();
                    System.out.println("Order Success");
                    order.showOrder();
                    break;
                case 3:
                    if (customer.getOrder() != null){
                        order = customer.getOrder();
                        order.showOrder();

                        System.out.println("1. Chat Driver");
                        System.out.println("2. Payment");
                        System.out.print("Pilih: ");
                        int choice = in.nextInt();
                        in.nextLine();

                        if (choice == 1) {
                            order.showChat();
                            System.out.print("Tulis pesan: ");
                            String message = in.nextLine();
                            order.sendChat(customer, message);
                        } else if (choice == 2) {
                            if(order.getPaymentStatus()){
                                System.out.println("Payment Success");
                                break;
                            }

                            order.pay(order.getPayment());
                            order.showPayment();

                            System.out.println("Order Success");
                            System.out.print("Give Review (1-5) :");
                            double review = in.nextInt();
                            order.giveReview(review);
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    } else{
                        System.out.println("No order");
                    }
                    break;
                case 4:
                    String message = app.topupBalance(customer);
                    System.out.println(message);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
