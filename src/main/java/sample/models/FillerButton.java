package sample.models;

import javafx.scene.control.Button;

/**
 * Class implementing a Button used to fill space
 * when designing a keyboard array.
 */
public class FillerButton extends Button {

    /**
     * Constructs a Filler Button with the given
     * width and height.
     * @param width width of the Button
     * @param height height of the Button
     */
    public FillerButton(final double width, final double height) {
        this.setVisible(false);
        this.setPrefSize(width, height);
    }
}
