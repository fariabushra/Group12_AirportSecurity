module com.example.airportsecurity {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.airportsecurity to javafx.fxml;
    exports com.example.airportsecurity;
}