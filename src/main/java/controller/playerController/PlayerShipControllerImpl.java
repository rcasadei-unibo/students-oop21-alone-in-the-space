package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GunFactory;
import model.PlayerShip;
import model.Ship;
import utilities.EnumInt;
import utilities.InputCommands;
import utilities.PlayerGunValues;
import utilities.PlayerValues;

public class PlayerShipControllerImpl implements PlayerShipController {

    private PlayerShip playerShip;
    private int currentLives;
    private int currentLevel;
    private int exp;
    private int score;
    private float fireRate;
    private float gunRechargeTime = 0;
    private boolean hasFired = false;
    private boolean hasPowerUp = false;
    private boolean activePowerUp = false;

    public PlayerShipControllerImpl() {
        this.currentLives = EnumInt.LIVES.getValue();
        this.exp = 0;
        this.score = 0;
        this.currentLevel = 1;
    }

    public PlayerShipControllerImpl(Vec2 initialPos, Image sprite) {
        this();
        initialisePlayerShip(initialPos, sprite);
    }

    @Override
    public void initialisePlayerShip(Vec2 initialPos, Image sprite) {
        playerShip          =   new PlayerShip(initialPos,
                                               PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH"),
                                               PlayerValues.MAIN_SHIP.getValueFromKey("MAXSPEED"),
                                               PlayerValues.MAIN_SHIP.getValueFromKey("ROTATIONSPEED"));
        fireRate            =                  PlayerValues.MAIN_SHIP.getValueFromKey("FIRERATE");
        playerShip.setGun(GunFactory.playerGun(playerShip,
                                               (int) PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE"),
                                               PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                                               PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                                               PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED")));
        playerShip.setSprite(sprite);
    }

    public Ship getPlayerShip(){
        return this.playerShip;
    }

    @Override
    public void thrust(InputCommands input) {
        playerShip.thrust(input);
    }

    @Override
    public void rotate(InputCommands input) {
        playerShip.rotate(input);
    }

    private void move(long deltaTime) {
        playerShip.move(deltaTime);
    }
    public void thrustReleased() {
        playerShip.thrustReleased();
    }

    public void update(long deltaTime) {
        long deltaTimeF = ( deltaTime) / 1000;
        move(deltaTimeF);
        playerShip.decaySpeed();
        display();
        if(this.hasFired) {
            this.gunRechargeTime += deltaTime;
            if(this.gunRechargeTime >= 1000/fireRate) {
                this.hasFired = false;
                this.gunRechargeTime = 0;
            }
        }
    }

    @Override
    public void shot() {
        if(!hasFired) {
            this.playerShip.shot();
            this.hasFired = true;
            this.gunRechargeTime = 0;
        }
    }

    @Override
    public void activatePowerUp() {
        if(hasPowerUp) {
            fireRate *= 2;
            this.activePowerUp = true;
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
        this.currentLives--;
        playerShip.destroy();
    }

    public int getCurrentLives() {
        return this.currentLives;
    }

    public void addLives() {
        this.currentLives++;
    }

    public int getExp() {
        return this.exp;
    }
    public int getScore() {
        return this.score;
    }

    public void setExp(int newPoints) {
        this.exp = newPoints;
    }
    public void setScore(int newScore) {
        this.score = newScore;
    }
    public void addScoreExp(int expGained) {
        this.score += expGained;
        this.exp += expGained;
        if(checkLevelUp()) {
            this.levelUp();
        }
    }

    public void levelUp() {
        this.playerShip.setMaxHealth(5 * (PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH"))/100) ;
        this.playerShip.setHealth(this.playerShip.getMaxHealth());

        if(this.currentLevel % 3 == 0) {
            this.fireRate += 5 * (PlayerValues.MAIN_SHIP.getValueFromKey("FIRERATE"))/100;
        }

        if(this.currentLevel % 5 == 0) {
            this.gunLevelUp((int) (5 * (PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE"))/100));
        }

        this.exp -= EnumInt.EXP_REQUIRED.getValue()*(Math.pow(2, this.currentLevel-1));
    }

    private void gunLevelUp(int newDamage) {
        this.playerShip.setGun(GunFactory.playerGun(this.playerShip,
                                                    (int)PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE") + newDamage,
                                                    PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                                                    PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                                                    PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED")));
    }

    public boolean checkLevelUp() {
        return this.exp >= EnumInt.EXP_REQUIRED.getValue() * (Math.pow(2, this.currentLevel - 1));
    }

}
