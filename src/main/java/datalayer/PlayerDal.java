package datalayer;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import entities.Player;

@ApplicationScoped
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
        List<Player> players = null;
        try {
            transaction.begin();

            players = entityManager.createQuery("SELECT p FROM tbl_Player p").getResultList();
            // players = entityManager.createQuery("SELECT p FROM Player
            // p").getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public Player update(Player entity) {
        try {
            transaction.begin();

            entityManager.persist(entity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.getById(entity.getId());
    }

    @Override
    public void delete(Player entity) {
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

    @Override
    public Player getByEmailAddress(String emailAddress) {
       //TODO: Create getByEmailAddress
       return null;
    }

    @Override
    public Player authenticate(String username, String password) {
        return null;
    }

}