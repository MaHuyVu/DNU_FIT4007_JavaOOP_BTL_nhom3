package CLI;

import model.Booking;
import model.Table;
import service.BookingService;

import java.util.List;
import java.util.Scanner;

public class BookingCLI {

    private final Scanner sc = new Scanner(System.in);
    private final BookingService bookingService;
    private final String FILE_PATH = "data/bookings.csv";

    public BookingCLI(List<Table> tables) {
        this.bookingService = new BookingService(tables);
    }

    private int readInt() {
        try { return Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return -1; }
    }

    public void menu() {


        bookingService.loadBookings(FILE_PATH);

        int choice;
        do {
            System.out.println("\n=== ĐẶT BÀN TRỰC TUYẾN ===");
            System.out.println("1. Đặt bàn mới");
            System.out.println("2. Hủy đặt bàn");
            System.out.println("3. Danh sách đặt bàn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1 -> createBooking();
                case 2 -> cancelBooking();
                case 3 -> listBookings();
                case 0 -> System.out.println("↩ Quay lại menu chính...");
                default -> System.out.println(" Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }



    private void createBooking() {
        System.out.println("\n[Tạo đặt bàn mới]");

        System.out.print("Tên khách hàng: ");
        String name = sc.nextLine();

        System.out.print("Số điện thoại: ");
        String phone = sc.nextLine();

        System.out.print("Mã bàn muốn đặt: ");
        String tableId = sc.nextLine();

        System.out.print("Ngày (yyyy-MM-dd): ");
        String date = sc.nextLine();

        System.out.print("Giờ (HH:mm): ");
        String time = sc.nextLine();

        try {
            Booking booking = bookingService.bookTable(name, phone, tableId, date, time);
            System.out.println("✔ Đặt bàn thành công!");
            System.out.println(booking);


            bookingService.saveBookings(FILE_PATH);

        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }
    }


    private void cancelBooking() {
        System.out.println("\n[Hủy đặt bàn]");
        System.out.print("Nhập mã đặt bàn cần hủy: ");
        String bookingId = sc.nextLine();

        try {
            bookingService.cancelBooking(bookingId);
            System.out.println("✔ Đã hủy đặt bàn thành công!");


            bookingService.saveBookings(FILE_PATH);

        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }
    }



    private void listBookings() {
        System.out.println("\n DANH SÁCH ĐẶT BÀN:");
        List<Booking> bookings = bookingService.getBookings();

        if (bookings.isEmpty()) {
            System.out.println("Không có dữ liệu đặt bàn!");
        } else {
            bookings.forEach(System.out::println);
        }
    }
}
