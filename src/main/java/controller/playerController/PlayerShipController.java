package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Bullet;
import model.Ship;
import utilities.InputCommands;

/**
 * Used to control and modify PlayerShip class
 */
public interface PlayerShipController  {
    /**
     * Initialises PlayerShip, keeps the original PlayerGun that levels up with the Player
     * @param initialPos position where the PlayerShip spawns
     * @param sprite image of the ship
     */
    void initialisePlayerShip(Vec2 initialPos, Image sprite);

    /**
     * Displays sprite based on PlayerShip position and rotation, updates timers for FireRate and PowerUps
     * @param deltaTime time elapsed
     * @return returns the Player sprite
     */
    ImageView update(long deltaTime);

    /**
     * updates the sprite based on PlayerShip
     * @return ImageView of the sprite
     */
    ImageView display();

    /**
     * destroys PlayerShip
     */
    void destroy();

    /**
     * getter of PlayerShip
     * @return PlayerShip
     */
    Ship getPlayerShip();

    /**
     * acceleration based on the user input
     * @param input direction of the thrust
     */
    void thrust(InputCommands input);

    /**
     * rotation based on the user input
     * @param input direction of the rotation
     */
    void rotate(InputCommands input);

    /**
     * shoots a bullet then enters cooldown until it can fire again
     * @return PlayerBullet that has been just shot
     */
    Bullet shot();

    /**
     * getter method
     * @return current lives of the Player
     */
    int getCurrentLives();

    /**
     * add one extra life to the Player
     */
    void addLivesBy1();

    /**
     * getter
     * @return how many EXP points the player has
     */
    int getExp();

    /**
     * getter
     * @return player score
     */
    int getScore();

    /**
     * adds score to both the Player score and EXP amount, checks for level ups; EXP and score correlate
     * @param scoreGained how many points the player gains on kill
     */
    void addScoreExp(int scoreGained);

    /**
     * setter
     * @param newScore
     */
    void setScore(int newScore);

    /**
     * setter
     * @param newExp
     */
    void setExp(int newExp);

    /**
     * if the player has the power up, it can activate it to enter Powered Up mode, enhancing damage and fire rate
     * ends after 30 secs
     */
    void activatePowerUp();

    /**
     * checks if the player has let go of the thrust inputs (UP and DOWN)
     * consequently the speed will decay
     */
    void thrustReleased();
}
