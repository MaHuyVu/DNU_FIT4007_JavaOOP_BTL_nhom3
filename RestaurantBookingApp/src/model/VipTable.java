package model;

public class VipTable extends Table {
    private double surcharge;

    public VipTable(int number, int capacity, double surcharge) {
        super(Integer.parseInt(number), capacity);
        this.surcharge = surcharge;
    }

    public double getSurcharge() { return surcharge; }
    public void setSurcharge(double surcharge) { this.surcharge = surcharge; }
}