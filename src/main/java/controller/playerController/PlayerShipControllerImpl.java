package controller.playerController;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GunFactory;
import model.PlayerShip;
import utilities.InputCommands;
import utilities.PlayerShipValues;

public class PlayerShipControllerImpl implements PlayerShipController {

    private PlayerShip playerShip;

    @Override
    public void initialisePlayerShip(Vector2 initialPos, Image sprite) {
        //playerShip = new PlayerShip();
        //sprite to be set in the center and visible
        //maybe initialize depending on ship????
        //use PlayerShipValues.MainShip
        playerShip = new PlayerShip(initialPos, PlayerShipValues.MainShip.MAXHEALTH, PlayerShipValues.MainShip.MAXSPEED,
                                                PlayerShipValues.MainShip.ACCELERATION, PlayerShipValues.MainShip.ROTATIONSPEED);
        playerShip.setSprite(sprite);
        //playerShip.setGun(GunFactory.shootgun()); //Needs proper playergun
    }

/*    private Vector2 rotate(InputCommands input) {
        playerShip.rotate(input);
        return playerShip.getPosition();
    }

    private Vector2 thrust(InputCommands input) {
        playerShip.thrust(input);
        return playerShip.getPosition();
    }*/

    @Override
    public Vector2 move(float deltaTime) {
        playerShip.move(deltaTime);
        playerShip.decaySpeed();
        return playerShip.getPosition();
    }

    public void changeMovement(InputCommands input, float deltaTime){
        playerShip.thrust(input);
        playerShip.rotate(input, deltaTime);
    }

    public void thrustReleased() {
        playerShip.thrustReleased();
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
