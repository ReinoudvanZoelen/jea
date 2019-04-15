package datalayer;

import java.util.List;

public abstract interface ICrudService<T> {
    T getById(int id);

    List<T> getAll();
    
    void add(T entity);
    
    void update(T entity);

    void delete(T entity);

    void delete(int id);
}