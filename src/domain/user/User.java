package domain.user;

import app.Application;

public abstract class User {
    Application app;
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

    public User(String name, String email, String password, String phone, Application app) {
        this.id = app.generateRandomId();
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public abstract double getBalance();

    public abstract void showProfile();
}
