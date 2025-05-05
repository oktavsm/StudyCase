package Service;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class MapPickerMultiStep {
    private final JFrame frame = new JFrame("Pilih Lokasi");
    private final JFXPanel jfxPanel = new JFXPanel();
    private final BiConsumer<CoordinatePair, CoordinatePair> resultCallback;

    private CoordinatePair pickupLocation;

    public MapPickerMultiStep(BiConsumer<CoordinatePair, CoordinatePair> resultCallback) {
        this.resultCallback = resultCallback;
        setupFrame();
        initFX();
    }

    private void setupFrame() {
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.add(jfxPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initFX() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();
            engine.load(getClass().getResource("/map-multistep.html").toExternalForm());

            JSObject window = (JSObject) engine.executeScript("window");
            window.setMember("javaConnector", new JavaConnector());

            jfxPanel.setScene(new Scene(webView));
        });
    }

    public void show() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    private class JavaConnector {
        public void sendCoordinates(double lat, double lng) {
            if (pickupLocation == null) {
                pickupLocation = new CoordinatePair(lat, lng);
                System.out.println("Titik jemput dipilih: " + lat + ", " + lng);
                // lanjut ke titik tujuan
                Platform.runLater(() -> {
                    WebEngine engine = ((WebView) jfxPanel.getScene().getRoot()).getEngine();
                    engine.executeScript("switchToDestination()");
                });
            } else {
                CoordinatePair destination = new CoordinatePair(lat, lng);
                System.out.println("Titik tujuan dipilih: " + lat + ", " + lng);
                resultCallback.accept(pickupLocation, destination);
                frame.dispose();
            }
        }
    }

    public static class CoordinatePair {
        public final double lat;
        public final double lng;

        public CoordinatePair(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }
}