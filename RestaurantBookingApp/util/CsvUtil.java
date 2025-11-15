package util;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<MenuItem> readMenu(String filePath) {
        List<MenuItem> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length < 5) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();
                String type = parts[2].trim().toUpperCase();
                double price = Double.parseDouble(parts[3].trim());
                double discount = Double.parseDouble(parts[4].trim());

                MenuItem item;

                if (type.equals("FOOD")) {
                    item = new Food(id, name, price, discount);
                } else {
                    item = new Drink(id, name, price, discount);
                }

                list.add(item);
            }

        } catch (Exception e) {
            System.out.println("Lỗi đọc menu CSV: " + e.getMessage());
        }

        return list;
    }


    public static List<Table> readTables(String filePath) {
        List<Table> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Bỏ qua dòng header
                }

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String type = parts[1].trim();
                    int seats = Integer.parseInt(parts[2].trim());
                    double surcharge = Double.parseDouble(parts[3].trim());

                    Table table;
                    if (type.equalsIgnoreCase("VIP")) {
                        table = new VipTable(id, seats, surcharge);
                    } else {
                        table = new StandardTable(id, seats);
                    }

                    list.add(table);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file tables CSV: " + e.getMessage());
        }

        return list;
    }

    public static void writeTables(String filePath, List<Table> tables) {
    }

    public static void write(String file, List<String[]> data) {
    }

    public static List<String[]> read(String file) {
    }
}
