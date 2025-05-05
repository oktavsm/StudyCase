package Service;
import java.io.*;
import java.net.*;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        try {
            String apiKey = "5b3ce3597851110001cf62483971fae53d8d4ea4b23323e682673057";  // Ganti dengan keymu
            String requestUrl = "https://api.openrouteservice.org/v2/matrix/driving-car";

            // Titik koordinat: [longitude, latitude]
            String jsonInput = "{"
                + "\"locations\":[[112.615, -7.952],[112.613, -7.957]],"
                + "\"metrics\":[\"distance\"]"
                + "}";

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", apiKey);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder responseBuilder = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                responseBuilder.append(responseLine.trim());
            }

            String response = responseBuilder.toString();

            // Pakai GSON untuk parsing
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            JsonArray distances = jsonObject.getAsJsonArray("distances");
            double distanceInMeters = distances.get(0).getAsJsonArray().get(1).getAsDouble();

            System.out.println("Jaraknya: " + distanceInMeters + " meter");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
