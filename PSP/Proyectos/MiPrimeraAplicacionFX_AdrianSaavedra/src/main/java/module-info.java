module com.example.miprimeraaplicacionfx_adriansaavedra {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.miprimeraaplicacionfx_adriansaavedra to javafx.fxml;
    exports com.example.miprimeraaplicacionfx_adriansaavedra;
}