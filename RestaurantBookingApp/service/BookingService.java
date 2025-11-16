package service;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final List<Table> tables;
    private final List<Booking> bookings;

    public BookingService(List<Table> tables) {
        this.tables = tables;
        this.bookings = new ArrayList<>();
    }

    // ---------------------------
    // ĐẶT BÀN CHÍNH
    // ---------------------------
    public Booking bookTable(Customer customer, String tableId, String date, String time)
            throws TableAlreadyBookedException, TableNotFoundException {

        Table table = findTableById(tableId);
        if (table == null)
            throw new TableNotFoundException("Không tìm thấy bàn có ID: " + tableId);

        if (isTableBooked(tableId, date, time))
            throw new TableAlreadyBookedException("Bàn này đã được đặt vào " + date + " lúc " + time);

        Booking booking = new Booking(customer, table, date, time);
        bookings.add(booking);

        return booking;
    }

    // ---------------------------
    // ĐẶT BÀN DẠNG INPUT CƠ BẢN
    // ---------------------------
    public Booking bookTable(String name, String phone, String tableId, String date, String time) {
        Customer customer = new Customer(name, phone);
        Table table = findTableById(tableId);

        if (table == null)
            throw new RuntimeException("Không tìm thấy bàn!");

        Booking booking = new Booking(customer, table, date, time);
        bookings.add(booking);

        return booking;
    }

    // ---------------------------
    // LƯU BOOKING XUỐNG FILE CSV
    // ---------------------------
    public void saveBookings(String filePath) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

            pw.println("id,customerId,tableId,date,time,guests,status");

            for (Booking b : bookings) {
                pw.println(b.getId() + "," +
                        b.getCustomer().getId() + "," +
                        b.getTable().getId() + "," +
                        b.getDate() + "," +
                        b.getTime() + "," +
                        b.getGuests() + "," +
                        b.getStatus());
            }

            System.out.println("✔ Đã lưu booking xuống file.");

        } catch (Exception e) {
            System.out.println("❌ Lỗi lưu file: " + e.getMessage());
        }
    }

    // ---------------------------
    // LOAD BOOKING TỪ FILE CSV
    // ---------------------------
    public List<Booking> loadBookings(String filePath) {
        bookings.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine(); // bỏ header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 7) continue;

                String id = data[0].trim();
                String customerId = data[1].trim();
                String tableId = data[2].trim();
                String date = data[3].trim();
                String time = data[4].trim();
                int guests = Integer.parseInt(data[5].trim());
                String status = data[6].trim();

                Table table = findTableById(tableId);
                if (table == null) {
                    System.out.println("⚠ Không tìm thấy bàn " + tableId + ", bỏ qua booking " + id);
                    continue;
                }

                // tạo customer tạm (vì file chỉ có id)
                Customer customer = new Customer(customerId);

                Booking booking = new Booking(id, customer, table, date, time, guests, status);
                bookings.add(booking);
            }

            System.out.println("✔ Load " + bookings.size() + " booking từ file.");

        } catch (Exception e) {
            System.out.println("❌ Lỗi load file: " + e.getMessage());
        }

        return bookings;
    }

    // phiên bản loadBookings() không tham số
    public List<Booking> loadBookings() {
        return bookings;
    }

    // ---------------------------
    // HỦY – XÁC NHẬN – HOÀN TẤT
    // ---------------------------
    public void cancelBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Cancelled");
    }

    public void confirmBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Confirmed");
    }

    public void completeBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Completed");
    }

    // ---------------------------
    // HÀM TÌM KIẾM
    // ---------------------------

    private Table findTableById(String tableId) {
        for (Table t : tables)
            if (t.getId().equals(tableId))
                return t;
        return null;
    }

    public Booking findBookingById(String bookingId) {
        for (Booking b : bookings)
            if (b.getId().equals(bookingId))
                return b;
        return null;
    }

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

    public List<Booking> getBookings() {
        return bookings;
    }
}
