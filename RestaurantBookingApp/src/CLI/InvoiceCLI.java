package CLI;

import model.Booking;
import model.Invoice;
import model.MenuItem;
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

    private static final String INVOICE_FILE = "data/invoices.csv";


    public InvoiceCLI(InvoiceService invoiceService,
                      BookingService bookingService,
                      MenuService menuService) {

        this.invoiceService = invoiceService;
        this.bookingService = bookingService;
        this.menuService = menuService;
    }


    public void menu() {
        int choice;
        do {
            System.out.println("\n=== QUẢN LÝ HÓA ĐƠN ===");
            System.out.println("1. Tạo hóa đơn mới");
            System.out.println("2. Danh sách hóa đơn");
            System.out.println("3. Xuất hóa đơn ra CSV");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1 -> createInvoice();
                case 2 -> listInvoices();
                case 3 -> exportInvoices();
                case 0 -> System.out.println("↩ Quay lại menu chính...");
                default -> System.out.println(" Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }


    private int readInt() {
        try { return Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return -1; }
    }


    private void createInvoice() {

        System.out.println("\n[TẠO HÓA ĐƠN MỚI]");

        System.out.print("Nhập mã bookingId: ");
        String bookingId = sc.nextLine();

        Booking booking = bookingService.findBookingById(bookingId);
        if (booking == null) {
            System.out.println(" Không tìm thấy booking!");
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
                System.out.println(" Đã thêm món: " + item.getName());
            } else {
                System.out.println(" Không tìm thấy món!");
            }

            System.out.print("Thêm món nữa? (y/n): ");
            addMore = sc.nextLine();

        } while (addMore.equalsIgnoreCase("y"));


        double total = 0;
        for (MenuItem m : orderedItems) {
            total += m.getPrice() * (1 - m.getDiscount());
        }

        String invoiceId = "INV" + (invoiceService.getInvoices().size() + 1);

        Invoice invoice = new Invoice(invoiceId, bookingId, orderedItems, total);
        invoiceService.addInvoice(invoice);

        System.out.println("\n TẠO HÓA ĐƠN THÀNH CÔNG!");
        System.out.println(invoice);

        invoiceService.saveInvoices(INVOICE_FILE);
    }


    private void listInvoices() {

        System.out.println("\n[DANH SÁCH HÓA ĐƠN]:");

        List<Invoice> invoices = invoiceService.getInvoices();

        if (invoices.isEmpty()) {
            System.out.println(" Chưa có hóa đơn nào.");
            return;
        }

        invoices.forEach(System.out::println);
    }


    private void exportInvoices() {
        invoiceService.saveInvoices(INVOICE_FILE);
        System.out.println(" Đã xuất hóa đơn ra: " + INVOICE_FILE);
    }
}
