package util;

import model.*;
import java.io.*;
import java.util.*;

public class CsvUtil {


    public static List<MenuItem> readMenu(String filePath) {
        List<MenuItem> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue;

                String id = data[0].trim();
                String name = data[1].trim();
                String type = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                double discount = Double.parseDouble(data[4].trim());

                MenuItem item = type.equalsIgnoreCase("FOOD")
                        ? new Food(name, price, discount)
                        : new Drink(name, price, discount);
                item.setId(id);
                items.add(item);
            }

        } catch (FileNotFoundException e) {
            System.err.println(" File không tồn tại: " + filePath);
        } catch (IOException e) {
            System.err.println(" Lỗi đọc menu: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(" Lỗi định dạng số trong file menu");
        }
        return items;
    }


    public static List<Table> readTables(String filePath) {
        List<Table> tables = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                String id = data[0].trim();
                String number = data[1].trim();
                int capacity = Integer.parseInt(data[2].trim());
                String type = data[3].trim();
                String status = data[4].trim();
                double surcharge = Double.parseDouble(data[5].trim());

                Table table = type.equalsIgnoreCase("VIP")
                        ? new VipTable(number, capacity, surcharge)
                        : new StandardTable(number, capacity);

                table.setId(id);
                table.setStatus(status);
                tables.add(table);
            }

        } catch (FileNotFoundException e) {
            System.err.println(" File không tồn tại: " + filePath);
        } catch (IOException e) {
            System.err.println(" Lỗi đọc tables: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(" Lỗi định dạng số trong file tables");
        }
        return tables;
    }


    public static void writeTables(String filePath, List<Table> tables) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("id,number,capacity,type,status,surcharge\n");

            for (Table table : tables) {
                String type = table instanceof VipTable ? "VIP" : "STANDARD";
                String line = String.format("%s,%d,%d,%s,%s,%.0f\n",
                        table.getId(),
                        table.getNumber(),
                        table.getCapacity(),
                        type,
                        table.getStatus().name(),
                        table.getSurcharge()
                );
                bw.write(line);
            }

        } catch (IOException e) {
            System.err.println(" Lỗi ghi tables: " + e.getMessage());
        }
    }

    public static List<String[]> read(String filePath) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            System.err.println(" File không tồn tại: " + filePath);
        } catch (IOException e) {
            System.err.println(" Lỗi đọc file: " + e.getMessage());
        }
        return rows;
    }

    public static void write(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.write("\n");
            }
        } catch (IOException e) {
            System.err.println(" Lỗi ghi file: " + e.getMessage());
        }
    }
}