package datalayer;

import java.util.ArrayList;
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
        List<Player> players = new ArrayList<Player>();
        try {
            transaction.begin();

            @SuppressWarnings("unchecked")
            Iterable<Object> result = entityManager.createQuery("SELECT p FROM tbl_Player p").getResultList();

            if (result != null) {
                for (Object object : result) {
                    players.add((Player) object);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public Player getByEmailAddress(String emailAddress) {
        Player player = new Player();

        try {
            transaction.begin();

            Object result = entityManager
                    .createQuery("SELECT player FROM tbl_Player player WHERE player.emailAddress=:emailAddress")
                    .setParameter("emailAddress", emailAddress).getSingleResult();

            if (result != null) {
                player = (Player) result;
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public Player authenticate(String emailAddress, String password) {
        Player player = null;

        try {
            transaction.begin();

            Object result = entityManager.createQuery(
                    "SELECT player FROM tbl_Player player WHERE player.emailAddress=:emailAddress AND player.password=:password")
                    .setParameter("emailAddress", emailAddress).setParameter("password", password).getSingleResult();

            if (result != null) {
                player = (Player) result;
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }
}