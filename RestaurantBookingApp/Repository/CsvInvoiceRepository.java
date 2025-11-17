package Repository;

import model.*;
import Iface.Repository;
import util.CsvUtil;
import util.UUIDGenerator;

import java.time.LocalDate;
import java.util.*;

public class CsvInvoiceRepository implements Repository<Invoice, String> {
    private final List<Invoice> invoices = new ArrayList<>();
    private static final String FILE = "invoices.csv";

    public CsvInvoiceRepository() { loadAll(); }

    @Override public void add(Invoice i) {
        i.setId(UUIDGenerator.generate());
        invoices.add(i);
        saveAll(invoices);
    }

    @Override public void update(Invoice i) {
        invoices.removeIf(x -> x.getId().equals(i.getId()));
        invoices.add(i);
        saveAll(invoices);
    }

    @Override public void deleteById(String id) {
        invoices.removeIf(inv -> inv.getId().equals(id));
        saveAll(invoices);
    }

    @Override public Optional<Invoice> findById(String id) {
        return invoices.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override public List<Invoice> findAll() { return new ArrayList<>(invoices); }

    @Override public void saveAll(List<Invoice> items) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"id","bookingId","items","total","discount","finalAmount","date"});
        for (Invoice i : items) {
            String itemsStr = i.getOrderItems().entrySet().stream()
                    .map(e -> e.getKey() + ":" + e.getValue())
                    .reduce((a,b) -> a + "|" + b).orElse("");
            data.add(new String[]{
                    i.getId(), i.getBookingId(), itemsStr,
                    String.valueOf(i.getTotal()), String.valueOf(i.getDiscount()),
                    String.valueOf(i.getFinalAmount()), i.getInvoiceDate().toString()
            });
        }
        CsvUtil.write(FILE, data);
    }

    @Override public List<Invoice> loadAll() {
        invoices.clear();
        var rows = CsvUtil.read(FILE);
        for (int i = 1; i < rows.size(); i++) {
            String[] r = rows.get(i);
            if (r.length < 7) continue;
            Map<String, Integer> items = new HashMap<>();
            if (!r[2].isEmpty()) {
                for (String p : r[2].split("\\|")) {
                    String[] kv = p.split(":");
                    if (kv.length == 2) items.put(kv[0], Integer.parseInt(kv[1]));
                }
            }
            Invoice inv = new Invoice(r[1], r[0], new ArrayList<>(), Double.parseDouble(r[3]));
            inv.setId(r[0]);
            inv.setInvoiceDate(LocalDate.parse(r[6]));
            invoices.add(inv);
        }
        return invoices;
    }
}