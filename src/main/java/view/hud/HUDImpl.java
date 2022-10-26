package view.hud;

import controller.collisionDetection.Collision;
import controller.collisionDetection.CollisionImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.hud.HUDPowerUp;
import model.hud.HUDPowerUpImpl;
import utilities.EnumInt;
import view.GameMap;

public class HUDImpl implements HUDInterface{

    private HUDPointsImpl pointsHUD;
    private HUDLifeImpl livesHUD;
    private HUDPowerUp powerUpHUD;
    private Collision collision;
    private GameMap gameMap;

    public HUDImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.generateHUD();
    }

    private void generateHUD() {

        this.pointsHUD = new HUDPointsImpl();
        this.gameMap.getGameContainer().getChildren().add(this.pointsHUD);
        this.pointsHUD.setViewOrder(EnumInt.VIEW_ORDER.getValue());

        this.livesHUD  = new HUDLifeImpl();
        this.gameMap.getGameContainer().getChildren().add(this.livesHUD);
        this.pointsHUD.setViewOrder(EnumInt.VIEW_ORDER.getValue());

        this.powerUpHUD = new HUDPowerUpImpl(this.gameMap);


        //this.collision = new CollisionImpl(this.gameMap, this.pointsHUD, this.livesHUD, this.powerUpHUD);
        this.collision = new CollisionImpl();
    }

    @Override
    public boolean checkGameStatus() {
        return this.livesHUD.getStatus();
    }

    @Override
    public Collision getCollision() {
        return this.collision;
    }

    @Override
    public int checkPoints() {
        return this.pointsHUD.getPoints();
    }

    @Override
    public int checkLives() {
        return this.livesHUD.getLifePoints();
    }

    @Override
    public HUDPowerUp getPowerUpImpl() {
        return this.powerUpHUD;
    }


}
