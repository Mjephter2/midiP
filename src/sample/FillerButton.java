package sample;

import javafx.scene.control.Button;

public class FillerButton extends Button {

    public FillerButton(int width, int height){
        this.setPrefSize(width, height);
        this.setVisible(false);
    }
}
