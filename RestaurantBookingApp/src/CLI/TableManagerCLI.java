package CLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableManagerCLI {
    private List<TableCLI> TableList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Show menu
    public void run() {
        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ BÀN ĂN =====");
            System.out.println("1. Thêm bàn ăn");
            System.out.println("2. Sửa bàn ăn");
            System.out.println("3. Xóa bàn ăn");
            System.out.println("4. Hiển thị danh sách bàn");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Choose: ");
            choice = readInt();

            switch (choice) {
                case 1 -> addTableCLI();
                case 2 -> editTableCLI();
                case 3 -> deleteTableCLI();
                case 4 -> showTableCLIs();
                case 0 -> System.out.println("Quay lại menu chính...");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    // 1️⃣ Add
    private void addTableCLI() {
        System.out.print("Nhập mã bàn: ");
        String id = sc.nextLine();
        System.out.print("Nhập số chỗ ngồi: ");
        int seats = readInt();
        System.out.print("Nhập trạng thái (Trống / Đặt trước / Đang phục vụ): ");
        String status = sc.nextLine();

        TableCLI TableCLI = new TableCLI(id, seats, status);
        TableList.add(TableCLI);
        System.out.println("Thêm bàn ăn thành công!");
    }

    // 2️⃣ Edit
    private void editTableCLI() {
        System.out.print("Nhập mã bàn cần sửa: ");
        String id = sc.nextLine();
        TableCLI t = findTableCLIById(id);

        if (t == null) {
            System.out.println("Không tìm thấy bàn ăn!");
            return;
        }

        System.out.print("Nhập số chỗ ngồi mới: ");
        int newSeats = readInt();
        System.out.print("Nhập trạng thái mới: ");
        String newStatus = sc.nextLine();

        t.setSeatCount(newSeats);
        t.setStatus(newStatus);
        System.out.println(" Cập nhật bàn ăn thành công!");
    }

    // 3️⃣ Delete
    private void deleteTableCLI() {
        System.out.print("Nhập mã bàn cần xóa: ");
        String id = sc.nextLine();
        TableCLI t = findTableCLIById(id);

        if (t != null) {
            TableList.remove(t);
            System.out.println("Xóa bàn ăn thành công!");
        } else {
            System.out.println("Không tìm thấy bàn ăn!");
        }
    }

    // 4️⃣ Display all
    private void showTableCLIs() {
        if (TableList.isEmpty()) {
            System.out.println("Danh sách bàn ăn trống.");
            return;
        }

        System.out.println("\\n===== DANH SÁCH BÀN ĂN =====");
        for (TableCLI t : TableList) {
            System.out.println(t);
        }
        System.out.println("===========================");
    }

    // Helper: find by ID
    private TableCLI findTableCLIById(String id) {
        for (TableCLI t : TableList) {
            if (t.getTableId().equalsIgnoreCase(id)) {
                return t;
            }
        }
        return null;
    }

    // Helper: safe integer input
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Dữ liệu không hợp lệ, vui lòng nhập lại:: ");
            }
        }
    }
}
