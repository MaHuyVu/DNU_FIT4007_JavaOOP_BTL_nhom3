package model;

import java.io.Serializable;

public class Food extends MenuItem implements Serializable {

    private boolean spicy;

    public Food(String name, double price, double discount) {
        super(name, price, discount);
        this.spicy = false;
    }

    public Food(String name, double price, double discount, boolean spicy) {
        super(name, price, discount);
        this.spicy = spicy;
    }

    @Override
    public String getType() {
        return "FOOD";
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    @Override
    public String toString() {
        return String.format("🍛 Món ăn: %s | Giá: %.0f₫ | Giảm giá: %.0f%% | %s",
                getName(), getPrice(), getDiscount() * 100, (spicy ? "Cay" : "Không cay"));
    }
}