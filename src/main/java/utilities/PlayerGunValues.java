package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * default player gun values stored for convenience’s sake
 */
public enum PlayerGunValues {

    MAIN_GUN(10, 4, 1, 2, 0.01f);

    private Map<String, Float> gunValues = new HashMap<>();

    PlayerGunValues(final float damage, final float maxSpeed, final float acceleration, final float rotationSpeed, final float fireRate) {
        gunValues.put("DAMAGE", damage);
        gunValues.put("MAXSPEED", maxSpeed);
        gunValues.put("ACCELERATION", acceleration);
        gunValues.put("ROTATIONSPEED", rotationSpeed);
        gunValues.put("FIRERATE", fireRate);
    }

    public float getValueFromKey(String key) {
        return gunValues.get(key);
    }
}
