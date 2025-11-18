package app;

import CLI.MenuManagerCLI;

public class Main {

    public static void main(String[] args) {
        // Khởi tạo MenuManagerCLI để quản lý toàn bộ hệ thống
        MenuManagerCLI menuManager = new MenuManagerCLI();

        // Chạy menu chính (bao gồm load dữ liệu và các submenu)
        menuManager.run();

        System.out.println(" Hệ thống đã thoát. Cảm ơn bạn!");
    }
}