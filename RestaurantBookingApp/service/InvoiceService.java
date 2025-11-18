package service;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    private final List<Invoice> invoices = new ArrayList<>();

    public Invoice createInvoice(Booking booking, List<MenuItem> items) {
        double total = 0;
        if (items != null) {
            for (MenuItem item : items) {
                total += item.getDiscountedPrice();
            }
        }
        // Cộng phụ phí bàn nếu có
        total += booking.getTable().getSurcharge();

        Invoice invoice = new Invoice(booking.getId(), items, total);
        invoices.add(invoice);
        System.out.println("🧾 Hóa đơn được tạo cho " + booking.getCustomer().getName()
                + " | Total: " + String.format("%.0f", total) + "₫");
        return invoice;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void saveInvoices(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("id,bookingId,items,total,discount,finalAmount,date\n");

            for (Invoice inv : invoices) {
                String itemsStr = "";
                if (inv.getItems() != null && !inv.getItems().isEmpty()) {
                    itemsStr = inv.getItems().stream()
                            .map(MenuItem::getId)
                            .reduce((a, b) -> a + "|" + b)
                            .orElse("");
                }

                String line = String.format("%s,%s,%s,%.0f,%.0f,%.0f,%s\n",
                        inv.getId(),
                        inv.getBookingId(),
                        itemsStr,
                        inv.getTotalAmount(),
                        0.0,
                        inv.getTotalAmount(),
                        LocalDate.now().toString()
                );
                bw.write(line);
            }
            System.out.println("✅ Lưu " + invoices.size() + " hóa đơn thành công.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi lưu invoice: " + e.getMessage());
        }
    }

    public void loadInvoices(String filePath) {
        invoices.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 7) continue;

                String id = data[0].trim();
                String bookingId = data[1].trim();
                String itemsStr = data[2].trim();
                double total = Double.parseDouble(data[3].trim());

                // Parse items (cần MenuService để load đầy đủ)
                List<MenuItem> items = new ArrayList<>();

                Invoice invoice = new Invoice(id, bookingId, items, total);
                invoices.add(invoice);
            }
            System.out.println("✅ Load " + invoices.size() + " invoice từ file.");
        } catch (FileNotFoundException e) {
            System.out.println("⚠ File không tồn tại: " + filePath);
        } catch (Exception e) {
            System.out.println("❌ Lỗi load invoice: " + e.getMessage());
        }
    }

    public void exportToCSV(String filename) {
        saveInvoices(filename);
    }
}