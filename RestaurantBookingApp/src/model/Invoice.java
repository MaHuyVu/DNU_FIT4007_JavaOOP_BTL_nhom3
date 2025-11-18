package model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Invoice {

    private String id;
    private String bookingId;
    private List<MenuItem> items;
    private double totalAmount;
    private LocalDate invoiceDate;

    // Constructor 1: Tự động tạo ID
    public Invoice(String bookingId, List<MenuItem> items, double totalAmount) {
        this.id = "INV-" + System.currentTimeMillis();
        this.bookingId = bookingId;
        this.items = items != null ? items : new ArrayList<>();
        this.totalAmount = totalAmount;
        this.invoiceDate = LocalDate.now();
    }

    // Constructor 2: Với ID cụ thể (khi load từ file)
    public Invoice(String id, String bookingId, List<MenuItem> items, double totalAmount) {
        this.id = id;
        this.bookingId = bookingId;
        this.items = items != null ? items : new ArrayList<>();
        this.totalAmount = totalAmount;
        this.invoiceDate = LocalDate.now();
    }

    // Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    // Tính toán tổng tiền
    public double calculateTotal() {
        double sum = 0;
        if (items != null) {
            for (MenuItem m : items) {
                sum += m.getPrice() * (1 - m.getDiscount());
            }
        }
        this.totalAmount = sum;
        return sum;
    }

    // Compatibility methods
    public double getTotal() {
        return totalAmount;
    }

    public double getDiscount() {
        return 0.0;
    }

    public double getFinalAmount() {
        return totalAmount;
    }

    public Map<String, Integer> getOrderItems() {
        Map<String, Integer> orderItems = new HashMap<>();
        if (items != null) {
            for (MenuItem item : items) {
                orderItems.put(item.getId(), 1);
            }
        }
        return orderItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------------------\n");
        sb.append("HÓA ĐƠN: ").append(id).append("\n");
        sb.append("ĐẶT BÀN: ").append(bookingId).append("\n");
        sb.append("NGÀY: ").append(invoiceDate).append("\n");
        sb.append("MÓN GỌI:\n");

        if (items != null && !items.isEmpty()) {
            for (MenuItem item : items) {
                sb.append(" - ").append(item.getName())
                        .append(" | Giá: ").append(String.format("%.0f", item.getPrice()))
                        .append(" | Giảm: ").append(String.format("%.0f", item.getDiscount() * 100)).append("%\n");
            }
        } else {
            sb.append(" (Chưa có món)\n");
        }

        sb.append("TỔNG TIỀN: ").append(String.format("%.0f", totalAmount)).append(" VND\n");
        sb.append("-------------------------------\n");

        return sb.toString();
    }

    public String toCsvLine() {
        String itemIds = "";
        if (items != null && !items.isEmpty()) {
            itemIds = items.stream()
                    .map(MenuItem::getId)
                    .collect(Collectors.joining(";"));
        }

        return String.format("%s,%s,%.2f,%s,%s",
                id,
                bookingId,
                totalAmount,
                itemIds,
                invoiceDate.toString());
    }
}