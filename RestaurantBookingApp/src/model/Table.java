package model;

import java.io.Serializable;
import java.util.UUID;

public class Table implements Serializable {
    protected String id;
    protected String type;
    protected int number;
    protected int capacity;
    protected double surcharge;
    protected TableStatus status = TableStatus.AVAILABLE;

    // Constructor 1: Với number và capacity
    public Table(int number, int capacity) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.capacity = capacity;
        this.surcharge = 0.0;
        this.type = "STANDARD";
    }

    // Constructor 2: Với String number (cho VipTable/StandardTable)
    public Table(String number, int capacity) {
        this.id = UUID.randomUUID().toString();
        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            this.number = 0;
        }
        this.capacity = capacity;
        this.surcharge = 0.0;
        this.type = "STANDARD";
    }

    // Constructor 3: Đầy đủ tham số
    public Table(String id, String type, int number, int capacity, double surcharge) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.capacity = capacity;
        this.surcharge = surcharge;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTableId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeats() {
        return capacity; // Alias
    }

    public double getSurcharge() {
        return surcharge;
    }

    public TableStatus getStatus() {
        return status;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSeats(int seats) {
        this.capacity = seats; // Alias
    }

    public void setSeatCount(int newSeats) {
        this.capacity = newSeats; // Alias
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
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

    // Utility methods
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

    @Override
    public String toString() {
        return String.format("Table[%s] #%d | Type: %s | Capacity: %d | Surcharge: %.0f₫ | Status: %s",
                id, number, type, capacity, surcharge, status);
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