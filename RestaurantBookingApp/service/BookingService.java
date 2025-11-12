package service;

import model.*;
import java.util.*;
import java.time.LocalDateTime;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Table> tables = new ArrayList<>();

    public BookingService() {
        tables.add(new StandardTable(4, 0));      // Bàn thường 4 ghế
        tables.add(new StandardTable(6, 20000));  // Bàn VIP 6 ghế, phụ thu 20k
        tables.add(new StandardTable(2, 0));      // Bàn nhỏ 2 ghế
    }

    // 🪑 Xem danh sách bàn hiện có
    public void displayTables() {
        System.out.println("📋 DANH SÁCH BÀN:");
        for (int i = 0; i < tables.size(); i++) {
            Table t = tables.get(i);
            System.out.println((i + 1) + ". " + t.getType() + " (" + t.getSeats() + " chỗ), Phụ thu: " + t.getSurcharge() + "₫, ID: " + t.getId());
        }
    }

    // 🔎 Tìm bàn theo ID
    public Table findTableById(String tableId) {
        for (Table t : tables) {
            if (t.getId().equals(tableId)) {
                return t;
            }
        }
        return null;
    }

    // ✅ Đặt bàn mới
    public Booking bookTable(Customer customer, String tableId, String date, String time) throws Exception {
        Table table = findTableById(tableId);
        if (table == null)
            throw new TableNotFoundException("❌ Không tìm thấy bàn có ID: " + tableId);

        if (isTableBooked(tableId, date, time))
            throw new TableAlreadyBookedException("⚠️ Bàn này đã được đặt vào thời điểm " + date + " " + time);

        Booking booking = new Booking(customer, table, date, time);
        bookings.add(booking);
        System.out.println("✅ Đặt bàn thành công cho khách: " + customer.getName());
        return booking;
    }

    // 🗓️ Kiểm tra xem bàn đã được đặt chưa
    private boolean isTableBooked(String tableId, String date, String time) {
        for (Booking b : bookings) {
            if (b.getTable().getId().equals(tableId)
                    && b.getDate().equals(date)
                    && b.getTime().equals(time)
                    && !b.getStatus().equalsIgnoreCase("Cancelled")) {
                return true;
            }
        }
        return false;
    }

    // ❌ Hủy đặt bàn
    public boolean cancelBooking(String bookingId) throws BookingNotFoundException {
        for (Booking b : bookings) {
            if (b.getId().equals(bookingId)) {
                b.setStatus("Cancelled");
                System.out.println("🗑️ Đã hủy đặt bàn cho khách " + b.getCustomer().getName());
                return true;
            }
        }
        throw new BookingNotFoundException("❌ Không tìm thấy mã đặt bàn: " + bookingId);
    }

    // 🧾 Xem danh sách tất cả các booking
    public void displayBookings() {
        if (bookings.isEmpty()) {
            System.out.println("📭 Hiện chưa có đơn đặt bàn nào.");
            return;
        }
        System.out.println("📅 DANH SÁCH ĐẶT BÀN:");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    // Getter
    public List<Booking> getBookings() {
        return bookings;
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Booking> loadBookings() {
        return new ArrayList<>();
    }
}
