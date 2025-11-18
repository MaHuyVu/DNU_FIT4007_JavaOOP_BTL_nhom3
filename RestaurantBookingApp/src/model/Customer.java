package model;

import java.util.UUID;

public class Customer extends Person {
    private String id;
    private int loyaltyPoints;
    private String email;
    private boolean vip;

    // Constructor 1: Với email và vip
    public Customer(String name, String phone, String email, boolean vip) {
        super(name, phone);
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.vip = vip;
        this.loyaltyPoints = 0;
    }

    // Constructor 2: Không có email (tạm thời)
    public Customer(String name, String phone, String email) {
        super(name, phone);
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.vip = false;
        this.loyaltyPoints = 0;
    }

    // Constructor 3: Với ID cụ thể (khi load từ file)
    public Customer(String id, String name, String phone, String email, boolean vip) {
        super(name, phone);
        this.id = id;
        this.email = email;
        this.vip = vip;
        this.loyaltyPoints = 0;
    }

    // Loyalty points
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addPoints(int points) {
        this.loyaltyPoints += points;
    }

    // Email
    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // VIP
    @Override
    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    // ID override
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer[" + id + "] " + super.toString()
                + " | Email: " + email
                + " | VIP: " + (vip ? "Yes" : "No")
                + " | Points: " + loyaltyPoints;
    }
}