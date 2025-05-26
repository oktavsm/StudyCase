import utils.EnvLoader;
import java.io.*;
import app.Application;

public class Main {
    public static void main(String[] args) throws IOException {
        EnvLoader.loadEnv(".env");
        Application app = new Application();
        app.loadDatabase();
        app.showMenu();
    }
}
