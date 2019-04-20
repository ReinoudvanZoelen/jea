package datalayer;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entities.Team;

@ApplicationScoped
public class TeamDal extends GenericDal<Team> implements ITeamDal {

    @PersistenceContext(unitName = "MasterPU")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public Team getById(int id) {
        Team team = null;
        try {
            transaction.begin();

            team = entityManager.find(Team.class, id);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public List<Team> getAll() {
        List<Team> teams = new ArrayList<Team>();
        try {
            transaction.begin();

            @SuppressWarnings("unchecked")
            Iterable<Object> result = entityManager.createQuery("SELECT t FROM tbl_Team t").getResultList();

            if (result != null) {
                for (Object object : result) {
                    teams.add((Team) object);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teams;
    }

}