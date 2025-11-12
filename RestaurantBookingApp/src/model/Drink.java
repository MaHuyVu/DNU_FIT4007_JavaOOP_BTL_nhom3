package model;

/**
 * Drink là một MenuItem, không phải Table.
 * Constructor gọi MenuItem(name, category, price, discount).
 */
public class Drink extends MenuItem {
    public Drink(String name, String category, double price, double discount) {
        super(name, "Drink", price, discount);
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Drink";
    }
}
