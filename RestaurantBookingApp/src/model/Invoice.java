package model;

import java.util.List;

public class Invoice {

    private String id;                    // Mã hóa đơn
    private String bookingId;             // Mã đặt bàn
    private List<MenuItem> items;         // Danh sách món đã gọi
    private double totalAmount;           // Tổng tiền

    // ============================
    //  CONSTRUCTOR
    // ============================
    public Invoice(String id, String bookingId, List<MenuItem> items, double totalAmount) {
        this.id = id;
        this.bookingId = bookingId;
        this.items = items;
        this.totalAmount = totalAmount;
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

        for (MenuItem item : items) {
            sb.append(" - ").append(item.getName())
                    .append(" | Giá: ").append(item.getPrice())
                    .append(" | Giảm: ").append(item.getDiscount() * 100).append("%\n");
        }

        sb.append("TỔNG TIỀN: ").append(totalAmount).append(" VND\n");
        sb.append("-------------------------------\n");

        return sb.toString();
    }
}
