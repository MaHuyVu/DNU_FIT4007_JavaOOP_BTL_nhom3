package CLI;

import java.util.Scanner;

public class BookingCLI {
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int choice;
        do {
            System.out.println("\n=== ĐẶT BÀN TRỰC TUYẾN ===");
            System.out.println("1. Đặt bàn mới");
            System.out.println("2. Hủy đặt bàn");
            System.out.println("3. Danh sách đặt bàn");
            System.out.println("0. Quay lại");
            System.out.print(" Chọn: ");
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

    private int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void createBooking() { System.out.println(" [Tạo đặt bàn mới ]"); }
    private void cancelBooking() { System.out.println(" [Hủy đặt bàn ]"); }
    private void listBookings() { System.out.println(" [Hiển thị danh sách đặt bàn ]"); }
}
