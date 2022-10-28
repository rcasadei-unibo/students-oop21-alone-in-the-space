package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * default player gun values stored for convenience’s sake
 */
public enum PlayerGunValues {

    MAIN_GUN(100, 35, 2, 2);

    private Map<String, Float> shipValues = new HashMap<>();

    PlayerGunValues(final float damage, final float maxSpeed, final float acceleration, final float rotationSpeed) {
        shipValues.put("DAMAGE", damage);
        shipValues.put("MAXSPEED", maxSpeed);
        shipValues.put("ACCELERATION", acceleration);
        shipValues.put("ROTATIONSPEED", rotationSpeed);
    }

    public float getValueFromKey(String key) {
        return shipValues.get(key);
    }
}
