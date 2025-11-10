package model;

public class Food extends MenuItem {
    public Food(String name, double price, double discount) {
        super(name, "Food", price, discount);
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Food";
    }
}
