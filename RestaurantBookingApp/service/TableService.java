package service;

import model.Table;
import util.CsvUtil;
import java.util.ArrayList;
import java.util.List;
import model.StandardTable;

public class TableService {

    private List<Table> tables = new ArrayList<>();

    public List<Table> loadTables(String filePath) {
        try {
            this.tables = CsvUtil.readTables(filePath);
            System.out.println(" Tải " + tables.size() + " bàn thành công.");
            return tables;
        } catch (Exception e) {
            System.out.println(" Lỗi khi tải danh sách bàn: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void showTables() {
        System.out.println("\n========= DANH SÁCH BÀN =========");
        if (tables.isEmpty()) {
            System.out.println("Không có bàn nào!");
            return;
        }
        for (Table table : tables) {
            System.out.println(table);
        }
    }

    public List<Table> getAllTables() {
        return tables;
    }

    public Table findTableById(String tableId) {
        for (Table table : tables) {
            if (table.getId().equals(tableId)) {
                return table;
            }
        }
        return null;
    }

    public void addTable(String id, int seats) {
        if (findTableById(id) != null) {
            System.out.println(" Bàn với ID " + id + " đã tồn tại!");
            return;
        }


        Table table = new StandardTable(id, seats);
        tables.add(table);
        System.out.println(" Thêm bàn " + id + " thành công!");
    }


    public void addTable(Table table) {
        if (findTableById(table.getId()) != null) {
            System.out.println(" Bàn với ID " + table.getId() + " đã tồn tại!");
            return;
        }
        tables.add(table);
        System.out.println(" Thêm bàn " + table.getId() + " thành công!");
    }


    public void updateTable(String id, int seats) {
        Table table = findTableById(id);
        if (table == null) {
            System.out.println(" Không tìm thấy bàn với ID: " + id);
            return;
        }

        table.setSeats(seats);
        System.out.println(" Cập nhật bàn " + id + " thành công!");
    }


    public boolean updateTable(String tableId, int newSeats, double newSurcharge) {
        Table table = findTableById(tableId);
        if (table == null) {
            System.out.println(" Không tìm thấy bàn với ID: " + tableId);
            return false;
        }

        table.setSeats(newSeats);
        table.setSurcharge(newSurcharge);
        System.out.println(" Cập nhật bàn " + tableId + " thành công!");
        return true;
    }


    public boolean updateTable(String tableId, int newSeats, double newSurcharge, String status) {
        Table table = findTableById(tableId);
        if (table == null) {
            System.out.println(" Không tìm thấy bàn với ID: " + tableId);
            return false;
        }

        table.setSeats(newSeats);
        table.setSurcharge(newSurcharge);
        table.setStatus(status);
        System.out.println(" Cập nhật bàn " + tableId + " thành công!");
        return true;
    }

    public boolean deleteTable(String id) {
        Table table = findTableById(id);
        if (table == null) {
            System.out.println(" Không tìm thấy bàn với ID: " + id);
            return false;
        }

        tables.remove(table);
        System.out.println(" Đã xóa bàn " + id + " thành công!");
        return true;
    }


    public boolean deleteTableAlt(String tableId) {
        boolean removed = tables.removeIf(t -> t.getId().equals(tableId));
        if (removed) {
            System.out.println(" Đã xóa bàn " + tableId + " thành công!");
        } else {
            System.out.println(" Không tìm thấy bàn với ID: " + tableId);
        }
        return removed;
    }


    public void saveTables(String filePath) {
        try {
            CsvUtil.writeTables(filePath, tables);
            System.out.println(" Lưu danh sách bàn thành công!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi lưu danh sách bàn: " + e.getMessage());
        }
    }
}