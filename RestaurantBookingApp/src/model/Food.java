package model;

import java.io.Serializable;

public class Food extends MenuItem implements Serializable {

    private boolean spicy; // Món có cay hay không

    public Food(String name, String price, double discount, double spicy) {
        super(name, Double.parseDouble(price), discount);
    }

    public Food(String name, double v, double discount) {
        super(name , v , discount);
    }

    @Override
    public String getType() {
        return "";
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
                getName(), getPrice(), getDiscount(), (spicy ? "Cay" : "Không cay"));
    }
}
