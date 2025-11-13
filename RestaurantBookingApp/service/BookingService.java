package service;

import model.*;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Table> tables = new ArrayList<>();

    public BookingService() {
        tables.add(new StandardTable(4, 0));      
        tables.add(new StandardTable(6, 20000));  
        tables.add(new StandardTable(2, 0));      
    }


    public void displayTables() {
        System.out.println(" DANH SÁCH BÀN:");
        for (int i = 0; i < tables.size(); i++) {
            Table t = tables.get(i);
            System.out.println((i + 1) + ". " + t.getType() + " (" + t.getSeats() + " chỗ), Phụ thu: " + t.getSurcharge() + "₫, ID: " + t.getId());
        }
    }

    public Table findTableById(String tableId) {
        for (Table t : tables) {
            if (t.getId().equals(tableId)) {
                return t;
            }
        }
        return null;
    }

 
    public Booking bookTable(Customer customer, String tableId, String date, String time) throws Exception {
        Table table = findTableById(tableId);
        if (table == null)
            throw new TableNotFoundException(" Không tìm thấy bàn có ID: " + tableId);

        if (isTableBooked(tableId, date, time))
            throw new TableAlreadyBookedException(" Bàn này đã được đặt vào " + date + " lúc " + time);

        Booking booking = new Booking(customer, table, date, time);
        bookings.add(booking);
        System.out.println(" Đặt bàn thành công cho " + customer.getName());
        return booking;
    }
    public List<Booking> loadBookings(String filePath) {
        bookings.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine(); 
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
                    System.out.println("⚠ Bỏ qua booking " + id + " (không tìm thấy bàn " + tableId + ")");
                    continue;
                }

              
                Customer customer = new Customer(customerId);


                Booking booking = new Booking(id, customer, table, date, time, guests, status);

                bookings.add(booking);
            }

            System.out.println(" Load " + bookings.size() + " booking từ file.");
        } catch (Exception e) {
            System.out.println(" Lỗi load booking: " + e.getMessage());
        }

        return bookings;
    }



    public void cancelBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Cancelled");
        System.out.println(" Hủy đặt bàn thành công cho khách " + booking.getCustomer().getName());
    }


    public void confirmBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Confirmed");
        System.out.println(" Xác nhận đặt bàn cho " + booking.getCustomer().getName());
    }


    public void completeBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = findBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Không tìm thấy mã đặt bàn: " + bookingId);

        booking.setStatus("Completed");
        System.out.println(" Bàn của " + booking.getCustomer().getName() + " đã hoàn tất!");
    }


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

 
    public boolean cancelBooking(String bookingId) throws BookingNotFoundException {
        for (Booking b : bookings) {
            if (b.getId().equals(bookingId)) {
                b.setStatus("Cancelled");
                System.out.println("🗑️ Đã hủy đặt bàn cho khách " + b.getCustomer().getName());
                return true;
            }
        }
        throw new BookingNotFoundException(" Không tìm thấy mã đặt bàn: " + bookingId);
    }

   
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

   
    public List<Booking> getBookings() {
        return bookings;
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Booking> loadBookings() {
        return new ArrayList<>();
    }

    public List<Booking> loadBookings() {
    }

    public Booking bookTable(String name, String phone, String tableId, String date, String time) {
    }
}
