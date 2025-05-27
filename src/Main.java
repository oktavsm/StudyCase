import utils.EnvLoader;
import java.io.*;
import javax.swing.UIManager;
import com.formdev.flatlaf.*;
import app.Application;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Failed to initialize LaF: " + e.getMessage());
        }

        EnvLoader.loadEnv(".env");
        Application app = new Application();
        app.loadDatabase();
        app.showMenu();
    }
}
