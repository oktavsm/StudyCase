package User;
abstract class User {
    private int id;
    public User(int id) {
        this.id = id;
    }
    public User(){
        
    }
    abstract void infoUser();
}
