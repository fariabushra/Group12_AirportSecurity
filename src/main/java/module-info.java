module com.example.airportsecurity {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.airportsecurity to javafx.fxml;
    opens com.example.airportsecurity.Tabib to javafx.fxml;

    exports com.example.airportsecurity;
    exports com.example.airportsecurity.Tabib;
}