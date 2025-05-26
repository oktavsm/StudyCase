package Utils;

import java.io.*;

public class EnvLoader {
    public static void loadEnv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || !line.contains("="))
                    continue;

                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    System.setProperty(key, value);
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to load .env file: " + e.getMessage());
        }
    }
}
