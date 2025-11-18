package model;

import java.io.Serializable;
import java.util.UUID;

public class Booking implements Serializable {
    private String id;
    private Customer customer;
    private Table table;
    private String date;
    private String time;
    private int guests;
    private String status; // "PENDING", "CONFIRMED", "COMPLETED", "CANCELLED"

    // Constructor 1: Tự động tạo ID
    public Booking(Customer customer, Table table, String date, String time) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.table = table;
        this.date = date;
        this.time = time;
        this.guests = table.getCapacity();
        this.status = "PENDING";
    }

    // Constructor 2: Chỉ định ID (dùng khi load từ file)
    public Booking(String id, Customer customer, Table table, String date, String time, int guests, String status) {
        this.id = id;
        this.customer = customer;
        this.table = table;
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.status = status;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Table getTable() {
        return table;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getGuests() {
        return guests;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Booking[%s] Customer: %s, Table: %s (%d seats), Date: %s, Time: %s, Guests: %d, Status: %s",
                id, customer.getName(), table.getId(), table.getCapacity(), date, time, guests, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Booking booking = (Booking) obj;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}