package controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public interface PlayerShipController  {

    void initialise ();

    Vector2 rotate (String direction);

    Vector2 thrust (String direction);

    Vector2 move();

    void destroy();

    Texture getTexture();

}
