package User;

public class Customer extends User {
    private String name;
    private String address;
    private String phone;

    public Customer(){

    }
    public Customer(int id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void infoUser() {
        System.out.println("Customer: " + name + " " + address + " " + phone);
    }
    
}
