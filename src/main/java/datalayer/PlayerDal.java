package datalayer;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entities.Player;

@ApplicationScoped
public class PlayerDal extends GenericDal<Player> implements IPlayerDal {

    @PersistenceContext(unitName = "MasterPU")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

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

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
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