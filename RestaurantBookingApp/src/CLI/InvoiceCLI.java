package CLI;

import model.Booking;
import model.Invoice;
import model.MenuItem;
import model.Table;
import service.BookingService;
import service.InvoiceService;
import service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceCLI {

    private final Scanner sc = new Scanner(System.in);
    private final InvoiceService invoiceService;
    private final BookingService bookingService;
    private final MenuService menuService;

    private final String INVOICE_FILE = "data/invoices.csv";

    public InvoiceCLI(List<Table> tables) {
        this.invoiceService = new InvoiceService();
        this.bookingService = new BookingService(tables);
        this.menuService = new MenuService();


        invoiceService.loadInvoices(INVOICE_FILE);
        menuService.loadMenu("data/menu.csv");
        bookingService.loadBookings("data/bookings.csv");
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n===  QUẢN LÝ HÓA ĐƠN ===");
            System.out.println("1. Tạo hóa đơn mới");
            System.out.println("2. Danh sách hóa đơn");
            System.out.println("3. Xuất hóa đơn ra file CSV");
            System.out.println("0. Quay lại");
            System.out.print(" Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1 -> createInvoice();
                case 2 -> listInvoices();
                case 3 -> exportInvoice();
                case 0 -> System.out.println("↩ Quay lại menu chính...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private int readInt() {
        try { return Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return -1; }
    }


    private void createInvoice() {
        System.out.println("\n[TẠO HÓA ĐƠN MỚI]");

        System.out.print("Nhập mã đặt bàn (bookingId): ");
        String bookingId = sc.nextLine();

        Booking booking = bookingService.findBookingById(bookingId);
        if (booking == null) {
            System.out.println("❌ Không tìm thấy booking!");
            return;
        }

        List<MenuItem> orderedItems = new ArrayList<>();
        String addMore;

        do {
            System.out.print("Nhập ID món: ");
            String itemId = sc.nextLine();
            MenuItem item = menuService.findById(itemId);

            if (item != null) {
                orderedItems.add(item);
                System.out.println("✔ Đã thêm: " + item.getName());
            } else {
                System.out.println("❌ Không tìm thấy món!");
            }

            System.out.print("Thêm món nữa? (y/n): ");
            addMore = sc.nextLine();

        } while (addMore.equalsIgnoreCase("y"));


        double total = 0;
        for (MenuItem m : orderedItems) {
            total += m.getPrice() * (1 - m.getDiscount());
        }


        String invoiceId = "HD" + (invoiceService.getInvoices().size() + 1);

        Invoice invoice = new Invoice(invoiceId, bookingId, orderedItems, total);
        invoiceService.addInvoice(invoice);

        System.out.println("🎉 TẠO HÓA ĐƠN THÀNH CÔNG!");
        System.out.println(invoice);

        invoiceService.saveInvoices(INVOICE_FILE);
    }


    private void listInvoices() {
        System.out.println("\n[DANH SÁCH HÓA ĐƠN]:");

        List<Invoice> invoices = invoiceService.getInvoices();
        if (invoices.isEmpty()) {
            System.out.println("Chưa có hóa đơn nào.");
            return;
        }

        invoices.forEach(System.out::println);
    }


    private void exportInvoice() {
        invoiceService.saveInvoices(INVOICE_FILE);
        System.out.println("📁 Đã xuất hóa đơn ra file: " + INVOICE_FILE);
    }
}
