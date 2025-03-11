import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Interface.*;
import User.*;
import Vehicle.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Transportasi Online ===");
            System.out.println("1. Login sebagai Customer");
            System.out.println("2. Login sebagai Driver");
            System.out.println("3. Login sebagai Admin");
            System.out.println("4. Keluar Aplikasi");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

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
                    System.out.println("Keluar aplikasi.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void menuCustomer() {
        while (true) {
            System.out.println("\n=== Menu Customer ===");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Pesan Kendaraan");
            System.out.println("3. Chat dengan Driver");
            System.out.println("4. Beri Rating ke Driver");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    System.out.println("Menampilkan profil customer...");
                    break;
                case 2:
                    System.out.println("Memproses pemesanan kendaraan...");
                    break;
                case 3:
                    System.out.println("Chat dengan driver...");
                    break;
                case 4:
                    System.out.println("Memberi rating ke driver...");
                    break;
                case 5:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void menuDriver() {
        while (true) {
            System.out.println("\n=== Menu Driver ===");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Cek Pesanan Masuk");
            System.out.println("3. Chat dengan Customer");
            System.out.println("4. Logout");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    System.out.println("Menampilkan profil driver...");
                    break;
                case 2:
                    System.out.println("Cek pesanan yang masuk...");
                    break;
                case 3:
                    System.out.println("Chat dengan customer...");
                    break;
                case 4:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void menuAdmin() {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Lihat Data Driver");
            System.out.println("3. Lihat Data Customer");
            System.out.println("4. Logout");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    System.out.println("Menampilkan profil Admin...");
                    break;
                case 2:
                    System.out.println("Menampilkan data driver...");
                    break;
                case 3:
                    System.out.println("Menampilkan data customer...");
                    break;
                case 4:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
