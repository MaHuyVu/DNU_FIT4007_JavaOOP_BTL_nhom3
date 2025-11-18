package model;

public class VipTable extends Table {

    public VipTable(String number, int capacity, double surcharge) {
        super(number, capacity);
        this.type = "VIP";
        this.surcharge = surcharge;
    }

    @Override
    public double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public String toString() {
        return String.format("Table[%s] #%d | VIP | Capacity: %d | Surcharge: %.0f₫ | Status: %s",
                id, number, capacity, surcharge, status);
    }
}