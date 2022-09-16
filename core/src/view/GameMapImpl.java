package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Bullet;
import model.GameEngine;
import model.Ship;

import javax.swing.text.html.ImageView;
import java.util.HashSet;
import java.util.Set;
public class GameMapImpl implements GameMap{

    private Set<Entity> entities;
    private Set<Bullet> playerBullets;
    private Set<Bullet> enemyBullets;
    private Set<Ship> enemyShips;

    private Ship player;
    private ImageView[] backGroundImage;
    private Scene scene;
    private GameEngine gameEngine;
    private AnchorPane gameContainer;
    private Stage stage;

    private final int width;
    private final int height;

    /**
     *
     * @param width
     * @param height
     */
    public GameMapImpl(final int width, final int height, final GameEngine engine) {
        this(width, height);
        this.setGameEngine(engine);
    }

    public GameMapImpl(final int width2, final int height2) {
        this.gameContainer = new AnchorPane();
        this.gameContainer.prefWidth(width2);
        this.gameContainer.prefHeight(height2);

        this.entities = new HashSet<Entity>();
        this.playerBullets = new HashSet<Bullet>();
        this.enemyBullets = new HashSet<Bullet>();
        this.enemyShips = new HashSet<Ship>();

        this.backGroundImage = new ImageView[];

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
        this.entities.add(bullet);
        this.playerBullets.add(bullet);
        this.gameContainer.getChildren().add(bullet.getNode());
    }

    @Override
    public Set<Bullet> getBulletsShotByEnemies() {

        return this.enemyBullets;
    }

    @Override
    public void addEnemyBullet(Bullet bullet) {
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

    }

    @Override
    public Node[] getBackground() {
        return new Node[0];
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void addEnemyShip(Ship enemy) {
        this.enemyShips.add(enemy);
        this.gameContainer.getChildren().add(enemy.getNode());
    }

    @Override
    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }
}
