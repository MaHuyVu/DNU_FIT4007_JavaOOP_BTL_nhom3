package CLI;

import java.util.Scanner;

public class TableCLI {
    private String tableId;
    private int seatCount;
    private String status;
    private final Scanner sc = new Scanner(System.in);


    public TableCLI(String tableId, int seatCount, String status) {
        this.tableId = tableId;
        this.seatCount = seatCount;
        this.status = status;
    }

    public TableCLI() {

    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Scanner getSc() {
        return sc;
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n===  QUẢN LÝ BÀN ĂN ===");
            System.out.println("1. Thêm bàn mới");
            System.out.println("2. Sửa thông tin bàn");
            System.out.println("3. Xóa bàn");
            System.out.println("4. Hiển thị danh sách bàn");
            System.out.println("5. Cập nhật trạng thái bàn");
            System.out.println("0. Quay lại");
            System.out.print(" Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1 -> addTable();
                case 2 -> updateTable();
                case 3 -> deleteTable();
                case 4 -> listTables();
                case 5 -> updateStatus();
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

    private void addTable() {
        System.out.println(" [Thêm bàn mới ]");
    }

    private void updateTable() {
        System.out.println(" [Sửa thông tin bàn ]");
    }

    private void deleteTable() {
        System.out.println("[Xóa bàn ]");
    }

    private void listTables() {
        System.out.println("[Hiển thị danh sách bàn ]");
    }

    private void updateStatus() {
        System.out.println("[Cập nhật trạng thái bàn ]");
    }
}
