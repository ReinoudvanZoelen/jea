package datalayer;

import entities.Player;

public interface IPlayerDal extends ICrudService<Player> {
    Player getByEmailAddress(String emailAddress);

    Player authenticate(String emailAddress, String password);
}
