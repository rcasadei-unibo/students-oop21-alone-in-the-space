package controller.gameEngine;

import com.almasb.fxgl.core.math.Vec2;
import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.gameController.GameControllerImpl;
import controller.gameSwitcher.SceneController;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import model.EnemyFactory;
import model.Ship;
import utilities.EnumInt;
import view.GameMap;
import view.GameMapImpl;
import view.WindowManager;
import view.WindowManagerImpl;

/**
 * 
 * Class that implements GameEngine interface.
 *
 */
public class GameEngineImpl extends AnimationTimer {

    private static final long SLEEP = 10_000_000;
    private static final int SLEEP_TIMER = 100_000;
    private static final double VALUE = 1e9;

    private static final long PERIOD = 100L;
    private GameControllerImpl game;
    private long enemyTimer;
    private static final long DELTAENEMY = 5000L;
    private double difficultFactor = 1;
    private EventController event;
    private Stage stage;
    private String playerName;
    private final GameMapImpl gameMap;
    private final SceneController sceneController;
    private final WindowManager windowManager;

    private long prevTime;

    public GameEngineImpl(final SceneController sceneController) {
        this.sceneController = sceneController;
        this.windowManager = new WindowManagerImpl(this.sceneController);
        this.stage = this.windowManager.getStage();
        this.gameMap = new GameMapImpl(EnumInt.WIDTH.getValue(), EnumInt.HEIGHT.getValue(), this);
        this.windowManager.addGameMap(this.gameMap);
        this.game = new GameControllerImpl(this.gameMap);
        this.game.setInputController(this.sceneController.getInputController());
        this.event = new EventControllerImpl(this.gameMap);
        this.enemyTimer = 0;
    }

    @Override
    public void handle(final long now) {
        try {
            Thread.sleep(0, SLEEP_TIMER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(gameMap.getActiveEnemyShips().size());
        System.out.println(gameMap.getActiveEntities().size());
        System.out.println(gameMap.getBulletsShotByEnemies().size());
        System.out.println(gameMap.getBulletsShotByPlayer().size());
        if (this.enemyTimer == 0) {
            System.out.println("time : " + (now - this.enemyTimer));
            this.enemyTimer = now; 
        }
        System.out.println("time : " + (now - this.enemyTimer) / 1_000_000_000L);
        if ((now - this.enemyTimer) / 1_000_000L > (DELTAENEMY / this.difficultFactor)) {
            System.out.println("new enemy");
            this.enemyTimer = now;
            this.difficultFactor *= 1.02;
            this.gameMap.addEnemyShip(randomShip());
        }
        this.game.update(now - prevTime);
        this.prevTime = now;
    }

    public double getFrameRateHertz(final long delta) {
        final double frameRate = 1d / delta;
        return frameRate * VALUE;
    }

    public long getTimeSleep() {
        return SLEEP;
    }

    /*
     * @Override public void mainLoop() { long lastTime =
     * System.currentTimeMillis(); while (event.checkGameStatus()) { long current =
     * System.currentTimeMillis(); long elapsed = (current - lastTime);
     * processInput(); update(elapsed); render(); try { waitForNextFrame(current); }
     * catch (IllegalArgumentException e) { e.printStackTrace(); } lastTime =
     * current; } }
     * 
     * @Override public void processInput() { // TODO Auto-generated method stub }
     * 
     * @Override public void update(long elapsed) { // TODO Auto-generated method
     * stub this.game.update(elapsed); long current = System.currentTimeMillis(); if
     * ((current - this.enemyTimer) > DELTAENEMY / this.difficultFactor) {
     * this.gameMap.addEnemyShip(randomShip()); this.enemyTimer = current;
     * this.difficultFactor *= 1.02; } }
     */
    private Ship randomShip() {
	// TODO Auto-generated method stub
	//System.out.println("new enemy!");
	final int typeShip = (int) (Math.random() * 3) + 1;
	final Vec2 spawnPosition = new Vec2( this.gameMap.getWidth().floatValue() / 2, 0);
	spawnPosition.setFromAngle(Math.random() * 360);
	spawnPosition.addLocal(this.gameMap.getWidth().floatValue() / 2,  this.gameMap.getHeight().floatValue() / 2);
	Ship enemy = null;
	switch (typeShip) {
	case 1:
	    enemy = EnemyFactory.BasicEnemy(spawnPosition);
	case 2:
	    enemy = EnemyFactory.MissileEnemy(spawnPosition);
	case 3:
	    enemy = EnemyFactory.RifleEnemy(spawnPosition);
	default:
	    enemy = EnemyFactory.BasicEnemy(spawnPosition);
	    break;
	}
	this.gameMap.getPlayer().getPosition();
	enemy.setTarget(this.gameMap.getPlayer());
	return enemy;
    }
    /*
     * @Override public void render() { // TODO Auto-generated method stub }
     * 
     * @Override public void initGame() {
     * 
     * }
     * 
     * protected void waitForNextFrame(final long current) { long dt =
     * System.currentTimeMillis() - current; if (dt < PERIOD) { try {
     * Thread.sleep(PERIOD - dt); } catch (IllegalArgumentException |
     * InterruptedException e) { e.printStackTrace(); } } }
     */

    public Stage getStage() {
	return this.stage;
    }

    public void setPlayerName(final String name) {
	this.playerName = name;
    }

    public String getPlayerName() {
	return this.playerName;
    }

    public GameMap getGameMap() {
	return this.gameMap;
    }

    @Override
    public void stop() {

    }

    public SceneController getSceneController() {
	return this.sceneController;
    }

}
