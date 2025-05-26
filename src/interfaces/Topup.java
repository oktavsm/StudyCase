package interfaces;

import domain.user.Customer;

public interface Topup {

    public void topupBalance(double amount, Customer customer);
}
