package model;

import java.io.Serializable;

public class Drink extends MenuItem implements Serializable {

    private String size;

    public Drink(String name, double price, double discount) {
        super(name, price, discount);
        this.size = "M";
    }

    public Drink(String name, double price, double discount, String size) {
        super(name, price, discount);
        this.size = size;
    }

    @Override
    public String getType() {
        return "DRINK";
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("🥤 Đồ uống: %s | Giá: %.0f₫ | Giảm giá: %.0f%% | Size: %s",
                getName(), getPrice(), getDiscount() * 100, size);
    }
}