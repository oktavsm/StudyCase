import MapPickerMultiStep;
import MapPickerMultiStep.CoordinatePair;

package Service;
public class MainApp {

    public static void main(String[] args) {
        // Inisialisasi JavaFX (harus dijalankan duluan kalau pakai Swing + JavaFX)
        javafx.application.Platform.startup(() -> {});

        // Buka peta pilih titik jemput & tujuan
        MapPickerMultiStep picker = new MapPickerMultiStep((pickup, destination) -> {
            System.out.println("Titik jemput: " + pickup.lat + ", " + pickup.lng);
            System.out.println("Titik tujuan: " + destination.lat + ", " + destination.lng);

            // Hitung jarak (pakai rumus haversine)
            double jarakKm = hitungJarak(pickup, destination);
            System.out.printf("Jarak: %.2f km\n", jarakKm);

            // Di sini kamu bisa lanjutkan logika: tampilkan estimasi harga, konfirmasi user, simpan data, dll
        });

        picker.show();
    }

    public static double hitungJarak(MapPickerMultiStep.CoordinatePair a, MapPickerMultiStep.CoordinatePair b) {
        double R = 6371; // radius bumi dalam km
        double dLat = Math.toRadians(b.lat - a.lat);
        double dLng = Math.toRadians(b.lng - a.lng);
        double lat1 = Math.toRadians(a.lat);
        double lat2 = Math.toRadians(b.lat);

        double aVal = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                      Math.sin(dLng / 2) * Math.sin(dLng / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(aVal), Math.sqrt(1 - aVal));
        return R * c;
    }
}