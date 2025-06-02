package infra.maps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleMap {
    private static final String API_KEY = System.getProperty("GOOGLE_API_KEY");
    private static final String DATA_URL = System.getProperty("GOOGLE_DATA_URL");
    private static final String DIRECTION_URL = System.getProperty("GOOGLE_DIRECTION_URL");
    private static final String ROUTE_URL = System.getProperty("GOOGLE_ROUTE_URL");
    private static final String MAP_SIZE = "400x326";

    private static JSONObject getJsonResponseFromUrl(String url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return new JSONObject(response.toString());
        }
    }

    public static String[] getCoordinates(String address) throws Exception {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String url = DATA_URL + encodedAddress + "&key=" + API_KEY;

        JSONObject json = getJsonResponseFromUrl(url);

        JSONArray results = json.getJSONArray("results");
        if (results == null || results.isEmpty()) {
            throw new Exception("Address not found");
        }

        JSONObject location = results.getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");

        return new String[] {
                String.valueOf(location.getDouble("lat")),
                String.valueOf(location.getDouble("lng"))
        };
    }

    public static String[] getRouteInfo(String origin, String destination) throws Exception {
        String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        String url = DIRECTION_URL + encodedOrigin +
                "&destination=" + encodedDestination +
                "&mode=driving&key=" + API_KEY;

        JSONObject json = getJsonResponseFromUrl(url);

        JSONArray routes = json.optJSONArray("routes");
        if (routes == null || routes.isEmpty()) {
            throw new Exception("Route not found");
        }

        JSONObject leg = routes.getJSONObject(0)
                .getJSONArray("legs")
                .getJSONObject(0);

        String distance = leg.getJSONObject("distance").getString("text");
        String duration = leg.getJSONObject("duration").getString("text");

        return new String[] { distance, duration };
    }

    public static ImageIcon getRouteMap(String origin, String destination) throws Exception {
        String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        String directionApiUrl = DIRECTION_URL + encodedOrigin +
                "&destination=" + encodedDestination +
                "&mode=driving&key=" + API_KEY;

        JSONObject json = getJsonResponseFromUrl(directionApiUrl);

        JSONArray routes = json.optJSONArray("routes");
        if (routes == null || routes.isEmpty()) {
            throw new Exception("Can not load route for map");
        }

        String encodedPolyline = routes.getJSONObject(0)
                .getJSONObject("overview_polyline")
                .getString("points");

        String mapUrl = ROUTE_URL +
                "size=" + MAP_SIZE +
                "&markers=color:green|" + encodedOrigin +
                "&markers=color:red|" + encodedDestination +
                "&path=enc:" + encodedPolyline +
                "&key=" + API_KEY;

        return new ImageIcon(new URL(mapUrl));
    }
}
