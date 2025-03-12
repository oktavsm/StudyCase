package User;
import Order.Order;
import App.Application;
import java.util.Scanner;
public class Customer extends User {
    private double balance;
    private Scanner sc = new Scanner(System.in);

    public Customer(int id, String name, String email, String phone, double balance, Application app) {
        super(id, name, email, phone, app);
        this.balance = balance;
    }
    public Order newOrder(){
        System.out.print("Choose your vehicle type (1. Motocycle, 2. Car): ");
        int choice = sc.nextInt();
        sc.nextLine();
        String type = (choice==1)?"Motocycle":"Car";
        Driver driver = app.findAvailableDriver(type);
        if(driver==null){
            System.out.println("No driver available at the moment");
            return null;
        }
        System.out.println("Enter your location: ");
        String location = sc.nextLine();
        System.out.println("Enter your destination: ");
        String destination = sc.nextLine();
        System.out.println("Enter the distance: ");
        int distance = sc.nextInt();
        sc.nextLine();
        return new Order(this,driver,location,destination,distance);
    }
    @Override
    public void showProfile() {
        System.out.println("--- Customer Profile ---");
        System.out.println("ID     : " + super.getId());
        System.out.println("Name   : " + super.getName());
        System.out.println("Email  : " + super.getEmail());
        System.out.println("Phone  : " + super.getPhone());
        System.out.println("Balance: " + this.balance);
    }
}
