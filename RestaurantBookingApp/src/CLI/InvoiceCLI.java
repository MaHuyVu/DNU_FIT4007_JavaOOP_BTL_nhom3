package CLI;

import java.util.Scanner;

public class InvoiceCLI {
    private final Scanner sc = new Scanner(System.in);

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

    private void createInvoice() { System.out.println(" [Tạo hóa đơn ]"); }
    private void listInvoices() { System.out.println(" [Hiển thị danh sách hóa đơn ]"); }
    private void exportInvoice() { System.out.println(" [Xuất hóa đơn ra CSV ]"); }
}
