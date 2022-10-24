package model.hud;

public interface HUDLife {

    /**
     * @return life points.
     */
    int getLifePoints();

    /**
     * It decreases life points.
     */
    void lifeDown();

    /**
     * @return game status.
     */
    boolean getStatus();
}
