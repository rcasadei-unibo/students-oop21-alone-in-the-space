package model.status;

public class StatusImpl implements Status{

    private int points;
    private int lifePoints;
    private int lives;

    public StatusImpl(int points, int lifePoints, int lives) {
        this.points = points;
        this.lifePoints = lifePoints;
        this.lives = lives;
    }

    @Override
    public void setPoints(int value) {
        this.points = value;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setLifePoints(int value) {
        this.lifePoints = value;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public void setLives(int value) {
        this.lives = value;
    }

    @Override
    public int getLives() {
        return this.lives;
    }
}
