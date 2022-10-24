package model.hud;

public interface HUDPoints {

    /**
     * @return points.
     */
    int getPoints();

    /**
     * Increase the points.
     */
    void pointsUp();

    /**
     * Set the points to the chosen value.
     * @param value
     */
    void setPoints(int value);


}
