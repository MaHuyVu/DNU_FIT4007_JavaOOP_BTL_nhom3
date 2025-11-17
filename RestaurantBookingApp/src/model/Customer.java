package model;

public class Customer extends Person {
    private int loyaltyPoints;
    private String email;
    private boolean vip;

    // Constructor 1
    public Customer(String phone, String s, String string, boolean b) {
        super(phone, s);   // s là name
        this.email = string;
        this.vip = b;
        this.loyaltyPoints = 0;
    }

    // Constructor 2
    public Customer(String customerId, String phone, String name) {
        super(phone, name);
        this.loyaltyPoints = 0;
        this.vip = false;
        this.email = "";
        setId(customerId);
    }

    // Loyalty points
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addPoints(int points) {
        this.loyaltyPoints += points;
    }

    // Email
    public String getEmail() {
        return email;
    }

    // VIP
    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    // ID override
    @Override
    public void setId(String id) {
        // gọi đúng hàm trong Person
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Email: " + email
                + " | VIP: " + (vip ? "Yes" : "No")
                + " | Points: " + loyaltyPoints;
    }
}
