package CLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.MenuItem;
import model.Food;
import model.Drink;
import service.MenuService;

public class MenuItemCLI {
    private final MenuService menuService = new MenuService();
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        menuService.loadMenu();

        int choice;
        do {
            System.out.println("\n=== QUẢN LÝ MÓN ĂN ===");
            System.out.println("1. Xem danh sách món");
            System.out.println("2. Thêm món");
            System.out.println("3. Sửa món");
            System.out.println("4. Xóa món");
            System.out.println("5. Tìm kiếm món ăn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> menuService.showMenu();
                case 2 -> addMenuItem();
                case 3 -> updateMenuItem();
                case 4 -> deleteMenuItem();
                case 5 -> searchMenuItem();
                case 0 -> System.out.println("⬅️ Quay lại MenuManager...");
                default -> System.out.println("⚠️ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private void searchMenuItem() {
    }

    private void addMenuItem() {
        System.out.print("Mã món: ");
        String id = sc.nextLine();
        System.out.print("Tên món: ");
        String name = sc.nextLine();
        System.out.print("Loại (1. FOOD / 2. DRINK): ");
        int typeChoice = Integer.parseInt(sc.nextLine());
        System.out.print("Giá: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Giảm giá (vd 0.1 cho 10%): ");
        double discount = Double.parseDouble(sc.nextLine());

        MenuItem item = (typeChoice == 1)
                ? new Food(id, name, price, discount)
                : new Drink(id, name, price, discount);

        menuService.addMenuItem(item);
        System.out.println("✅ Đã thêm món mới!");
    }

    private void updateMenuItem() {
        System.out.print("Nhập mã món cần sửa: ");
        String id = sc.nextLine();
        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Giá mới: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Giảm giá mới: ");
        double discount = Double.parseDouble(sc.nextLine());

        if (menuService.updateMenuItem(id, name, price, discount))
            System.out.println("✅ Cập nhật thành công!");
        else
            System.out.println("❌ Không tìm thấy món!");
    }

    private void deleteMenuItem() {
        System.out.print("Nhập mã món cần xóa: ");
        String id = sc.nextLine();

        if (menuService.deleteMenuItem(id))
            System.out.println("✅ Đã xóa món!");
        else
            System.out.println("❌ Không tìm thấy mã món!");
    }

    private void sortMenuItems() {
        System.out.println("\n--- SẮP XẾP MÓN ĂN ---");
        System.out.println("1. Theo giá (tăng dần)");
        System.out.println("2. Theo giá (giảm dần)");
        System.out.println("3. Theo giảm giá (tăng dần)");
        System.out.println("4. Theo giảm giá (giảm dần)");
        System.out.print("Chọn: ");
        int option = Integer.parseInt(sc.nextLine());

        List<MenuItem> sorted = new ArrayList<>();

        switch (option) {
            case 1 -> sorted = menuService.sortByPrice(true);
            case 2 -> sorted = menuService.sortByPrice(false);
            case 3 -> sorted = menuService.sortByDiscount(true);
            case 4 -> sorted = menuService.sortByDiscount(false);
            default -> {
                System.out.println("⚠️ Lựa chọn không hợp lệ!");
                return;
            }
        }

        System.out.printf("\n%-6s | %-22s | %-6s | %-8s | %-8s%n",
                "ID", "Tên món", "Loại", "Giá", "Giảm");
        for (MenuItem item : sorted) {
            System.out.printf("%-6s | %-22s | %-6s | %8.0f | %6.0f%%%n",
                    item.getId(), item.getName(), item.getType(),
                    item.getPrice(), item.getDiscount() * 100);
        }
    }

    public void menu() {
    }
}

