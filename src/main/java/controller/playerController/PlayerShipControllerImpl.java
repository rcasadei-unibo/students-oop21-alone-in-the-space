package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
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
    private int gunDamage = (int) PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE");
    private Gun normalPlayerGun;
    private long powerTime = 0;

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
        this.playerShip = new PlayerShip(initialPos,
                                                PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH"),
                                                PlayerValues.MAIN_SHIP.getValueFromKey("MAXSPEED"),
                                                PlayerValues.MAIN_SHIP.getValueFromKey("ROTATIONSPEED"));
        this.normalPlayerGun = GunFactory.playerGun(this.playerShip,
                                        (int)   PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE"),
                                                PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                                                PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                                                PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED"));
        this.fireRate = PlayerValues.MAIN_SHIP.getValueFromKey("FIRERATE");
        this.playerShip.setGun(this.normalPlayerGun);
        this.playerShip.setSprite(sprite);
    }

    public Ship getPlayerShip() {
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
    public void thrustReleased() {
        playerShip.thrustReleased();
    }

    public ImageView update(long deltaTime) {
        if (this.hasFired) {
            this.gunRechargeTime += deltaTime;
            if (this.gunRechargeTime >= 1000 / fireRate) {
                this.hasFired = false;
                this.gunRechargeTime = 0;
            }
        }
        if (this.activePowerUp) {
            this.powerTime += deltaTime;
            if (this.powerTime >= EnumInt.POWER_UP_DURATION.getValue()) {
                this.activePowerUp = false;
                this.powerTime = 0;
                this.endPowerUp();
            }
        }
        return display();
    }

    @Override
    public Bullet shot() {
        Bullet bullet = null;
        if (!this.hasFired) {
            bullet = this.playerShip.shot();
            this.hasFired = true;
            this.gunRechargeTime = 0;
        }
        return bullet;
    }

    @Override
    public void activatePowerUp() {
        if (hasPowerUp && !activePowerUp) {
            this.hasPowerUp = false;
            this.fireRate *= 2;
            this.gunDamage *= 2;
            //bit jank, but it will have to do; original playergun is remembered here
            this.playerShip.setGun(GunFactory.playerGun(this.playerShip,
                                                        gunDamage,
                                                        PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                                                        PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                                                        PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED")));
            this.activePowerUp = true;
        }
    }

    private void endPowerUp() {
        this.fireRate /= 2;
        this.gunDamage /= 2;
        this.playerShip.setGun(normalPlayerGun);
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

    public void addLivesBy1() {
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
        //TODO: increase requirement naturally
        if ((this.score + expGained) >= EnumInt.POWER_UP_SCORE.getValue()) {
            this.hasPowerUp = true;
        }
        this.score += expGained;
        this.exp += expGained;
        if (checkLevelUp()) {
            this.levelUp();
        }
    }

    private void levelUp() {
        /* every level the player gains 5% MaxHP of the original MaxHP value and gets healed
        * every 3 levels, fire rate increases by 5% of the original value
        * every 5 levels, gun damage is increased by 5% of the original value
        */
        this.playerShip.setMaxHealth(5 * (PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH")) / 100);
        this.playerShip.setHealth((int) this.playerShip.getMaxHealth());

        if (this.currentLevel % 3 == 0) {
            this.fireRate += 5 * (PlayerValues.MAIN_SHIP.getValueFromKey("FIRERATE")) / 100;
        }

        if (this.currentLevel % 5 == 0) {
            this.gunDamage += 5 * (PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE")) / 100;
            this.gunLevelUp(gunDamage);
        }

        this.exp -= EnumInt.EXP_REQUIRED.getValue() * (Math.pow(2, this.currentLevel - 1));
    }

    private void gunLevelUp(int newDamage) {
        //modify the playergun in here to keep it updated for whenever the player activated Power Up mode
        this.normalPlayerGun = GunFactory.playerGun(this.playerShip,
                (int)        PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE") + newDamage,
                                    PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                                    PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                                    PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED"));
        this.playerShip.setGun(normalPlayerGun);
    }

    private boolean checkLevelUp() {
        //level requirement increases exponentially
        return this.exp >= EnumInt.EXP_REQUIRED.getValue() * (Math.pow(2, this.currentLevel - 1));
    }

}
