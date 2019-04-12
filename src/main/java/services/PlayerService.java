package services;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;

import models.Player;
import datalayer.IPlayerDal;
import datalayer.PlayerDal;

@ApplicationScoped
public class PlayerService {

    @Resource
    private IPlayerDal playerDAL;

    public void add(Player player) {
        this.playerDAL.add(player);
    }

    public Player getById(int id) {
        return this.playerDAL.getById(id);
    }

    // public Player authenticate(String username, String password) {
    // return entityManager
    // .createQuery("SELECT p FROM Player p WHERE p.UserName=:username AND
    // p.Password=:password", Player.class)
    // .setParameter("username", username).setParameter("password",
    // password).getSingleResult();
    // }

}
