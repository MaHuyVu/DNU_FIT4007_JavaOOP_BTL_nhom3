package service;

import model.Booking;
import model.Invoice;
import model.MenuItem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    private final List<Invoice> invoices = new ArrayList<>();

    /**
     * Tạo hóa đơn từ một booking và danh sách món.
     * Trả về Invoice vừa tạo.
     */
    public Invoice createInvoice(Booking booking, List<MenuItem> items) {
        double total = 0;
        if (items != null) {
            for (MenuItem item : items) {
                // dùng alias getDiscountedPrice() để tương thích với tên cũ
                total += item.getDiscountedPrice();
            }
        }
        // cộng phụ phí bàn nếu có
        total += booking.getTable().getSurcharge();

        Invoice invoice = new Invoice(booking, items == null ? new ArrayList<>() : new ArrayList<>(items), total);
        invoices.add(invoice);
        System.out.println("🧾 Hóa đơn được tạo cho " + booking.getCustomer().getName()
                + " | Total: " + (long) total + "₫");
        return invoice;
    }

    /**
     * Xuất tất cả hóa đơn hiện có ra file CSV.
     * Format header: id,customerName,tableId,createdAt,total,itemIds
     */
    public void exportToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("id,customerName,tableId,createdAt,total,itemIds\n");
            for (Invoice inv : invoices) {
                writer.append(inv.toCsvLine()).append("\n");
            }
            System.out.println("📁 Đã xuất " + invoices.size() + " hóa đơn ra " + filename);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file CSV: " + e.getMessage());
        }
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
    }

    public void saveInvoices(String invoiceFile) {
    }

    public void loadInvoices(String s) {
    }
}
