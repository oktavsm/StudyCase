import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Interface.*;
import User.*;
import Vehicle.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application app = new Application();
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
                    app.menuCustomer();
                    break;
                case 2:
                    app.menuDriver();
                    break;
                case 3:
                    app.menuAdmin();
                    break;
                case 4:
                    System.out.println("Keluar aplikasi.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    

    
}
