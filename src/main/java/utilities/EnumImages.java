package utilities;

import javafx.scene.image.Image;

public enum EnumImages {

    /**
     *
     */
    BULLET("assets/images/bullet.png"),

    /**
     *
     */
    BACKGROUND("assets/images/skybox13.png"),

    /**
     *
     */
    PLAYER("assets/images/playerShip.png"),

    /**
     *
     */
    ENEMY("assets/images/enemy.png");

    private Image value;

    /**
     * Constructor.
     *
     * @param path
     */
    private EnumImages(final String path) {
        this.value = new Image(getClass().getResourceAsStream(path));
    }

    /**
     * @return value.
     */
    public Image getImage() {
        return this.value;
    }

}
