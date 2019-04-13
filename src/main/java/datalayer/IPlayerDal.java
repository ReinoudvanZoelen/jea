package datalayer;

import models.Player;

public interface IPlayerDal extends ICrudService<Player> {
    Player getByEmailAddress(String emailAddress);

    Player authenticate(String username, String password);
}