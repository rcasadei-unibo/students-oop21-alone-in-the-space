package model.status;

import controller.playerController.PlayerShipController;
import utilities.EnumInt;

public class StatusImpl implements Status{

    private int points;
    private int lifePoints;
    private int powerUpActivations = 0;
    private PlayerShipController playerController;
    private boolean powerUp = false;

    public StatusImpl(int points, int lifePoints) {
        this.points = points;
        this.lifePoints = lifePoints;
    }

    public void update() {
        if(this.points >= EnumInt.POWER_UP_SCORE.getValue() * (powerUpActivations+1)) {
            this.playerController.acquirePowerUp();
            if(!this.playerController.isInPowerUp())
                this.powerUpActivations++;
        }
        this.setLifePoints(this.playerController.getPlayerShip().getHealth());

    }

    @Override
    public void setPoints(int value) {
        this.points = value;
        this.playerController.setExp(value);
    }
    @Override
    public void addPoints(int value) {
        this.points += value;
        this.playerController.addExp(value);
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setLifePoints(int value) {
        this.lifePoints = value;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public void switchPowerUp() {
    }



    @Override
    public void setPlayerController(PlayerShipController playerShipController) {
        this.playerController = playerShipController;
    }

    @Override
    public boolean hasPowerUp() {
        return this.powerUp;
    }
}
