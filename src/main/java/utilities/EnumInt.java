package utilities;

public enum EnumInt {
    /**
     *
     */
    ZERO(0),

    /**
     *
     */
    ONE(1),

    /**
     *
     */
    TWO(2),

    /**
     *
     */
    THREE(3),

    /**
     *
     */
    FOUR(4),

    /**
     *
     */
    FIVE(5),

    /**
     *
     */
    SEVEN(6),

    /**
     *
     */
    TEN(10),

    /**
     *
     */
    WIDTH(2730),

    /**
     *
     */
    HEIGHT(1536),

    /**
     *
     */
    DAMAGE_COLLISION(50);

    private final int value;

    private EnumInt(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
