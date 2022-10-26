package model.hud;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.EnumInt;
import utilities.PowerUpEnum;
import view.GameMap;

import java.util.stream.IntStream;


public class HUDPowerUpImpl implements HUDPowerUp{

    private static final int SPACING = 30;
    private static final int X_LAYOUT = 50;
    private static final int Y_TRANSLATION = 645;

    private ImageView[] powerUp = new ImageView[EnumInt.ONE.getValue()];
    private boolean[] statusMonitor;
    private GameMap gameMap;

    public HUDPowerUpImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.statusMonitor = new boolean[EnumInt.ONE.getValue()];
        //TODO
    }

    @Override
    public ImageView[] getPowerUp() {
        return this.powerUp;
    }

    private void addPowerUp() {
        IntStream.range(EnumInt.ZERO.getValue(), EnumInt.ONE.getValue()).forEach(index -> {
            this.powerUp[index] = new ImageView(new Image(getClass().getResourceAsStream("/Images/" + "powerUp" + index + ".png"),
                SPACING, SPACING, false,true));
            this.powerUp[index].setLayoutX(EnumInt.WIDTH.getValue() - X_LAYOUT);
            this.powerUp[index].setLayoutY(index * -SPACING);
            this.powerUp[index].setTranslateY(Y_TRANSLATION);
            this.powerUp[index].setViewOrder(EnumInt.VIEW_ORDER.getValue());
        });
    }

    @Override
    public void showPowerUp(final PowerUpEnum powerUp) {
        try {
            switch (powerUp) {
                case WeaponDamage:
                    this.gameMap.getGameContainer().getChildren().add(this.powerUp[EnumInt.ZERO.getValue()]);
                    this.statusMonitor[EnumInt.ZERO.getValue()] = true;
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
            this.gameMap.getGameContainer().getChildren().remove(this.powerUp[powerUp.ordinal()]);
            this.statusMonitor[powerUp.ordinal()] = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean getStatus(int index) {
        return this.statusMonitor[index];
    }
}
