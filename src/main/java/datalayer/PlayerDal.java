package datalayer;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import models.Player;

public class PlayerDal implements IPlayerDal {

    @PersistenceContext(unitName = "MasterPU")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public void add(Player entity) {
        try {
            transaction.begin();

            entityManager.persist(entity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Player getById(int id) {
        Player player = null;
        try {
            transaction.begin();

            player = entityManager.find(Player.class, id);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public List<Player> getAll() {
        return null;
    }

    @Override
    public Player update(Player entity) {
        return null;
    }

    @Override
    public void delete(Player entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Player getByEmailAddress(String emailAddress) {
        return null;
    }

    @Override
    public Player authenticate(String username, String password) {
        return null;
    }

}