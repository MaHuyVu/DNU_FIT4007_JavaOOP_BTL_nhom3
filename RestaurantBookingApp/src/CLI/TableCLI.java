package CLI;

import model.TableStatus;
import service.TableService;
import java.util.Scanner;

public class TableCLI {

    private final Scanner sc = new Scanner(System.in);
    private final TableService tableService;

    public TableCLI(TableService tableService) {
        this.tableService = tableService;
    }

    public TableCLI(String id, int seats, String status) {
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ BÀN ĂN =====");
            System.out.println("1. Xem danh sách bàn");
            System.out.println("2. Thêm bàn");
            System.out.println("3. Sửa bàn");
            System.out.println("4. Xóa bàn");
            System.out.println("5. Đổi trạng thái bàn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            choice = readInt();

            switch (choice) {
                case 1 -> tableService.showTables();
                case 2 -> addTable();
                case 3 -> updateTable();
                case 4 -> deleteTable();
                case 5 -> changeStatus();
                case 0 -> System.out.println("↩ Quay về menu chính...");
                default -> System.out.println(" Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }

    private int readInt() {
        try { return Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return -1; }
    }

    // ============================
    //       THÊM BÀN
    // ============================
    private void addTable() {
        System.out.print("Nhập ID bàn: ");
        String id = sc.nextLine();

        System.out.print("Số ghế: ");
        int seats = readInt();

        TableStatus status = inputStatus();

        tableService.addTable(id, seats, status);
    }

    // ============================
    //       SỬA BÀN
    // ============================
    private void updateTable() {
        System.out.print("Nhập ID bàn cần sửa: ");
        String id = sc.nextLine();

        System.out.print("Số ghế mới: ");
        int seats = readInt();

        TableStatus status = inputStatus();

        tableService.updateTable(id, seats, status);
    }

    // ============================
    //       XÓA BÀN
    // ============================
    private void deleteTable() {
        System.out.print("Nhập ID bàn cần xóa: ");
        String id = sc.nextLine();

        tableService.deleteTable(id);
    }

    // ============================
    //     ĐỔI TRẠNG THÁI BÀN
    // ============================
    private void changeStatus() {
        System.out.print("Nhập ID bàn cần đổi trạng thái: ");
        String id = sc.nextLine();

        TableStatus status = inputStatus();

        tableService.changeStatus(id, status);
    }

    // ============================
    //  CHỌN TRẠNG THÁI BÀN (ENUM)
    // ============================
    private TableStatus inputStatus() {
        System.out.println("\nChọn trạng thái bàn:");
        System.out.println("1. TRỐNG");
        System.out.println("2. ĐÃ ĐẶT");
        System.out.println("3. ĐANG PHỤC VỤ");
        System.out.print("Chọn: ");

        while (true) {
            int opt = readInt();
            switch (opt) {
                case 1 -> return TableStatus.TRONG;
                case 2 -> return TableStatus.DA_DAT;
                case 3 -> return TableStatus.DANG_PHUC_VU;
                default -> System.out.print("❌ Sai! Nhập lại (1-3): ");
            }
        }
    }
}
