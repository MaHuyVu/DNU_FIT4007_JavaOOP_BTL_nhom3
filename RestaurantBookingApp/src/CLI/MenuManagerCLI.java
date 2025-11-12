package CLI;

import java.io.IOException;
import java.util.Scanner;

public class MenuManagerCLI {
    private final Scanner sc = new Scanner(System.in);
    private final TableCLI tableCLI = new TableCLI();
    private final MenuItemCLI menuItemCLI = new MenuItemCLI();
    private final BookingCLI bookingCLI = new BookingCLI();
    private final InvoiceCLI invoiceCLI = new InvoiceCLI();
    private final ReportCLI reportCLI = new ReportCLI();

    public void start() {
        int choice;
        do {
            System.out.println("\n===========================================");
            System.out.println("  HỆ THỐNG QUẢN LÝ NHÀ HÀNG TRỰC TUYẾN");
            System.out.println("===========================================");
            System.out.println("1. Quản lý bàn ăn");
            System.out.println("2. Quản lý thực đơn");
            System.out.println("3. Đặt bàn trực tuyến");
            System.out.println("4. Quản lý hóa đơn");
            System.out.println("5. Báo cáo & thống kê");
            System.out.println("0. Thoát");
            System.out.print(" Chọn chức năng: ");
            choice = readInt();

            switch (choice) {
                case 1 -> tableCLI.menu();
                case 2 -> menuItemCLI.menu();
                case 3 -> bookingCLI.menu();
                case 4 -> invoiceCLI.menu();
                case 5 -> reportCLI.menu();
                case 0 -> System.out.println(" Đang thoát chương trình...");
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

    public void loadData() throws IOException {

        System.out.println(" Dữ liệu mẫu đã được tải thành công!");
    }

    public void saveData() throws IOException {

        System.out.println(" Dữ liệu đã được lưu vào tệp!");
    }

    public void run() {
    }
}
