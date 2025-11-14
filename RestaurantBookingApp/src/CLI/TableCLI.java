package CLI;

import service.TableService;
import java.util.Scanner;

public class TableCLI {

    private final Scanner sc = new Scanner(System.in);
    private final TableService tableService;


    public TableCLI(TableService tableService) {
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
                default -> System.out.println(" Lựa chọn không hợp lệ!");
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
        System.out.print("Nhập ID bàn: ");
        String id = sc.nextLine();

        System.out.print("Số ghế: ");
        int seats = Integer.parseInt(sc.nextLine());

        tableService.addTable(id, seats);
    }

    private void updateTable() {
        System.out.print("Nhập ID bàn cần sửa: ");
        String id = sc.nextLine();

        System.out.print("Số ghế mới: ");
        int seats = Integer.parseInt(sc.nextLine());

        tableService.updateTable(id, seats);
    }

    private void deleteTable() {
        System.out.print("Nhập ID bàn cần xóa: ");
        String id = sc.nextLine();

        tableService.deleteTable(id);
    }
}
