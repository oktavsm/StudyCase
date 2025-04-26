import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class BacaFileVehicle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String namaFile = "vehicle_data.txt";
        System.out.println("Nama file dibaca: " + namaFile);

        try {
            FileReader fileReader = new FileReader(namaFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            System.out.println("============================================================================");
            System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s |\n","Type", "Plate No", "Color", "Brand", "Base Rate");
            System.out.println("============================================================================");

            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                if (tokenizer.countTokens() == 5) {
                    // Gunakan StringBuffer untuk menggabungkan hasil parsing
                    StringBuffer buffer = new StringBuffer();

                    // Gunakan StringJoiner untuk membuat isi tabel lebih rapi
                    StringJoiner joiner = new StringJoiner(" | ", "| ", " |");

                    while (tokenizer.hasMoreTokens()) {
                        String token = tokenizer.nextToken().trim();
                        joiner.add(String.format("%-12s", token));
                    }

                    buffer.append(joiner.toString());
                    System.out.println(buffer);
                }
            }

            System.out.println("============================================================================");
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("File gagal dibaca." + e.getMessage());
        }
    }
}
