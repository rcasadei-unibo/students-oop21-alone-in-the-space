package model;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class AbstractPlayerGun implements Gun{
    //TODO basically fallback option for the basicgun, powerups change stats or way of firing (TBD?)
    @Override
    public Bullet shot() {
        return null;
    }

    @Override
    public Boolean refreshRange(Vector2 shipPos, Vector2 direction, List<Vector2> enemyPos) {
        return null;
    }

    public Boolean range(Vector2 shipPos, Vector2 enemyPos) {
        return null;
    }

    @Override
    public float getDegRange() {
        return 0;
    }
}
