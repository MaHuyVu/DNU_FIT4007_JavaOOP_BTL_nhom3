package CLI;

import model.StandardTable;
import model.Table;
import model.VipTable;
import service.TableService;
import java.util.Scanner;

public class TableCLI {

    private final Scanner sc = new Scanner(System.in);
    private TableService tableService;


    // Constructor chính - chỉ giữ cái này
    public TableCLI(TableService tableService) {
        this.tableService = tableService;
    }

    public TableCLI(String id, int seats, String status) {
        this.tableService = tableService;
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ BÀN ĂN =====");
            System.out.println("1. Xem danh sách bàn");
            System.out.println("2. Thêm bàn");
            System.out.println("3. Sửa bàn");
            System.out.println("4. Xóa bàn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1 -> tableService.showTables();
                case 2 -> addTable();
                case 3 -> updateTable();
                case 4 -> deleteTable();
                case 0 -> System.out.println("↩ Quay về menu chính...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }

    private int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private void addTable() {
        System.out.println("\n[Thêm bàn mới]");

        System.out.print("Nhập ID bàn: ");
        String id = sc.nextLine();

        System.out.print("Số ghế: ");
        int seats = Integer.parseInt(sc.nextLine());

        System.out.print("Loại bàn (1-Thường, 2-VIP): ");
        int type = Integer.parseInt(sc.nextLine());

        try {
            Table table;
            if (type == 2) {
                System.out.print("Phụ phí VIP: ");
                double surcharge = Double.parseDouble(sc.nextLine());
                table = new VipTable(id, seats, surcharge);
            } else {
                table = new StandardTable(id, seats);
            }

            tableService.addTable(table);
            System.out.println("✔ Thêm bàn thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }

    private void updateTable() {
        System.out.println("\n[Cập nhật bàn]");

        System.out.print("Nhập ID bàn cần sửa: ");
        String id = sc.nextLine();

        System.out.print("Số ghế mới: ");
        int seats = Integer.parseInt(sc.nextLine());

        System.out.print("Phụ phí mới (0 nếu bàn thường): ");
        double surcharge = Double.parseDouble(sc.nextLine());

        boolean success = tableService.updateTable(id, seats, surcharge);
        if (!success) {
            System.out.println("❌ Không tìm thấy bàn với ID: " + id);
        }
    }

    private void deleteTable() {
        System.out.println("\n[Xóa bàn]");

        System.out.print("Nhập ID bàn cần xóa: ");
        String id = sc.nextLine();

        System.out.print("Xác nhận xóa bàn " + id + "? (y/n): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            boolean success = tableService.deleteTable(id);
            if (!success) {
                System.out.println("❌ Không tìm thấy bàn với ID: " + id);
            }
        } else {
            System.out.println("❌ Đã hủy thao tác xóa.");
        }
    }

    public void setSeatCount(int newSeats) {
    }

    public void setStatus(String newStatus) {
    }

    public String getTableId() {
        System.out.print("Nhập ID bàn: ");
        return sc.nextLine();
    }
}