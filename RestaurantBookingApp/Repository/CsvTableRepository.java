package Repository;

import Iface.Repository;
import model.*;
import util.CsvUtil;
import util.UUIDGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvTableRepository implements Repository<Table, String> {

    private final List<Table> tables = new ArrayList<>();
    private static final String FILE = "tables.csv";

    public CsvTableRepository() {
        loadAll();
    }

    @Override
    public void add(Table table) {
        table.setId(UUIDGenerator.generate());
        tables.add(table);
        saveAll(tables);
    }

    @Override
    public void update(Table table) {
        tables.removeIf(t -> t.getId().equals(table.getId()));
        tables.add(table);
        saveAll(tables);
    }

    @Override
    public void deleteById(String id) {
        tables.removeIf(t -> t.getId().equals(id));
        saveAll(tables);
    }

    @Override
    public Optional<Table> findById(String id) {
        return tables.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Table> findAll() {
        return new ArrayList<>(tables);
    }

    public List<Table> findAvailable() {
        return tables.stream()
                .filter(t -> t.getStatus() == TableStatus.AVAILABLE)
                .toList();
    }

    @Override
    public void saveAll(List<Table> items) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"id", "number", "capacity", "type", "status", "surcharge"});

        for (Table t : items) {
            String type = t instanceof VipTable ? "VIP" : "STANDARD";
            String surcharge = t instanceof VipTable ? String.valueOf(((VipTable) t).getSurcharge()) : "0";
            data.add(new String[]{
                    t.getId(),
                    String.valueOf(t.getNumber()),
                    String.valueOf(t.getCapacity()),
                    type,
                    t.getStatus().name(),
                    surcharge
            });
        }
        CsvUtil.write(FILE, data);
    }

    @Override
    public List<Table> loadAll() {
        tables.clear();
        List<String[]> rows = CsvUtil.read(FILE);

        for (int i = 1; i < rows.size(); i++) {
            String[] r = rows.get(i);
            if (r.length < 6) continue;

            String id = r[0];
            int number = Integer.parseInt(r[1]);
            int capacity = Integer.parseInt(r[2]);
            String type = r[3];
            TableStatus status = TableStatus.valueOf(r[4]);
            double surcharge = Double.parseDouble(r[5]);

            Table table = "VIP".equals(type)
                    ? new VipTable(String.valueOf(number), capacity, surcharge)
                    : new StandardTable(String.valueOf(number), capacity);
            table.setId(id);
            table.setStatus(status);
            tables.add(table);
        }
        return tables;
    }
}