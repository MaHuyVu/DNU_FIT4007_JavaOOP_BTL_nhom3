package model;

public class Food extends MenuItem {
<<<<<<< HEAD
    public Food(String name, String category, double price, double discount) {
=======
    public Food(String name, String s, double price, double discount) {
>>>>>>> main
        super(name, "Food", price, discount);
    }
    @Override
    public String toString() {
        return super.toString() + " | Type: Food";
    }
}
