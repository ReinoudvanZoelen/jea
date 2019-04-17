package services;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;

import entities.Player;
import datalayer.IPlayerDal;

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
}
