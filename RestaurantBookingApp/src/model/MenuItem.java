package model;

public abstract class MenuItem {

    protected String id;
    protected String name;
    protected double price;
    protected double discount;

    public MenuItem(String id, double price, double discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return id + " | " + name + " | " + getType() + " | " + price + " | discount: " + discount;
    }

    public double getDiscountedPrice() {
        double discountAmount = price * discount / 100;
        return price - discountAmount;
    }

    public void setId(String generate) {
    }
}
