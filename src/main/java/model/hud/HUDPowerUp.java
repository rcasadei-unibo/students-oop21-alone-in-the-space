package model.hud;

import javafx.scene.image.ImageView;
import utilities.PowerUpEnum;

/**
 * This interface show the methods to handle HUD powerUp system.
 */
public interface HUDPowerUp {

    /**
     * @return powerUp.
     */
    ImageView getPowerUp();

    /**
     * Show the powerUp.
     * 
     * @param powerUp name
     */
    void showPowerUp(PowerUpEnum powerUp);

    /**
     * Hide the powerUp.
     * 
     * @param powerUp name.
     */
    void hidePowerUp(PowerUpEnum powerUp);

    /**
     * @param index.
     * @return which status is activated.
     */
    boolean getStatus(int index);
}
