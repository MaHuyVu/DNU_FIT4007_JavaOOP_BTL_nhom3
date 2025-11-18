package Iface;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    void add(T entity);

    void update(T entity);

    void deleteById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

    void saveAll(List<T> items);

    List<T> loadAll();
}