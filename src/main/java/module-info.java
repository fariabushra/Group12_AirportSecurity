module com.example.airportsecurity {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.github.librepdf.openpdf;

    opens com.example.airportsecurity to javafx.fxml;
    opens com.example.airportsecurity.Tabib to javafx.fxml;
    opens com.example.airportsecurity.Faria to javafx.fxml;
    opens com.example.airportsecurity.Samia to javafx.fxml; // Make sure this folder exists!

    exports com.example.airportsecurity;
    exports com.example.airportsecurity.Tabib;
    exports com.example.airportsecurity.Faria;
    exports com.example.airportsecurity.Samia;
}