package view;

import controller.gameEngine.GameEngineImpl;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Bullet;
import model.Entity;
import model.Ship;
import model.status.StatusImpl;
import utilities.EnumInt;

import java.util.HashSet;
import java.util.Set;
public class GameMapImpl implements GameMap {

    private Set<Entity> entities;
    private Set<Bullet> playerBullets;
    private Set<Bullet> enemyBullets;
    private Set<Ship> enemyShips;

    private Ship player;
    private ImageView backGroundImage;
    private Scene scene;
    private GameEngineImpl gameEngine;
    private AnchorPane gameContainer;
    private Stage stage;

    private StatusImpl status;

    private final int width;
    private final int height;
    private int shipCounter=1;
    /**
     *
     * @param width
     * @param height
     */
    public GameMapImpl(final int width, final int height, final GameEngineImpl engine) {
        this(width, height);
        this.setGameEngine(engine);
    }

    public GameMapImpl(final int width2, final int height2) {
        this.gameContainer = new AnchorPane();
        this.gameContainer.prefWidth(width2);
        this.gameContainer.prefHeight(height2);

        this.entities = new HashSet<>();
        this.playerBullets = new HashSet<>();
        this.enemyBullets = new HashSet<>();
        this.enemyShips = new HashSet<>();

        this.backGroundImage = new ImageView();

        this.width = width2;
        this.height = height2;

    }
    @Override
    public AnchorPane getGameContainer() {
        return this.gameContainer;
    }

    public final Set<Entity> getActiveEntities() {
        return this.entities;
    }

    @Override
    public Number getWidth() {
        return this.width;
    }

    @Override
    public Number getHeight() {
        return this.height;
    }

    @Override
    public void setPlayer(Ship player) {
        this.player = player;
        this.player.getNode().setId(String.valueOf(this.shipCounter++));
        this.gameContainer.getChildren().add(this.player.getNode());
        this.entities.add(player);
    }

    @Override
    public Ship getPlayer() {
        return this.player;
    }

    @Override
    public Set<Ship> getActiveEnemyShips() {
        return this.enemyShips;
    }

    @Override
    public Set<Bullet> getBulletsShotByPlayer() {
        return this.playerBullets;
    }

    @Override
    public void addPlayerBullet(final Bullet bullet) {
	bullet.getNode().setId(String.valueOf(this.shipCounter++));
	Node var = null;
        this.entities.add(bullet);
        this.playerBullets.add(bullet);
        this.gameContainer.getChildren().add(this.player.getNode());
        this.gameContainer.getChildren().add(bullet.getNode());
    }

    @Override
    public Set<Bullet> getBulletsShotByEnemies() {
        return this.enemyBullets;
    }

    @Override
    public void addEnemyBullet(Bullet bullet) {
	bullet.getNode().setId(String.valueOf(this.shipCounter++));
        this.entities.add(bullet);
        this.enemyBullets.add(bullet);
        this.gameContainer.getChildren().add(bullet.getNode());
    }

    @Override
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
        this.gameContainer.getChildren().remove(entity.getNode());
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
        System.out.println(scene.getX());
        System.out.println(scene.getY());
    }

    @Override
    public void setStageReference(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setBackgroundImage(String path) {
            this.backGroundImage = new ImageView("images/skybox13.jpg");
            this.backGroundImage.setLayoutX(EnumInt.ZERO.getValue());
            this.backGroundImage.setLayoutY(EnumInt.ZERO.getValue());
            this.backGroundImage.setFitWidth(this.width);
            this.backGroundImage.setFitHeight(this.height);
            this.gameContainer.getChildren().add(this.backGroundImage);
    }

    @Override
    public Node getBackground() {
        return this.backGroundImage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void addEnemyShip(Ship enemy) {
	enemy.getNode().setId(String.valueOf(this.shipCounter++));
        this.enemyShips.add(enemy);
        this.entities.add(enemy);
        this.gameContainer.getChildren().add(enemy.getNode());
    }

    @Override
    public void setGameEngine(GameEngineImpl gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }

    @Override
    public void setStatus(StatusImpl status) {
        this.status = status;
    }

    @Override
    public StatusImpl getStatus() {
        return this.status;
    }

}
