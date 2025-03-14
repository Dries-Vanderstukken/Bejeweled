module be.kdg.bejeweledtake2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens be.kdg.bejeweledtake2 to javafx.fxml;
    exports be.kdg.bejeweledtake2;
}