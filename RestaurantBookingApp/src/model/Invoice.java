package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Invoice {

    private String id;                    // Mã hóa đơn
    private String bookingId;             // Mã đặt bàn
    private List<MenuItem> items;         // Danh sách món đã gọi
    private double totalAmount;           // Tổng tiền
    private char[] total;
    private char[] discount;
    private char[] finalAmount;
    private LocalDate invoiceDate;        // Đổi từ char[] sang LocalDate

    // ============================
    //  CONSTRUCTOR
    // ============================
    public Invoice(String id, Map<String, Integer> bookingId, List<MenuItem> items, double totalAmount) {
        this.id = id;
        this.bookingId = bookingId.toString();
        this.items = items;  // Sửa: items giờ là List<MenuItem>
        this.totalAmount = totalAmount;
    }

    public Invoice(Booking booking, String bookingId, double total) {
        this.id = "INV-" + System.currentTimeMillis(); // Tự động tạo ID
        this.bookingId = bookingId;
        this.items = null; // Hoặc new ArrayList<>()
        this.totalAmount = total;
    }

    public Invoice(String invoiceId, String bookingId, List<MenuItem> orderedItems, double total) {
        this.id = invoiceId;
        this.bookingId = bookingId;
        this.items = orderedItems;
        this.totalAmount = total;
    }

    // ============================
    //  GETTER - SETTER
    // ============================
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

    // ============================
    //  HÀM TÍNH TỔNG TIỀN (nếu cần tính lại)
    // ============================
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

    // ============================
    //  HIỂN THỊ HÓA ĐƠN
    // ============================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------------------\n");
        sb.append("HÓA ĐƠN: ").append(id).append("\n");
        sb.append("ĐẶT BÀN: ").append(bookingId).append("\n");
        sb.append("MÓN GỌI:\n");

        if (items != null && !items.isEmpty()) {
            for (MenuItem item : items) {
                sb.append(" - ").append(item.getName())
                        .append(" | Giá: ").append(item.getPrice())
                        .append(" | Giảm: ").append(item.getDiscount() * 100).append("%\n");
            }
        } else {
            sb.append(" (Chưa có món)\n");
        }

        sb.append("TỔNG TIỀN: ").append(totalAmount).append(" VND\n");
        sb.append("-------------------------------\n");

        return sb.toString();
    }

    // ============================
    //  CHUYỂN ĐỔI SANG DÒNG CSV
    // ============================
    public String toCsvLine() {
        // Format: id,bookingId,totalAmount,itemIds
        String itemIds = "";
        if (items != null && !items.isEmpty()) {
            itemIds = items.stream()
                    .map(MenuItem::getId)
                    .collect(Collectors.joining(";"));
        }

        return String.format("%s,%s,%.2f,%s",
                id,
                bookingId,
                totalAmount,
                itemIds);
    }

    public Map<Object, Object> getOrderItems() {
        Map<Object, Object> orderItems = new HashMap<>();
        if (items != null) {
            for (MenuItem item : items) {
                orderItems.put(item.getId(), item);
            }
        }
        return orderItems;
    }

    public char[] getTotal() {
        return total;
    }

    public char[] getDiscount() {
        return discount;
    }

    public char[] getFinalAmount() {
        return finalAmount;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}