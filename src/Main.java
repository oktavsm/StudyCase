import java.util.*;
import App.Application;
import java.io.*;
import Utils.EnvLoader;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        EnvLoader.loadEnv(".env");
        Application app = new Application();
        app.loadDatabase();
        app.showMenu();
    }
}
