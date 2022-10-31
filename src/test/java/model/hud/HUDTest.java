package model.hud;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Test;
import utilities.EnumInt;
import view.GameMap;
import view.GameMapImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public final class HUDTest {

    private HUDLife lifeHUD;
    private HUDPoints pointsHUD;
    private HUDPowerUp powerUpHUD;
    private AnchorPane gamePane;
    private GameMap gameMap;

    /**
     * Constructor.
     */
    public HUDTest(){
        new JFXPanel();
        this.gamePane = new AnchorPane();
        this.gameMap = new GameMapImpl(EnumInt.WIDTH.getValue(), EnumInt.HEIGHT.getValue());
        this.pointsHUD = new HUDPointsImpl();
        this.lifeHUD = new HUDLifeImpl();
        this.powerUpHUD = new HUDPowerUpImpl(this.gameMap);
    }

    @Test
    public void TestLifePointsBehaviour() {
        /*
         * At the beginning of the game, life points are sets to 100.
         */
        assertTrue(this.lifeHUD.getLifePoints() == EnumInt.LIFE_POINTS.getValue());
        assertTrue(this.lifeHUD.getGameStatus());
        System.out.println("Starting life points: " + this.lifeHUD.getLifePoints());
        System.out.println("Starting status: " + this.lifeHUD.getGameStatus() + "\n");

        /*
         *
         */

    }
}
