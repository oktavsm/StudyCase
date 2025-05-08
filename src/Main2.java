import java.util.*;
import App.Application;
import java.io.*;
import Utils.EnvLoader;

public class Main2 {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        EnvLoader.loadEnv(".env"); // Load environment variables from .env file
        Application app = new Application();
        app.loadDatabase();
        app.showMenu();
    }
}
