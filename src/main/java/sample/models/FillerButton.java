package sample.models;

import javafx.scene.control.Button;

public class FillerButton extends Button {

    public FillerButton(int width, int height){
        this.setVisible(false);
        this.setPrefSize(width, height);
    }

}
