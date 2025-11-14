package service;

import util.Persistable;
import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> extends Persistable<T> {
    void add(T entity);
    void update(T entity);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}