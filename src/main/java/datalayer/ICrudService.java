package datalayer;

import java.util.List;

public abstract interface ICrudService<T> {
    void add(T entity);

    T getById(int id);

    List<T> getAll();

    T update(T entity);

    void delete(T entity);

    void delete(int id);
}