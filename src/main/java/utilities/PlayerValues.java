package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * default player ship values stored here for convenience’s sake
 */
public enum PlayerValues {

    MAIN_SHIP(100, 35, 2, 2);

    private Map<String, Float> shipValues = new HashMap<>();

    PlayerValues(final float maxHealth, final float maxSpeed, final float fireRate, final float rotationSpeed) {
        shipValues.put("MAXHEALTH", maxHealth);
        shipValues.put("MAXSPEED", maxSpeed);
        shipValues.put("FIRERATE", fireRate);
        shipValues.put("ROTATIONSPEED", rotationSpeed);
    }

    public float getValueFromKey(String key) {
        return shipValues.get(key);
    }
}

