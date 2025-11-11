package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {
    private final List<Table> tables;
    private final List<Booking> bookings;

    public BookingService(List<Table> tables) {
        this.tables = tables;
        this.bookings = new ArrayList<>();
    }

    // ------------------ ĐẶT BÀN ------------------
    public Booking bookTable(Customer customer, String tableId, String date, String time)
            throws TableAlreadyBookedException, TableNotFoundException {

        Table table = findTableById(tableId);
        if (table == null)
            throw new TableNotFoundException("Không tìm thấy bàn có ID: " + tableId);

        if (isTableBooked(tableId, date, time))
            throw new TableAlreadyBookedException("❌ Bàn này đã được đặt vào " + date + " lúc " + time);

        Booking booking = new Booking(customer, table, date, time);
        bookings.add(booking);
        System.out.println("✅ Đặt bàn thành công cho " + customer.getName());
        return booking;
    }

    // ------------------ HỦY ĐẶT BÀN ------------------
    public void cancelBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Cancelled");
        System.out.println("❌ Hủy đặt bàn thành công cho khách " + booking.getCustomer().getName());
    }

    // ------------------ XÁC NHẬN ĐẶT BÀN ------------------
    public void confirmBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Confirmed");
        System.out.println("✅ Xác nhận đặt bàn cho " + booking.getCustomer().getName());
    }

    // ------------------ HOÀN TẤT BÀN ------------------
    public void completeBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Completed");
        System.out.println("🍽️ Bàn của " + booking.getCustomer().getName() + " đã hoàn tất!");
    }

    // ------------------ KIỂM TRA TRÙNG ------------------
    private boolean isTableBooked(String tableId, String date, String time) {
        for (Booking b : bookings) {
            if (b.getTable().getId().equals(tableId)
                    && b.getDate().equals(date)
                    && b.getTime().equals(time)
                    && !b.getStatus().equals("Cancelled")) {
                return true;
            }
        }
        return false;
    }

    // ------------------ HÀM HỖ TRỢ ------------------
    private Table findTableById(String tableId) {
        for (Table t : tables) {
            if (t.getId().equals(tableId))
                return t;
        }
        return null;
    }

    private Booking findBookingById(String bookingId) {
        for (Booking b : bookings) {
            if (b.getId().equals(bookingId))
                return b;
        }
        return null;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void listAllBookings() {
        System.out.println("=== DANH SÁCH ĐẶT BÀN ===");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }
}
