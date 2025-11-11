package model;

import java.io.Serializable;
import java.util.UUID;

public abstract class MenuItem implements Serializable {
    protected String id;
    protected String name;
    protected String category;
    protected double price;
    protected double discount; // percent

    public MenuItem(String name, String category, double price, double discount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
    }

    public String getId(){ return id; }
    public String getName(){ return name; }
    public String getCategory(){ return category; }
    public double getPrice(){ return price; }
    public double getDiscount(){ return discount; }

    // tên chuẩn: trả về giá sau khi đã giảm giá
    public double getFinalPrice() {
        return price * (1 - discount / 100.0);
    }

    // alias: nếu nơi khác gọi getDiscountedPrice()
    public double getDiscountedPrice() {
        return getFinalPrice();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %.0fđ (-%.0f%%)", id, name, category, price, discount);
    }
}
