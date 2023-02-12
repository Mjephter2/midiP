// Wikipedia Reference: https://en.wikipedia.org/wiki/Mode_(music)
package sample.models.scales;

/**
 * enum for Scale modes
 */
public enum ScaleMode {

    /**
     * Ionian Mode.
     * Same as base Major Scale
     */
    IONIAN(0),

    /**
     * Dorian Mode.
     * Same as base Major Scale but starting from the second (2) note
     */
    DORIAN(1),

    /**
     * Phrygian Mode.
     * Same as base Major Scale but starting from the third (3) note
     */
    PHRYGIAN(2),

    /**
     * Lydian Mode.
     * Same as base Major Scale but starting from the fourth (4) note
     */
    LYDYAN(3),

    /**
     * Mixolydian Mode.
     * Same as base Major Scale but starting from the fifth (5) note
     */
    MIXOLYDIAN(4),

    /**
     * Aeolian Mode.
     * Same as base Major Scale but starting from the six (6) note
     */
    AEOLIAN(5),

    /**
     * Locrian Mode.
     * Same as base Major Scale but starting from the fifth (5) note
     */
    LOCRIAN(6);

    private final int value;

    ScaleMode(final int i) {
        this.value = i;
    }

    int getValue() {
        return value;
    }
}
