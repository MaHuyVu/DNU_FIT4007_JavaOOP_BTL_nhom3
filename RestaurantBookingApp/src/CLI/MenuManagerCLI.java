package CLI;

import model.Table;
import service.*;

import java.util.List;
import java.util.Scanner;

public class MenuManagerCLI {

    private final Scanner sc = new Scanner(System.in);

    private MenuService menuService = new MenuService();
    private TableService tableService = new TableService();
    private BookingService bookingService;
    private InvoiceService invoiceService = new InvoiceService();
    private ReportService reportService = new ReportService();

    private TableCLI tableCLI;
    private MenuItemCLI menuItemCLI;
    private BookingCLI bookingCLI;
    private InvoiceCLI invoiceCLI;
    private ReportCLI reportCLI;

    private List<Table> tables; // ✅ ĐỔI TÊN từ BookingService sang tables

    public void start() {
        // Load dữ liệu từ file
        tables = tableService.loadTables("data/tables.csv");
        menuService.loadMenu("data/menu.csv");
        invoiceService.loadInvoices("data/invoices.csv");

        bookingService = new BookingService(tables);
        bookingService.loadBookings("data/bookings.csv");

        // Khởi tạo các CLI
        tableCLI = new TableCLI(tableService);
        menuItemCLI = new MenuItemCLI(menuService);
        bookingCLI = new BookingCLI(tables); // ✅ TRUYỀN tables thay vì BookingService
        invoiceCLI = new InvoiceCLI(invoiceService, bookingService, menuService);
        reportCLI = new ReportCLI(reportService, invoiceService, menuService);

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
            System.out.println("6. Tìm kiếm nâng cao");
            System.out.println("0. Thoát");
            System.out.print("➜ Chọn chức năng: ");
            choice = readInt();

            switch (choice) {
                case 1 -> tableCLI.menu();
                case 2 -> menuItemCLI.run();
                case 3 -> bookingCLI.menu();
                case 4 -> invoiceCLI.menu();
                case 5 -> reportCLI.menu();
                case 6 -> advancedSearch();
                case 0 -> System.out.println("👋 Đang thoát chương trình...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
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

    private void advancedSearch() {
        System.out.println("\n----- TÌM KIẾM NÂNG CAO -----");
        System.out.println("1. Tìm theo khoảng giá");
        System.out.println("2. Tìm theo mức giảm giá tối thiểu");
        System.out.println("3. Tìm theo từ khóa + loại món (FOOD/DRINK)");
        System.out.print("Chọn: ");
        int choice = readInt();

        switch (choice) {
            case 1 -> searchByPriceRange();
            case 2 -> searchByDiscount();
            case 3 -> searchKeywordAndType();
            default -> System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    }

    private void searchByPriceRange() {
        System.out.print("Giá thấp nhất: ");
        double min = Double.parseDouble(sc.nextLine());

        System.out.print("Giá cao nhất: ");
        double max = Double.parseDouble(sc.nextLine());

        var results = menuService.searchByPriceRange(min, max);

        if (results.isEmpty()) {
            System.out.println("❌ Không tìm thấy món nào trong khoảng giá!");
        } else {
            System.out.println("\n✅ KẾT QUẢ TÌM KIẾM:");
            results.forEach(System.out::println);
        }
    }

    private void searchByDiscount() {
        System.out.print("Nhập mức giảm giá tối thiểu (vd 0.1 = 10%): ");
        double discount = Double.parseDouble(sc.nextLine());

        var results = menuService.searchByMinDiscount(discount);

        if (results.isEmpty()) {
            System.out.println("❌ Không có món nào đủ mức giảm giá!");
        } else {
            System.out.println("\n✅ KẾT QUẢ TÌM KIẾM:");
            results.forEach(System.out::println);
        }
    }

    private void searchKeywordAndType() {
        System.out.print("Nhập từ khóa: ");
        String keyword = sc.nextLine();

        System.out.print("Nhập loại (FOOD/DRINK): ");
        String type = sc.nextLine();

        var results = menuService.searchByKeywordAndType(keyword, type);

        if (results.isEmpty()) {
            System.out.println("❌ Không có món phù hợp!");
        } else {
            System.out.println("\n✅ KẾT QUẢ TÌM KIẾM:");
            results.forEach(System.out::println);
        }
    }

    public void run() {
        start();
    }
}