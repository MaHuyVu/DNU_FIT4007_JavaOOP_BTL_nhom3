package app;

import CLI.BookingCLI;
import service.TableService;
import model.Table;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TableService tableService = new TableService();

        // 🔹 Load danh sách bàn từ CSV
        List<Table> tables = tableService.loadTables("data/tables.csv");

        BookingCLI bookingCLI = new BookingCLI(tables);

        int choice;
        do {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ NHÀ HÀNG =====");
            System.out.println("1. Quản lý đặt bàn");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> bookingCLI.menu();
                case 0 -> System.out.println(" Thoát chương trình...");
                default -> System.out.println(" Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }
}
