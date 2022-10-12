package controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import javafx.scene.image.ImageView;
import utilities.InputCommands;


public interface PlayerShipController  {

    void initialisePlayerShip ();

    Vector2 rotate (InputCommands input);

    Vector2 thrust (InputCommands input);

    Vector2 move(float deltaTime);

    ImageView display();

    void destroy();

    Texture getTexture();

}
