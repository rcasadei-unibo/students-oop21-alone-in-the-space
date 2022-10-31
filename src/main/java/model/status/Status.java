package model.status;

import controller.playerController.PlayerShipController;

public interface Status {

    void setPoints(int value);

    int getPoints();

    void setLifePoints(int value);

    int getLifePoints();

    void addPoints(int value);

    void switchPowerUp();

    void setPlayerController(PlayerShipController playerShipController);

    boolean hasPowerUp();
}
