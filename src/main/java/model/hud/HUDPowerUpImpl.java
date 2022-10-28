package model.hud;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.EnumInt;
import utilities.PowerUpEnum;
import view.GameMap;


public class HUDPowerUpImpl implements HUDPowerUp {

    private static final int SPACING = 30;
    private static final int X_LAYOUT = 50;
    private static final int Y_TRANSLATION = 645;

    private ImageView powerUp = new ImageView();
    private boolean statusMonitor;
    private GameMap gameMap;

    public HUDPowerUpImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.addPowerUp();
    }

    @Override
    public ImageView getPowerUp() {
        return this.powerUp;
    }

    private void addPowerUp() {
            this.powerUp = new ImageView(new Image(getClass().getResourceAsStream("/Images/" + "powerUp" + ".png"),
                SPACING, SPACING, false, true));
            this.powerUp.setLayoutX(EnumInt.WIDTH.getValue() - X_LAYOUT);
            this.powerUp.setLayoutY(-SPACING);
    }

    @Override
    public void showPowerUp(final PowerUpEnum powerUp) {
        try {
            switch (powerUp) {
                case WeaponDamage:
                    this.gameMap.getGameContainer().getChildren().add(this.powerUp);
                    this.statusMonitor = true;
                    break;
                default:
            }
        } catch (Exception e) {
            this.hidePowerUp(powerUp);
        }
    }

    @Override
    public void hidePowerUp(final PowerUpEnum powerUp) {
        try {
            this.gameMap.getGameContainer().getChildren().remove(this.powerUp);
            this.statusMonitor = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean getStatus(int index) {
        return this.statusMonitor;
    }
}
