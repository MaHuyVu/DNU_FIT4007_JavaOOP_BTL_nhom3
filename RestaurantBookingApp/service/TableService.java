package service;

import model.Table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TableService {

    private List<Table> tables = new ArrayList<>();

    public List<Table> getTables() {
        return tables;
    }

    public List<Table> loadTables(String filePath) {
        tables = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                if (p.length < 4) continue;

                Table table = new Table(
                        p[0],                 // id
                        p[1],                 // type
                        Integer.parseInt(p[2]), // seats
                        Double.parseDouble(p[3]) // surcharge
                );

                tables.add(table);
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi load danh sách bàn: " + e.getMessage());
        }

        return tables;
    }

    public void showTables() {
    }
}

