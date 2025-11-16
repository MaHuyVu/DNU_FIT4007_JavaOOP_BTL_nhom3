package model;

public class Customer extends Person {
    private int loyaltyPoints;

<<<<<<< HEAD
    public Customer(String name, String phone) {
        super(name, phone);
=======
    public Customer(String phone, String name) {  // ✅ Thêm phone
        super(phone, name);
>>>>>>> b30100cf7b1ff0ffd06260066a1e2d23aa035ff6
        this.loyaltyPoints = 0;
    }

    public Customer(String customerId) {
        super();
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void addPoints(int points) {
        this.loyaltyPoints += points;
    }
    @Override
    public String toString() {
        return super.toString() + " | Points: " + loyaltyPoints;
    }
}
