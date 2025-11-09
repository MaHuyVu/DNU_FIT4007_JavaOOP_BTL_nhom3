package CLI;

import java.util.Scanner;

public class MenuItemCLI {
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int choice;
        do {
            System.out.println("\n===  QUẢN LÝ THỰC ĐƠN ===");
            System.out.println("1. Thêm món ăn / đồ uống");
            System.out.println("2. Sửa món");
            System.out.println("3. Xóa món");
            System.out.println("4. Tìm món theo tên hoặc loại");
            System.out.println("5. Hiển thị toàn bộ thực đơn");
            System.out.println("0. Quay lại");
            System.out.print(" Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1 -> addItem();
                case 2 -> updateItem();
                case 3 -> deleteItem();
                case 4 -> searchItem();
                case 5 -> listItems();
                case 0 -> System.out.println("↩ Quay lại menu chính...");
                default -> System.out.println("Lựa chọn không hợp lệ!");
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

    private void addItem() { System.out.println(" [Thêm món mới - TODO]"); }
    private void updateItem() { System.out.println(" [Sửa món - TODO]"); }
    private void deleteItem() { System.out.println(" [Xóa món - TODO]"); }
    private void searchItem() { System.out.println(" [Tìm kiếm món - TODO]"); }
    private void listItems() { System.out.println(" [Hiển thị thực đơn - TODO]"); }
}
