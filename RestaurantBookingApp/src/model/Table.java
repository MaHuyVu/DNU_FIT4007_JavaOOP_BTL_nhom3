package model;

import java.io.Serializable;
import java.util.UUID;

public class Table implements Serializable {
    protected String id;
    protected String type;
    protected int seats;
    protected double surcharge;

    // Constructor tạo bàn mới (id tự động)
    public Table(String id, String type, int seats, double surcharge) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.seats = seats;
        this.surcharge = surcharge;
    }

    // Constructor load từ CSV
    public Table(String id, String type, int seats, String surcharge) {
        this.id = id;
        this.type = type;
        this.seats = seats;
        this.surcharge = surcharge;
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

    @Override
    public String toString() {
        return String.format(
                "Table[%s] Type: %s, Seats: %d, Surcharge: %.0f₫",
                id, type, seats, surcharge
        );
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
