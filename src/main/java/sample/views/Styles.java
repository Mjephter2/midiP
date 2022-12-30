package sample.views;

/**
 * Utility Class to maintain all CSS.
 * This is probably not good practice.
 * Will reevaluate later.
 */
public final class Styles {
    public static final String whiteKeysPressedCss = "-fx-background-color: linear-gradient(#fff 0%,#b9b9b9 100%);"
            + " -fx-background-radius: 0 0 4 4;"
            + " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 3 );"
            + "  -fx-border-insets: 10 0 0 0;";
    public static final String whiteKeysReleasedCss = "-fx-background-color: linear-gradient(to bottom,#eee 0%,#fff 100%);"
            + " -fx-background-radius: 0 0 5 5;"
            + " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );";

    public static final String blackKeysReleasedCss = "-fx-background-color: linear-gradient(to bottom left, #111 0%, #555 100%);"
            + " -fx-border-radius:0 0 3 3;"
            + "  -fx-border-color: #000;";
    public static final String blackKeysPressedCSs = "-fx-background-color: linear-gradient(to top right, #111 0%, #555 100%);"
            + " -fx-padding: 0 0 0 -1;" +
            "  -fx-border-insets: 0 0 1 1;" +
            "  -fx-background-insets: 0 0 1 1;" +
            "  -fx-border-style: solid inside;" +
            "  -fx-border-width: 1;" +
            " -fx-border-radius:0 0 3 3;"
            + "  -fx-border-color: #000;";
}
