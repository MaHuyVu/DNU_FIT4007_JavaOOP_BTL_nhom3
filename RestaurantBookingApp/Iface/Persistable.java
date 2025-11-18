package Iface;

import java.util.List;

public interface Persistable<T> {
    void saveAll(List<T> items);
    List<T> loadAll();
}
