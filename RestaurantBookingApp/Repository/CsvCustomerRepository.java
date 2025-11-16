package Repository;

import model.Customer;
import Iface.Repository;
import util.CsvUtil;
import util.UUIDGenerator;

import java.util.*;

public class CsvCustomerRepository implements Repository<Customer, String> {
    private final List<Customer> customers = new ArrayList<>();
    private static final String FILE = "customers.csv";

    public CsvCustomerRepository() { loadAll(); }

    @Override public void add(Customer c) {
        c.setId(UUIDGenerator.generate());
        customers.add(c);
        saveAll(customers);
    }

    @Override public void update(Customer c) {
        customers.removeIf(x -> x.getId().equals(c.getId()));
        customers.add(c);
        saveAll(customers);
    }

    @Override public void deleteById(String id) {
        customers.removeIf(c -> c.getId().equals(id));
        saveAll(customers);
    }

    @Override public Optional<Customer> findById(String id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override public List<Customer> findAll() { return new ArrayList<>(customers); }

    public Optional<Customer> findByPhone(String phone) {
        return customers.stream().filter(c -> c.getPhone().equals(phone)).findFirst();
    }

    @Override public void saveAll(List<Customer> items) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"id","name","phone","email","isVip"});
        for (Customer c : items) {
            data.add(new String[]{
                    c.getId().toString(), c.getName(), c.getPhone(), c.getEmail(), String.valueOf(c.isVip())
            });
        }
        CsvUtil.write(FILE, data);
    }

    @Override public List<Customer> loadAll() {
        customers.clear();
        var rows = CsvUtil.read(FILE);
        for (int i = 1; i < rows.size(); i++) {
            String[] r = rows.get(i);
            if (r.length < 5) continue;
            Customer c = new Customer(r[1], r[2], r[3], Boolean.parseBoolean(r[4]));
            c.setId(r[0]);
            customers.add(c);
        }
        return customers;
    }
}