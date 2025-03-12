package User;
import App.Application;
public abstract class User {
    Application app;
    private int id;
    private String name;
    private String email;
    private String phone;

    public User(int id, String name, String email, String phone, Application app) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.app=app;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    abstract public void showProfile();
}
