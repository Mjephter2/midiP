module midiP {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;
    requires org.apache.logging.log4j;

    opens sample.views;
}