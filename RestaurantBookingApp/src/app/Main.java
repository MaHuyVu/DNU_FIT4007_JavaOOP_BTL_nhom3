package app;

import CLI.MenuManagerCLI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MenuManagerCLI menuManager = new MenuManagerCLI();

        try {
            System.out.println(" Đang tải dữ liệu...");
            menuManager.loadData();
        } catch (IOException e) {
            System.out.println(" Không thể tải dữ liệu: " + e.getMessage());
        }

        menuManager.start();

        try {
            menuManager.saveData();
            System.out.println(" Dữ liệu đã được lưu. Tạm biệt ");
        } catch (IOException e) {
            System.out.println(" Lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }
}
