package Test;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class DistanceCalculatorGUI {
    private JFrame frame;
    private JTextField location1TextField;
    private JTextField location2TextField;
    private JTextArea resultTextArea;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DistanceCalculatorGUI window = new DistanceCalculatorGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DistanceCalculatorGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Distance Calculator");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblLocation1 = new JLabel("Location 1 (Name):");
        lblLocation1.setBounds(10, 20, 120, 14);
        frame.getContentPane().add(lblLocation1);

        location1TextField = new JTextField();
        location1TextField.setBounds(140, 20, 200, 20);
        frame.getContentPane().add(location1TextField);
        location1TextField.setColumns(10);

        JLabel lblLocation2 = new JLabel("Location 2 (Name):");
        lblLocation2.setBounds(10, 60, 120, 14);
        frame.getContentPane().add(lblLocation2);

        location2TextField = new JTextField();
        location2TextField.setBounds(140, 60, 200, 20);
        frame.getContentPane().add(location2TextField);
        location2TextField.setColumns(10);

        JButton btnCalculate = new JButton("Calculate Distance");
        btnCalculate.setBounds(140, 100, 150, 23);
        frame.getContentPane().add(btnCalculate);

        resultTextArea = new JTextArea();
        resultTextArea.setBounds(10, 140, 400, 100);
        frame.getContentPane().add(resultTextArea);

        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String location1 = location1TextField.getText();
                String location2 = location2TextField.getText();
                
                if (location1.isEmpty() || location2.isEmpty()) {
                    resultTextArea.setText("Please enter both locations.");
                    return;
                }
                
                try {
                    double[] coords1 = getCoordinates(location1);
                    double[] coords2 = getCoordinates(location2);
                    System.out.println("Location 1 Coordinates: Latitude: " + coords1[0] + ", Longitude: " + coords1[1]);
System.out.println("Location 2 Coordinates: Latitude: " + coords2[0] + ", Longitude: " + coords2[1]);

                    if (coords1 == null || coords2 == null) {
                        resultTextArea.setText("Unable to find coordinates for one or both locations.");
                        return;
                    }

                    double distance = calculateDistance(coords1[0], coords1[1], coords2[0], coords2[1]);
                    resultTextArea.setText("Distance: " + distance + " km");
                } catch (Exception ex) {
                    resultTextArea.setText("Error: " + ex.getMessage());
                }
            }
        });
    }

    private double[] getCoordinates(String location) throws IOException {
        String apiKey = "5b3ce3597851110001cf62483971fae53d8d4ea4b23323e682673057"; // Ganti dengan API Key kamu
        String urlString = "https://api.openrouteservice.org/geocode/search?api_key=" + apiKey + "&text=" + URLEncoder.encode(location, "UTF-8");

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject obj = new JSONObject(response.toString());
        try {
            double longitude = obj.getJSONArray("features")
                                   .getJSONObject(0)
                                   .getJSONObject("geometry")
                                   .getJSONArray("coordinates")
                                   .getDouble(0); // longitude
            double latitude = obj.getJSONArray("features")
                                  .getJSONObject(0)
                                  .getJSONObject("geometry")
                                  .getJSONArray("coordinates")
                                  .getDouble(1); // latitude

            return new double[] {latitude, longitude};
        } catch (Exception e) {
            return null; // Jika koordinat tidak ditemukan
        }
    }

    // Haversine formula untuk menghitung jarak antara dua koordinat
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371; // Radius bumi dalam kilometer
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Hasil dalam kilometer
    }
}
