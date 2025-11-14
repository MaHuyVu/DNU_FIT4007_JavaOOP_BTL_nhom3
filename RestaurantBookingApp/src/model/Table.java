package model;

import java.io.Serializable;
import java.util.UUID;

public abstract class Table implements Serializable {
    protected String id;
    protected String type;
    protected int seats;
    protected double surcharge;
    private int number;
    private int capacity;
    private TableStatus status = TableStatus.AVAILABLE;

    public Table(String type, int seats, double surcharge, int number, int capacity) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.seats = seats;
        this.surcharge = surcharge;
        this.number = number;
        this.capacity = capacity;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public double getSurcharge() {
        return surcharge;
    }

    // Setters
    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public TableStatus getStatus() { return status; }
    public void setStatus(TableStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Table[%s] Type: %s, Seats: %d, Surcharge: %.0f₫",
                id, type, seats, surcharge);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Table table = (Table) obj;
        return id.equals(table.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
