package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.DataClasses.FillerButton;
import sample.DataClasses.Utilities;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FreePlayWindow extends Application {
    private static final int NUMBER_OF_KEYS = 88;
    private Button[] keyBoard = new Button[NUMBER_OF_KEYS];
    private LinkedList<Button> whiteKeys = new LinkedList<>();
    private LinkedList<Button> blackKeys = new LinkedList<>();
    private static int screenWidth = 1248;
    private static int whiteKeySize = 24;
    private static int blackKeySize = 20;
    private String buttonOriginalStyle;
    private String black_buttonOriginalStyle;
    private MidiInputReceiver midiInputReceiver = new MidiInputReceiver("Receiver");
    private int lastKeyPressedIndex = 0;

    {
        int i = 0;
        while(i < 88){
            ArrayList<Integer> blackIndex = new ArrayList<>();
            blackIndex.add(1);
            blackIndex.add(4);
            blackIndex.add(6);
            blackIndex.add(9);
            blackIndex.add(11);
            Button button = new Button(Utilities.NOTE_NAMES.get(i));
            if(blackIndex.contains(i % 12)){
                blackKeys.add(button);
                //System.out.println("Added " + Utilities.NOTE_NAMES.get(i) + " to the black keys");
            }else{
                whiteKeys.add(button);
                //System.out.println("Added " + Utilities.NOTE_NAMES.get(i) + " to the white keys");
            }
            keyBoard[i] = button;
            i++;
        }

        //System.out.println("white keys: " + whiteKeys.size());
        //System.out.println("black keys: " + blackKeys.size());
        buttonOriginalStyle = keyBoard[0].getStyle();
        black_buttonOriginalStyle = keyBoard[1].getStyle();
    }

    public void openAllTransmitters(){
        MidiDevice device;
    MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for(int i = 0; i < infos.length; i++) {
        try {
            device = MidiSystem.getMidiDevice(infos[i]);
            int numTrans = device.getTransmitters().size();
            System.out.println(infos[i] + " has " + numTrans + " transmitters" );
            List<Transmitter> transmitters = device.getTransmitters();
            for (int j = 0; j < transmitters.size(); j++) transmitters.get(j).setReceiver(midiInputReceiver);
            Transmitter trans = device.getTransmitter();
            trans.setReceiver(midiInputReceiver);
            device.open();
            System.out.println(device.getDeviceInfo() + " Was Opened");
        } catch (MidiUnavailableException e) {

        }
    }
}
    public void closeAllTransmitters(){
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for(int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                if(device.isOpen()) {
                    device.close();
                    System.out.println(device.getDeviceInfo() + " Was closed");
                }
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage freePlay) {
        openAllTransmitters();
        BorderPane root = new BorderPane();
        freePlay.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
//                Main mainWindow = new Main();
//                Stage mainStage = new Stage();
//                try {
//                    mainWindow.start(mainStage);
//                    freePlay.close();
//                    mainStage.show();
//                } catch (Exception ex) {
//                    System.out.println("Error opening Main Window!!!");
//                    ex.printStackTrace();
//                }
                closeAllTransmitters();
                midiInputReceiver.close();
                System.exit(0);
            }
        });

        HBox white_keyPane = new HBox();
        white_keyPane.setPickOnBounds(false);
        HBox black_keyPane = new HBox();
        black_keyPane.setPickOnBounds(false);
        black_keyPane.setPadding(new Insets(0,0,0,13));
        black_keyPane.setSpacing(4);
        white_keyPane.setSpacing(0.4);

        //System.out.println(screenSize.width);
        for(Button button: whiteKeys){
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(whiteKeySize,112);
            white_keyPane.getChildren().add(button);
        }
        for(Button button: blackKeys){
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(blackKeySize,80);
            black_keyPane.getChildren().add(button);
            button.setStyle("-fx-background-color: black");
        }
        black_keyPane.getChildren().add(1, new FillerButton(blackKeySize,80));
        int i = 1;
        while(i < black_keyPane.getChildren().size() - 1){
            black_keyPane.getChildren().add(i + 3, new FillerButton(blackKeySize,80));
            black_keyPane.getChildren().add(i + 7, new FillerButton(blackKeySize,80));
            i = i + 7;
        }

        GridPane keyPane = new GridPane();
        //keyPane.setPadding(new Insets(55,20,20,20));
        keyPane.add(white_keyPane,0,0,2,1);
        keyPane.add(black_keyPane,0,0,2,1);
        root.setCenter(keyPane);

        Scene scene = new Scene(root,screenWidth,112);
        freePlay.setFullScreen(false);
        freePlay.setResizable(false);
        freePlay.setTitle("Free Play");
        freePlay.setScene(scene);
        freePlay.show();
    }
    private void keyPressed_Released(int key){
        Button button = keyBoard[key];
        if(button.getStyle().contains("blue") || button.getStyle().contains("red")){
            if(whiteKeys.contains(button)){
                button.setStyle(buttonOriginalStyle);
            }else if(blackKeys.contains(button)){
                button.setStyle("-fx-background-color: black");
            }
        }else {
            if(whiteKeys.contains(button)){
                button.setStyle("-fx-background-color: blue");
            }else if(blackKeys.contains(button)){
                button.setStyle("-fx-background-color: red");
            }
        }
    }

    public class MidiInputReceiver implements Receiver {

        public String name;
        public MidiInputReceiver(String name) {
            this.name = name;
        }
        public void send(MidiMessage msg, long timeStamp) {
            byte[] aMsg = msg.getMessage();
            if((lastKeyPressedIndex == 127 && aMsg[2] == 0) || aMsg[2] == 127) {
                lastKeyPressedIndex = aMsg[2];
                return;
            }
            System.out.println("Message: " + aMsg[0] + ", " + aMsg[1] + ", " + aMsg[2]);
            if(aMsg[1] - 21 < 0) return;
            keyPressed_Released(aMsg[1] - 21);
            lastKeyPressedIndex = aMsg[2];
        }
        @Override
        public void close() {
            closeAllTransmitters();
        }
    }
}
