package controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import javafx.scene.image.ImageView;
import model.PlayerShip;
import utilities.InputCommands;
import utilities.PlayerShipValues;

public class PlayerShipControllerImpl implements PlayerShipController {

    private PlayerShip playerShip;

    @Override
    public void initialisePlayerShip() {
        //playerShip = new PlayerShip();
        //sprite to be set in the center and visible
        //maybe initialize depending on ship????
        //use PlayerShipValues.MainShip
    }

    @Override
    public Vector2 rotate(InputCommands input) {
        playerShip.rotate(input);
        return playerShip.getPosition();
    }

    @Override
    public Vector2 thrust(InputCommands input) {
        playerShip.thrust(input);
        return playerShip.getPosition();
    }

    @Override
    public Vector2 move(float deltaTime) {
        playerShip.move(deltaTime);
        if(!isThrustBeingHeld())
            playerShip.decaySpeed();
        return playerShip.getPosition();

    }

    private boolean isThrustBeingHeld() {
        return false;
    }

    public void update(float deltaTime) {
        while(true) {
            move(deltaTime);
            display();
        }
    }

    @Override
    public ImageView display() {
        ImageView sprite = playerShip.getSprite();
        sprite.setX(playerShip.getPosition().x);
        sprite.setY(playerShip.getPosition().y);
        sprite.setRotate(playerShip.getPosition().angleDeg());
        return sprite;
    }

    @Override
    public void destroy() {
        playerShip.destroy();
    }

    @Override
    public Texture getTexture() {
        return playerShip.getTexture();
    }
}
