package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.InputCommands;


public interface PlayerShipController  {

    void initialisePlayerShip (Vec2 initialPos, Image sprite);


    Vec2 move(float deltaTime);

    ImageView display();

    void destroy();


}
