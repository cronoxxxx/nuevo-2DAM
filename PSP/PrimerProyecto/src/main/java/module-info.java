module com.example.primerproyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.primerproyecto to javafx.fxml;
    exports com.example.primerproyecto;
}