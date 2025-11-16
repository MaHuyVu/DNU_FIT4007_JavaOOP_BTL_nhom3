package Repository;

import model.*;
import Iface.Repository;
import util.CsvUtil;
import util.UUIDGenerator;

import java.util.*;

public class CsvMenuRepository implements Repository<MenuItem, String> {
    private final List<MenuItem> items = new ArrayList<>();
    private static final String FILE = "menu.csv";

    public CsvMenuRepository() { loadAll(); }

    @Override public void add(MenuItem m) {
        m.setId(UUIDGenerator.generate());
        items.add(m);
        saveAll(items);
    }

    @Override public void update(MenuItem m) {
        items.removeIf(i -> i.getId().equals(m.getId()));
        items.add(m);
        saveAll(items);
    }

    @Override public void deleteById(String id) {
        items.removeIf(i -> i.getId().equals(id));
        saveAll(items);
    }

    @Override public Optional<MenuItem> findById(String id) {
        return items.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override public List<MenuItem> findAll() { return new ArrayList<>(items); }

    public List<MenuItem> searchByName(String keyword) {
        return items.stream()
                .filter(m -> m.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override public void saveAll(List<MenuItem> items) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"id","name","type","price","discount"});
        for (MenuItem m : items) {
            String type = m instanceof Food ? "FOOD" : "DRINK";
            data.add(new String[]{
                    m.getId(), m.getName(), type,
                    String.valueOf(m.getPrice()), String.valueOf(m.getDiscount())
            });
        }
        CsvUtil.write(FILE, data);
    }

    @Override public List<MenuItem> loadAll() {
        items.clear();
        var rows = CsvUtil.read(FILE);
        for (int i = 1; i < rows.size(); i++) {
            String[] r = rows.get(i);
            if (r.length < 5) continue;
            MenuItem m = "FOOD".equals(r[2])
                    ? new Food(r[1], Double.parseDouble(r[3]), Double.parseDouble(r[4]))
                    : new Drink(r[1], Double.parseDouble(r[3]), Double.parseDouble(r[4]));
            m.setId(r[0]);
            items.add(m);
        }
        return items;
    }
}