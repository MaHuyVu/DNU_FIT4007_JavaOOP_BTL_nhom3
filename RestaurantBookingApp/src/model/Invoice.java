 package model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Invoice implements Serializable {
    private String id;
    private Booking booking;
    private List<MenuItem> items;
    private double total;

    public Invoice(Booking booking, List<MenuItem> items) {
        this.id = UUID.randomUUID().toString();
        this.booking = booking;
        this.items = items;
        calculateTotal();
    }

    private void calculateTotal() {
        total = 0;
        for (MenuItem item : items)
            total += item.getFinalPrice();
        total += booking.getTable().getSurcharge();
    }

    public double getTotal() { return total; }

    @Override
    public String toString() {
        return String.format("Invoice[%s] Total: %.0fđ (Table surcharge included)", id, total);
    }
}
