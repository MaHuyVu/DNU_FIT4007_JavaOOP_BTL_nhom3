package model;

import java.io.Serializable;
import java.util.UUID;

public class Table implements Serializable {
    protected String id;
    protected String type;
    protected int seats;
    protected double surcharge;
    private int number;
    private int capacity;
    private TableStatus status = TableStatus.AVAILABLE;

    // ============================
    //  CONSTRUCTORS
    // ============================
    public Table(int seats, double surcharge) {
        this.id = UUID.randomUUID().toString();
        this.seats = seats;
        this.surcharge = surcharge;
        this.capacity = seats;  // Mặc định capacity = seats
    }

    public Table(String id, int seats, double surcharge) {
        this.id = id;
        this.seats = seats;
        this.surcharge = surcharge;
        this.capacity = seats;
    }

    public Table(String id, String type, int seats, double surcharge) {
        this.id = id;
        this.type = type;
        this.seats = seats;
        this.surcharge = surcharge;
        this.capacity = seats;
    }

    // ============================
    //  GETTERS
    // ============================
    public String getId() {
        return id;
    }

    public String getTableId() {
        return id;  // Alias cho getId()
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

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    // ============================
    //  SETTERS
    // ============================
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setSeatCount(int newSeats) {
        this.seats = newSeats;  // Alias cho setSeats()
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public void setStatus(String newStatus) {
        try {
            this.status = TableStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Trạng thái không hợp lệ: " + newStatus);
        }
    }

    // ============================
    //  UTILITY METHODS
    // ============================
    public boolean isAvailable() {
        return status == TableStatus.AVAILABLE;
    }

    public void markAsOccupied() {
        this.status = TableStatus.OCCUPIED;
    }

    public void markAsReserved() {
        this.status = TableStatus.RESERVED;
    }

    public void markAsAvailable() {
        this.status = TableStatus.AVAILABLE;
    }

    public double calculateTotal() {
        return surcharge;
    }

    // ============================
    //  TOSTRING
    // ============================
    @Override
    public String toString() {
        return String.format("Table[%s] Type: %s, Seats: %d, Surcharge: %.0f₫, Status: %s",
                id, type, seats, surcharge, status);
    }

    // ============================
    //  EQUALS & HASHCODE
    // ============================
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