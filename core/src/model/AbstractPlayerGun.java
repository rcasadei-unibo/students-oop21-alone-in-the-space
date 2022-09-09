package model;

import com.badlogic.gdx.math.Vector2;

public class AbstractPlayerGun implements gun{
    //TODO basically fallback option for the basicgun, powerups change stats or way of firing (TBD?)
    @Override
    public Bullet shot() {
        return null;
    }

    @Override
    public Boolean range(Vector2 shipPos, Vector2 enemyPos) {
        return null;
    }

    @Override
    public float getDegRange() {
        return 0;
    }
}
