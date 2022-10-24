package model.hud;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import utilities.EnumInt;
import utilities.EnumString;

public class HUDPointsImpl extends Label implements HUDPoints {

    /*
     * HUD structure
     */
    private static final int X_LAYOUT = 130;
    private static final int Y_LAYOUT = 20;
    private static final int POINTS_UP = 1;
    private static final String YELLOW = "yellow";
    private static final String MATTER = "Points: ";

    private int points;

    public HUDPointsImpl() {
        this.points = EnumInt.ZERO.getValue();

        this.setLayoutX(EnumInt.WIDTH.getValue() - X_LAYOUT);
        this.setLayoutY(Y_LAYOUT);
        this.setText(MATTER + this.getPoints());
        this.setFont(new Font(EnumString.FONT.getValue(), EnumInt.FONT_SIZE.getValue()));
        this.setTextFill(Paint.valueOf(YELLOW));
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void pointsUp() {
        if(this.getPoints() < EnumInt.MAX_POINTS.getValue()) {
            this.setPoints(POINTS_UP);
        }
    }

    @Override
    public void setPoints(int value) {
        this.points += value;
        this.setText(MATTER + this.getPoints());
    }
}
