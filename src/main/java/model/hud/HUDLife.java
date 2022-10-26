package model.hud;

public interface HUDLife {

    /**
     * @return life points.
     */
    int getLifePoints();

    /**
     * It decreases life points.
     */
    void lifeDown(final int damage);

    /**
     * @return game status.
     */
    boolean getStatus();
}
