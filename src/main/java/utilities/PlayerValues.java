package utilities;

import java.util.HashMap;

public enum PlayerValues {

    MAINSHIP(100,35,2,2);

    private HashMap<String, Float> shipValues = new HashMap<>();

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
