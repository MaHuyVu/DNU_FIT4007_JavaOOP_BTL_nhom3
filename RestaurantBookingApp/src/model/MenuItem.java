package model;

import java.util.UUID;

public abstract class MenuItem {

    protected String id;
    protected String name;
    protected double price;
    protected double discount;

    public MenuItem(String name, double price, double discount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %.0f₫ | Giảm: %.0f%%",
                id, name, getType(), price, discount * 100);
    }

    public double getDiscountedPrice() {
        return price * (1 - discount);
    }
}