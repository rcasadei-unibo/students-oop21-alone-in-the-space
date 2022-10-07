package view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Bullet;
import model.Ship;

import java.util.Set;

import controller.GameEngine;
public class GameMapImpl implements GameMap{

    @Override
    public AnchorPane getGameContainer() {
        return null;
    }

    @Override
    public Number getWidth() {

        return null;
    }

    @Override
    public Number getHeight() {

        return null;
    }

    @Override
    public void setPlayer(Ship player) {

    }

    @Override
    public Ship getPlayer() {

        return null;
    }

    @Override
    public Set<Ship> getActiveEnemyShips() {

        return null;
    }

    @Override
    public Set<Bullet> getBulletsShotByPlayer() {

        return null;
    }

    @Override
    public void addPlayerBullet(Bullet bullet) {

    }

    @Override
    public Set<Bullet> getBulletsShotByEnemies() {

        return null;
    }

    @Override
    public void addEnemyBullet(Bullet bullet) {

    }

    @Override
    public Scene getScene() {

        return null;
    }

    @Override
    public void setScene(Scene scene) {

    }

    @Override
    public void setStageReference(Stage stage) {

    }

    @Override
    public Stage getStage() {

        return null;
    }

    @Override
    public void addEnemyShip(Ship enemy) {

    }

    @Override
    public void setGameEngine(GameEngine gameEngine) {

    }

    @Override
    public GameEngine getGameEngine() {
        return null;
    }
}
