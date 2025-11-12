package service;

import model.Booking;
import model.Customer;
import model.Table;

import java.io.*;
import java.util.*;

public class BookingService {
    private static final String FILE_PATH = "RestaurantBookingApp/data/bookings.csv";

    public List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine(); // bỏ dòng tiêu đề

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String id = parts[0].trim();
                    String customerId = parts[1].trim();
                    String tableId = parts[2].trim();
                    String date = parts[3].trim();
                    String time = parts[4].trim();
                    int guests = Integer.parseInt(parts[5].trim());
                    String status = parts[6].trim();

                    // Tạo các đối tượng tạm (về sau sẽ lấy từ file riêng)
                    Customer customer = new Customer(customerId); // có thể thay = findCustomerById()
                    Table table = new Table(tableId, "Standard", guests);

                    Booking booking = new Booking(customer, table, date, time);
                    booking.setStatus(status);

                    // Gán lại id gốc từ file (đè lên UUID mặc định)
                    booking.setCustomer(customer);
                    booking.setTable(table);

                    // Hack: đặt lại id cho khớp file (vì constructor tự tạo UUID)
                    // → cần bổ sung setter id trong Booking.java
                    try {
                        var idField = Booking.class.getDeclaredField("id");
                        idField.setAccessible(true);
                        idField.set(booking, id);
                    } catch (Exception ignored) {}

                    bookings.add(booking);
                }
            }
        } catch (IOException e) {
            System.err.println("⚠️ Lỗi khi đọc file bookings.csv: " + e.getMessage());
        }

        return bookings;
    }
}
