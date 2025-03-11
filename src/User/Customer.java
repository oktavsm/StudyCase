package User;

public class Customer extends User {
    private double balance;

    public Customer(int id, String name, String email, String phone, double balance) {
        super(id, name, email, phone);
        this.balance = balance;
    }

    @Override
    public void showProfile() {
        System.out.println("Your Profile");
        System.out.println("Name   : " + super.getName());
        System.out.println("Email  : " + super.getEmail());
        System.out.println("Phone  : " + super.getPhone());
        System.out.println("Balance: " + this.balance);
    }
}
