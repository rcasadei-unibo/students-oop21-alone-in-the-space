package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Ship;
import utilities.InputCommands;


public interface PlayerShipController  {
    void initialisePlayerShip (Vec2 initialPos, Image sprite);
    void update(long deltaTime);
    ImageView display();
    void destroy();
    Ship getPlayerShip();
    void thrust(InputCommands input);
    void rotate(InputCommands input);

    void shot();

    void activatePowerUp();

    void thrustReleased();
}
