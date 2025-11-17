package model;

import java.io.Serializable;

public class Drink extends MenuItem implements Serializable {

    private String size; // Kích cỡ: S, M, L

    public Drink(String name, String price, double discount, double size) {
        super(name, Double.parseDouble(price), discount);
    }

    public Drink(String name, double v, double discount) {
        super(name ,v , discount);
    }

    // Getter & Setter
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("🥤 Đồ uống: %s | Giá: %.0f₫ | Giảm giá: %.0f%% | Size: %s",
                getName(), getPrice(), getDiscount(), size);
    }
}
