package model;

public class Customer extends Person {
    private int loyaltyPoints;

    public Customer(String phone, String s, String string, boolean b) {
        super(phone, "");
        this.loyaltyPoints = 0;
    }

    public Customer(String customerId, String phone, String name) {
        super(phone, name);
        this.loyaltyPoints = 0;
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

    public abstract setId(String generate) ;

    public String getEmail() {
    }

    public char[] isVip() {
    }
}