package Interface;
import Order.Order;
import User.Customer;

public interface Payment {
    public void pay(double amount);
    public void showPayment();
}
