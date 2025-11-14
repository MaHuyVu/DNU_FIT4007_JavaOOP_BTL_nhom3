package service;
import java.util.Comparator;
import model.MenuItem;
import model.Food;
import model.Drink;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuService {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private String newCategory;

    // =====================
    // THÊM MÓN ĂN
    // =====================
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        System.out.println("✅ Đã thêm món: " + item.getName());
    }

    // =====================
    // HIỂN THỊ MENU
    // =====================
    public void displayMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("📭 Menu hiện trống.");
            return;
        }

        System.out.println("🍽️ DANH SÁCH MÓN TRONG MENU:");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i));
        }
    }

    // =====================
    // TÌM KIẾM THEO TÊN
    // =====================
    public List<MenuItem> searchByName(String keyword) {
        return menuItems.stream()
                .filter(i -> i.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // =====================
    // TÌM KIẾM THEO CATEGORY
    // =====================
    public List<MenuItem> searchByCategory(String category) {
        return menuItems.stream()
                .filter(i -> i.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // =====================
    // XÓA THEO ID
    // =====================
    public boolean removeById(String id) {
        return menuItems.removeIf(i -> i.getId().equals(id));
    }

    // ==========================================================
    // 🆕 HÀM MỚI 1: CẬP NHẬT MÓN ĂN (updateMenuItem)
    // ==========================================================
    public boolean updateMenuItem(String id, String newName, double newPrice, double newDiscount) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {

                item.setName(newName);
                item.setCategory(newCategory);
                item.setPrice(newPrice);
                item.setDiscount(newDiscount);

                System.out.println("♻️ Đã cập nhật món: " + newName);
                return true;
            }
        }

        System.out.println("❌ Không tìm thấy món có ID: " + id);
        return false;
    }

    // ==========================================================
    // 🆕 HÀM MỚI 2: XÓA MÓN ĂN (deleteMenuItem)
    // ==========================================================
    public boolean deleteMenuItem(String id) {
        boolean removed = menuItems.removeIf(i -> i.getId().equals(id));

        if (removed) {
            System.out.println("🗑️ Đã xóa món có ID: " + id);
        } else {
            System.out.println("❌ Không tìm thấy món để xóa: " + id);
        }

        return removed;
    }

    // =====================
    // LẤY TẤT CẢ MÓN
    // =====================
    public List<MenuItem> getAll() {
        return menuItems;
    }

    // =====================
    // ĐỌC MENU TỪ CSV
    // =====================
    public void loadFromCSV(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("⚠️ Không tìm thấy file menu: " + filename);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // bỏ tiêu đề

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String name = parts[0];
                String category = parts[1];
                double price = Double.parseDouble(parts[2]);
                double discount = Double.parseDouble(parts[3]);

                if (category.equalsIgnoreCase("Food")) {
                    menuItems.add(new Food(name, category, price, discount));
                } else {
                    menuItems.add(new Drink(name, category, price, discount));
                }
            }

            System.out.println("📂 Đã tải menu từ file: " + filename);

        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file menu: " + e.getMessage());
        }
    }

    // =====================
    // GHI MENU RA CSV
    // =====================
    public void saveToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Name,Category,Price,Discount\n");

            for (MenuItem item : menuItems) {
                writer.append(String.format("%s,%s,%.0f,%.0f\n",
                        item.getName(), item.getCategory(), item.getPrice(), item.getDiscount()));
            }

            System.out.println("💾 Đã lưu menu ra file: " + filename);

        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file menu: " + e.getMessage());
        }
    }

    public void loadMenu() {
        // tuỳ bạn có thể tự xử lý
    }

    public List<MenuItem> sortByPrice(boolean b) {
        // Tạo list mới để chứa kết quả đã sắp xếp
        List<MenuItem> sortedList = new ArrayList<>(this.menuItems); // hoặc từ nguồn dữ liệu khác

        // Sắp xếp theo giá
        if (b) {
            sortedList.sort(Comparator.comparingDouble(MenuItem::getPrice)); // tăng dần
        } else {
            sortedList.sort(Comparator.comparingDouble(MenuItem::getPrice).reversed()); // giảm dần
        }

        return sortedList;
    }

    public List<MenuItem> sortByDiscount(boolean b) {
        List<MenuItem> sortedList = new ArrayList<>(this.menuItems);

        // Sắp xếp theo discount
        if (b) {
            sortedList.sort(Comparator.comparingDouble(MenuItem::getDiscount));
        } else {
            sortedList.sort(Comparator.comparingDouble(MenuItem::getDiscount).reversed());
        }

        return sortedList;
    }

    public void showMenu() {
    }
}
