package model;

import java.io.Serializable;

public class Booking implements Serializable {
    // Field static để tự sinh ID dạng bXXX (b001, b002, ...)
    private static int idCounter = 1;  // Khởi tạo từ 1, sẽ tự động cập nhật khi load dữ liệu

    private String id;
    private Customer customer;
    private Table table;
    private String date;
    private String time;
    private int guests;
    private String status; // "PENDING", "CONFIRMED", "COMPLETED", "CANCELLED"

    // Constructor 1: Tự động tạo ID dạng bXXX (dùng khi tạo booking mới)
    public Booking(Customer customer, Table table, String date, String time) {
        this.id = "b" + String.format("%03d", idCounter++);  // Tự sinh ID: b001, b002, ...
        this.customer = customer;
        this.table = table;
        this.date = date;
        this.time = time;
        this.guests = table.getCapacity();
        this.status = "PENDING";
    }

    // Constructor 2: Chỉ định ID (dùng khi load từ file CSV, giữ nguyên để tương thích)
    public Booking(String id, Customer customer, Table table, String date, String time, int guests, String status) {
        this.id = id;
        this.customer = customer;
        this.table = table;
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.status = status;
        // Cập nhật counter nếu ID load lớn hơn counter hiện tại (để tránh trùng khi tạo mới)
        if (id.startsWith("b") && id.length() > 1) {
            try {
                int num = Integer.parseInt(id.substring(1));
                if (num >= idCounter) {
                    idCounter = num + 1;
                }
            } catch (NumberFormatException e) {
                // Bỏ qua nếu không parse được (ví dụ: ID không đúng định dạng)
            }
        }
    }

    // Method static tùy chọn để reset counter (dùng khi test hoặc khởi tạo lại hệ thống)
    public static void resetIdCounter() {
        idCounter = 1;
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
