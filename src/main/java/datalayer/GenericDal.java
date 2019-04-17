package datalayer;

import javax.enterprise.context.ApplicationScoped;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ApplicationScoped
public abstract class GenericDal<T> implements ICrudService<T> {

    @PersistenceContext(unitName = "MasterPU")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public void add(T entity) {
        try {
            transaction.begin();

            entityManager.persist(entity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(T entity) {
        try {
            transaction.begin();

            entityManager.merge(entity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        try {
            transaction.begin();

            entityManager.remove(entity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        this.delete(this.getById(id));
    }
}