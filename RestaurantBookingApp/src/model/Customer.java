package model;

public class Customer extends Person {
    private int loyaltyPoints;

    public Customer(String name) {
        super(name, phone);
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
}
