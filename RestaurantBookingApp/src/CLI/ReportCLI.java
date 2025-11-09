package CLI;

import java.util.Scanner;

public class ReportCLI {
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int choice;
        do {
            System.out.println("\n===  BÁO CÁO & THỐNG KÊ ===");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Top 5 món bán chạy nhất");
            System.out.println("4. Số bàn đặt trước mỗi ngày");
            System.out.println("0. Quay lại");
            System.out.print(" Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1 -> reportDailyRevenue();
                case 2 -> reportMonthlyRevenue();
                case 3 -> top5Dishes();
                case 4 -> bookingCountPerDay();
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

    private void reportDailyRevenue() { System.out.println(" [Báo cáo doanh thu theo ngày ]"); }
    private void reportMonthlyRevenue() { System.out.println(" [Báo cáo doanh thu theo tháng  ]"); }
    private void top5Dishes() { System.out.println(" [Top 5 món bán chạy nhất ]"); }
    private void bookingCountPerDay() { System.out.println(" [Thống kê số bàn đặt trước  ]"); }
}
