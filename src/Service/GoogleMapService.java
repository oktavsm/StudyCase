package Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.ImageIcon;

import org.json.JSONObject;

public class GoogleMapService {
    private static final String API_KEY = "AIzaSyAaPACkNY9Mq29Dbxuc-ckW4QZ1fUrXzoY"; // Ganti dengan API Key kamu

    // Mendapatkan koordinat dari alamat (Geocoding API)
    public static String[] getCoordinates(String address) throws Exception {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + API_KEY;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());
        JSONObject location = json.getJSONArray("results")
                                  .getJSONObject(0)
                                  .getJSONObject("geometry")
                                  .getJSONObject("location");

        String lat = String.valueOf(location.getDouble("lat"));
        String lng = String.valueOf(location.getDouble("lng"));
        return new String[]{lat, lng};
    }

    // Mendapatkan info rute (jarak & durasi) dari Directions API
    public static String[] getRouteInfo(String origin, String destination) throws Exception {
        String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + encodedOrigin +
                     "&destination=" + encodedDestination + "&mode=driving&key=" + API_KEY;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());
        JSONObject leg = json.getJSONArray("routes")
                             .getJSONObject(0)
                             .getJSONArray("legs")
                             .getJSONObject(0);

        String distance = leg.getJSONObject("distance").getString("text");
        String duration = leg.getJSONObject("duration").getString("text");

        return new String[]{distance, duration};
    }

    // Mendapatkan gambar map dengan rute sesuai jalan raya (pakai encoded polyline)
    public static ImageIcon getRouteMap(String origin, String destination) throws Exception {
        String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        // Step 1: Ambil encoded polyline dari Directions API
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + encodedOrigin
                   + "&destination=" + encodedDestination
                   + "&mode=driving&key=" + API_KEY;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());
        String encodedPolyline = json.getJSONArray("routes")
                                     .getJSONObject(0)
                                     .getJSONObject("overview_polyline")
                                     .getString("points");
        
        // Step 2: Buat URL static map dengan encoded polyline sebagai path
        String size = "600x400";
        String mapUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                      + "size=" + size
                      + "&markers=color:green|" + encodedOrigin
                      + "&markers=color:red|" + encodedDestination
                      + "&path=enc:" + encodedPolyline
                      + "&key=" + API_KEY;

        return new ImageIcon(new URL(mapUrl));
    }
}
