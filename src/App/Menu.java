package App;

import java.util.*;
import User.*;
import Order.*;

public class Menu {
    Application app;
    Scanner in = new Scanner(System.in);

    Menu(Application app) {
        this.app = app;
    }

    void mainMenu() {
        while (true) {
            System.out.println("\n=== Online Transportation ===");
            System.out.println("1. Register as a Customer");
            System.out.println("2. Register as a Driver");
            System.out.println("3. Login as a Customer");
            System.out.println("4. Login as a Driver");
            System.out.println("5. Exit Application");
            System.out.print("Choose: ");
            int pilihan = in.nextInt();
            in.nextLine();

            switch (pilihan) {
                case 1:
                    app.registerCustomer();
                    break;
                case 2:
                    app.registerDriver();
                    break;
                case 3:
                    menuCustomer();
                    break;
                case 4:
                    menuDriver();
                    break;
                case 5:
                    System.out.println("Thank you for using our application!!!");
                    return;
                case 1945:
                    menuAdmin();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    void menuAdmin() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Driver");
            System.out.println("3. Show All Users");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            int pilihan = in.nextInt();
            in.nextLine();

            switch (pilihan) {
                case 1:
                    app.addCustomer();
                    break;
                case 2:
                    app.addDriver();
                    break;
                case 3:
                    app.showAllUsers();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    void menuDriver() {
        System.out.println("=== Driver Login ===");
        System.out.print("Enter Driver Email: ");
        String email = in.nextLine();
        System.out.print("Enter Driver Password: ");
        String password = in.nextLine();
        Driver driver = (Driver) app.validateEmailAndPassword(email, password, "Driver");

        if (driver == null) {
            System.out.println("Email or password is wrong");
            return;
        }

        while (true) {
            System.out.println("\n=== Driver Menu ===");
            System.out.println("1. Show Profile");
            System.out.println("2. Check Incoming Orders");
            System.out.println("3. Logout");
            System.out.print("Choice: ");
            int pilihan = in.nextInt();
            in.nextLine();

            switch (pilihan) {
                case 1:
                    driver.showProfile();
                    break;
                case 2:
                if(driver.getOrder() == null){
                    System.out.println("No order");
                    break;
                }
                    driver.showOrder();
                    System.out.println("1. Chat Customer");
                    System.out.println("2. Finish Order");
                    System.out.print("Choose: ");
                    int choice = in.nextInt();
                    in.nextLine();
                    if (choice == 1) {
                        driver.getOrder().showChat();
                        System.out.print("Type a message: ");
                        String message = in.nextLine();
                        driver.getOrder().sendChat(driver, message);
                    } else if (choice == 2) {
                        if(driver.getOrder().getPaymentStatus() == false){
                            System.out.println("Payment not yet done");
                            break;
                        }
                        System.out.println("Order Success");
                        driver.getOrder().finishOrder();
                        driver.showProfile();
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    void menuCustomer() {
        System.out.println("=== Customer Login ===");
        System.out.print("Enter Customer Email: ");
        String email = in.nextLine();
        System.out.println("Enter Customer Password");
        String password = in.nextLine();
        Customer customer = (Customer) app.validateEmailAndPassword(email, password, "Customer");

        if (customer == null) {
            System.out.println("Email or password is wrong");
            return;
        }

        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. Show Profile");
            System.out.println("2. Make an Order");
            System.out.println("3. Check Order");
            System.out.println("4. Topup Balance");
            System.out.println("5. Logout");
            System.out.print("Choose: ");
            int pilihan = in.nextInt();
            in.nextLine();

            switch (pilihan) {
                case 1:
                    customer.showProfile();
                    break;
                case 2:
                    Order order = customer.newOrder();
                    if (order == null) {
                        break;
                    }
                    order.processOrder();
                    System.out.println("Order Success");
                    order.showOrder();
                    break;
                case 3:
                    if (customer.getOrder() != null){
                        order = customer.getOrder();
                        order.showOrder();

                        System.out.println("1. Chat Driver");
                        System.out.println("2. Payment");
                        System.out.print("Choose: ");
                        int choice = in.nextInt();
                        in.nextLine();

                        if (choice == 1) {
                            order.showChat();
                            System.out.print("Type a message: ");
                            String message = in.nextLine();
                            order.sendChat(customer, message);
                        } else if (choice == 2) {
                            if(order.getPaymentStatus()){
                                System.out.println("Payment Success");
                                break;
                            }
                            
                            order.pay(order.getPayment());
                            order.showPayment();

                            System.out.println("Order Success");
                            System.out.print("Give Review (1-5) :");
                            double review = in.nextDouble();
                            in.nextLine();
                            order.giveReview(review);
                        } else {
                            System.out.println("Invalid choice");
                        }
                    } else{
                        System.out.println("No order");
                    }
                    break;
                case 4:
                    String message = app.topupBalance(customer);
                    System.out.println(message);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
