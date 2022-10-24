package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GunFactory;
import model.PlayerShip;
import utilities.InputCommands;
import utilities.PlayerValues;

public class PlayerShipControllerImpl implements PlayerShipController {

    private PlayerShip playerShip;

    @Override
    public void initialisePlayerShip(Vec2 initialPos, Image sprite) {
        //playerShip = new PlayerShip();
        //sprite to be set in the center and visible
        //maybe initialize depending on ship????
        //use PlayerShipValues.MainShip
        playerShip = new PlayerShip(initialPos, PlayerValues.MAINSHIP.getValueFromKey("MAXHEALTH"), PlayerValues.MAINSHIP.getValueFromKey("MAXSPEED"),
                                                PlayerValues.MAINSHIP.getValueFromKey("FIRERATE"), PlayerValues.MAINSHIP.getValueFromKey("ROTATIONSPEED"));
        playerShip.setSprite(sprite);
        playerShip.setGun(GunFactory.shootgun(playerShip)); //Needs proper playergun
    }


    @Override
    public Vec2 move(float deltaTime) {
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
        sprite.setRotate(playerShip.getYaw());
        return sprite;
    }

    @Override
    public void destroy() {
        playerShip.destroy();
    }

}
