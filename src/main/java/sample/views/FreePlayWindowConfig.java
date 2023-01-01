package sample.views;

import java.awt.Toolkit;
import java.awt.Dimension;

/**
 * Class to generating the FreeWindow GUI components dimensions.
 */
public class FreePlayWindowConfig {
    /**
     * Spacing between the white keys.
     * The 'final' ratio values below are from personal observations
     * from measuring components size that yielded an acceptable view.
     */
    public static final double WHITE_KEY_PANE_SPACING = 1;
    /**
     * Size ratio of white keys height to the full height of the window.
     */
    public static final double WHITE_KEY_HEIGHT_RATIO = 0.6;
    /**
     * Size ratio of white keys width to the full width of the window.
     */
    public static final double WHITE_KEY_WIDTH_RATIO = 1.0 / 55.0;
    /**
     * Size ratio of black keys width to the full height of the window.
     */
    public static final double BLACK_KEY_HEIGHT_RATIO = 0.32;
    /**
     * Width difference between the black and white keys.
     * Hand-picked
     */
    private static final double BW_KEYS_WIDTH_DIFF = 5;
    /**
     * Right padding for black keys.
     */
    private final double blackKeysPaddingRight;
    /**
     * Width of the window.
     */
    private final double windowWidth;

    /**
     * Height of the window.
     */
    private final double windowHeight;
    /**
     * White keys preferred width.
     */
    private final double whiteKeysPrefWidth;
    /**
     * White keys preferred height.
     */
    private final double whiteKeysPrefHeight;
    /**
     * Black keys preferred width.
     */
    private final double blackKeysPrefWidth;
    /**
     * Black keys preferred height.
     */
    private final double blackKeysPrefHeight;
    /**
     * Left Padding for black key Pane.
     */
    private final double blackKeyPaneLeftPadding;
    /**
     * Spacing for black key pane.
     */
    private final double blackKeyPaneSpacing;

    public static double getBwKeysWidthDiff() {
        return BW_KEYS_WIDTH_DIFF;
    }

    public double getBlackKeysPaddingRight() {
        return blackKeysPaddingRight;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public double getWhiteKeysPrefWidth() {
        return whiteKeysPrefWidth;
    }

    public double getWhiteKeysPrefHeight() {
        return whiteKeysPrefHeight;
    }

    public double getBlackKeysPrefWidth() {
        return blackKeysPrefWidth;
    }

    public double getBlackKeysPrefHeight() {
        return blackKeysPrefHeight;
    }

    public double getBlackKeyPaneLeftPadding() {
        return blackKeyPaneLeftPadding;
    }

    public double getBlackKeyPaneSpacing() {
        return blackKeyPaneSpacing;
    }

    /**
     * Constructs WindowConfig given the screen resolution.
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public FreePlayWindowConfig(final double width, final double height) {
        this.windowWidth = width;
        this.windowHeight = height;

        this.whiteKeysPrefWidth = WHITE_KEY_WIDTH_RATIO * windowWidth;
        this.blackKeysPrefWidth = this.whiteKeysPrefWidth - BW_KEYS_WIDTH_DIFF;

        this.whiteKeysPrefHeight = WHITE_KEY_HEIGHT_RATIO * height;
        this.blackKeysPrefHeight = BLACK_KEY_HEIGHT_RATIO * height;

        this.blackKeysPaddingRight = BW_KEYS_WIDTH_DIFF;
        this.blackKeyPaneSpacing = BW_KEYS_WIDTH_DIFF + WHITE_KEY_PANE_SPACING;
        this.blackKeyPaneLeftPadding = this.whiteKeysPrefWidth - (this.blackKeysPrefWidth / 2.0);
    }

    /**
     * @return a pre-defined WindowConfig using screen resolution 1650 x 250.
     */
    public static FreePlayWindowConfig defaultConfig() {
        return new FreePlayWindowConfig(1650.0, 250.0);
    }

    /**
     * @return a pre-defined Window Config with width
     * equal the full width of the screen.
     */
    public static FreePlayWindowConfig fullWidthConfig() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new FreePlayWindowConfig(screenSize.getWidth(), screenSize.getHeight() * 0.35);
    }

    /**
     * @return a WindowConfig with width 1200.
     */
    public static FreePlayWindowConfig config1200() {
        return customConfig(1200.0, 250.0);
    }

    /**
     * @return a WindowConfig with width 1500.
     */
    public static FreePlayWindowConfig config1500() {
        return customConfig(1500.0, 300.0);
    }

    /**
     * @param width width of the window
     * @param height height of the window
     * @return a WindowConfig using the given height and width if given width is > 1200
     * and height is > 200, otherwise return the defaultConfig.
     */
    public static FreePlayWindowConfig customConfig(final Double width, final Double height) {
        if (width < 1200 || height < 200) {
            System.out.println("Specified Width or height is too small.\nDefaulting to the defaultConfig.");
            return defaultConfig();
        } else if (width > Toolkit.getDefaultToolkit().getScreenSize().getWidth()) {
            return fullWidthConfig();
        }
        return new FreePlayWindowConfig(width, height);
    }

    /**
     * @return a String representation of the WindowConfig object.
     */
    public String toString() {
        StringBuilder rep = new StringBuilder();
        rep.append("Window width: ").append(windowWidth)
                .append("\nWindow height: ").append(windowHeight)
                .append("\nWhiteKeysPrefWidth: ").append(whiteKeysPrefWidth)
                .append("\nwhiteKeysPrefHeight: ").append(whiteKeysPrefHeight)
                .append("\nblackKeysPrefWidth: ").append(blackKeysPrefWidth)
                .append("\nblackKeysPrefHeight: ").append(blackKeysPrefHeight)
                .append("\nblackKeyPaneLeftPadding: ").append(blackKeyPaneLeftPadding)
                .append("\nblackKeysPaddingRight: ").append(blackKeysPaddingRight)
                .append("\nblackKeyPaneSpacing: ").append(blackKeyPaneSpacing);
        return rep.toString();
    }
}
