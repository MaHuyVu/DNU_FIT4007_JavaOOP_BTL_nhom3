package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class Invoice implements Serializable {
    private String id;
    private Booking booking;
    private List<MenuItem> items;
    private double total;
    private String createdAt; // lưu chuỗi yyyy-MM-dd'T'HH:mm

    public Invoice(String id, Booking booking, List<MenuItem> items, double total, LocalDateTime createdAt) {
        this.id = id;
        this.booking = booking;
        this.items = items;
        this.total = total;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

    // helper constructor
    public Invoice(Booking booking, List<MenuItem> items, double total) {
        this(UUID.randomUUID().toString(), booking, items, total, LocalDateTime.now());
    }

    public String getId(){ return id; }
    public Booking getBooking(){ return booking; }
    public List<MenuItem> getItems(){ return items; }
    public double getTotal(){ return total; }
    public String getCreatedAt(){ return createdAt; }

    // CSV line - include item ids separated by '|'
    public String toCsvLine(){
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",")
                .append(escapeCsv(booking.getCustomer().getName())).append(",")
                .append(booking.getTable().getId()).append(",")
                .append(createdAt).append(",")
                .append((long) total).append(","); // total as integer VND
        // item ids
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).getId());
            if (i < items.size() - 1) sb.append("|");
        }
        return sb.toString();
    }

    private String escapeCsv(String s){
        if (s == null) return "";
        return s.replace(",", " "); // very simple escape
    }

    @Override
    public String toString(){
        return String.format("Invoice[%s] for %s | Table: %s | Total: %.0f | At: %s",
                id, booking.getCustomer().getName(), booking.getTable().getId(), total, createdAt);
    }
}
