package model.hud;

public interface HUDLife {

    /**
     * @return life points.
     */
    int getLifePoints();

    /**
     * @return game status.
     */
    boolean getGameStatus();

    /**
     * update the HUD with the new stats
     */
    void update(int lifePoints);
}
