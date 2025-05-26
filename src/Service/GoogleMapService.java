package Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ImageIcon;
import org.json.JSONObject;

public class GoogleMapService {

    public static String[] getCoordinates(String address) throws Exception {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String url = System.getProperty("GOOGLE_DATA_URL") + encodedAddress + "&key="
                + System.getProperty("GOOGLE_API_KEY");

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
        return new String[] { lat, lng };
    }

    public static String[] getRouteInfo(String origin, String destination) throws Exception {
        String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        String url = System.getProperty("GOOGLE_DIRECTION_URL") + encodedOrigin +
                "&destination=" + encodedDestination + "&mode=driving&key=" + System.getProperty("GOOGLE_API_KEY");

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

        return new String[] { distance, duration };
    }

    public static ImageIcon getRouteMap(String origin, String destination) throws Exception {
        String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        String directionUrl = System.getProperty("GOOGLE_MAP_URL") + encodedOrigin
                + "&destination=" + encodedDestination
                + "&mode=driving&key=" + System.getProperty("GOOGLE_API_KEY");

        HttpURLConnection conn = (HttpURLConnection) new URL(directionUrl).openConnection();
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

        String size = "340x226";
        String mapUrl = System.getProperty("GOOGLE_ROUTE_URL")
                + "size=" + size
                + "&markers=color:green|" + encodedOrigin
                + "&markers=color:red|" + encodedDestination
                + "&path=enc:" + encodedPolyline
                + "&key=" + System.getProperty("GOOGLE_API_KEY");

        return new ImageIcon(new URL(mapUrl));
    }
}
