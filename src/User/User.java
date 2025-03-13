package User;

import App.Application;

public abstract class User {
    Application app;
    private String id;
    private String name;
    private String email;
    private String phone;

    public User(String name, String email, String phone, Application app) {
        this.id = app.generateRandomId();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.app = app;
    }

    public String getId() {
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
