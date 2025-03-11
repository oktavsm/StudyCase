package User;

public class Driver extends User {
    private String name;
    private String address;
    private String phone;

    public Driver(){

    }
    public Driver(int id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void infoUser() {
        System.out.println("Driver: " + name + " " + address + " " + phone);
    }
    
}
