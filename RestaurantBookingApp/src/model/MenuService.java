package model;

import model.MenuItem;
import model.Food;
import model.Drink;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuService {
    private final List<MenuItem> menuItems = new ArrayList<>();

    // Thêm món mới
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        System.out.println("✅ Đã thêm món: " + item.getName());
    }

    // Hiển thị toàn bộ menu
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

    // Tìm món theo tên (không phân biệt hoa thường)
    public List<MenuItem> searchByName(String keyword) {
        return menuItems.stream()
                .filter(i -> i.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Tìm món theo loại (Food / Drink)
    public List<MenuItem> searchByCategory(String category) {
        return menuItems.stream()
                .filter(i -> i.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Xóa món theo ID
    public boolean removeById(String id) {
        return menuItems.removeIf(i -> i.getId().equals(id));
    }

    // Lấy danh sách tất cả món
    public List<MenuItem> getAll() {
        return menuItems;
    }

    // Đọc menu từ file CSV
    public void loadFromCSV(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("⚠️ Không tìm thấy file menu: " + filename);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // bỏ qua tiêu đề
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;
                String name = parts[0];
                String category = parts[1];
                double price = Double.parseDouble(parts[2]);
                double discount = Double.parseDouble(parts[3]);

                if (category.equalsIgnoreCase("Food"))
                    menuItems.add(new Food(name, category, price, discount));
                else
                    menuItems.add(new Drink(name, category, price, discount));
            }
            System.out.println("📂 Đã tải menu từ file: " + filename);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file menu: " + e.getMessage());
        }
    }

    // Ghi menu ra file CSV
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
}
