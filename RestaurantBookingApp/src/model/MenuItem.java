package model;

public abstract class Menultem implements Serializable {
    protected String id;
    protected String name;
    protected String type;
    protected double price;
    protected double discount; // %

    public Menultem(String name, String type, double price, double discount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.price = price;
        this.discount = discount;
    }

    public double getFinalPrice() {
        return price * (1 - discount / 100);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %.0fđ (-%.0f%%)", id, name, type, price, discount);
    }
}


