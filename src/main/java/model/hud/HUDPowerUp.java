package model.hud;

import javafx.scene.image.ImageView;
import utilities.PowerUpEnum;

public interface HUDPowerUp {

    ImageView getPowerUp();

    void showPowerUp(PowerUpEnum powerUp);

    void hidePowerUp(PowerUpEnum powerUp);

    boolean getStatus(int index);
}
