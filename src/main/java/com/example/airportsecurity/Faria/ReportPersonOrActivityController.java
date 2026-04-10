package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDateTime;

public class ReportPersonOrActivityController
{
    @javafx.fxml.FXML
    private TextField locationField;
    @javafx.fxml.FXML
    private TextArea descriptionArea;
    @javafx.fxml.FXML
    private ComboBox<String> reportTypeCombo;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
        reportTypeCombo.getItems().addAll("Suspicious Person", "Suspicious Activity");
    }

    @javafx.fxml.FXML
    public void dashboardButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/checkpostOfficer.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("CheckpostOfficer Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void submitReportButton(ActionEvent actionEvent) {
        String type = reportTypeCombo.getValue();
        String description = descriptionArea.getText();
        String location = locationField.getText();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";


        if (type == null || description.isEmpty() || location.isEmpty() || date.isEmpty()) {
            statusLabel.setText("Error: All fields must be filled!");
            return;
        }


        String fileName = "Security_Report_" + System.currentTimeMillis() + ".pdf";


        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            document.add(new Paragraph("APBN AIRPORT SECURITY - INCIDENT REPORT"));
            document.add(new Paragraph("Generated on: " + LocalDateTime.now()));
            document.add(new Paragraph("-------------------------------------------------"));
            document.add(new Paragraph("Incident Type: " + type));
            document.add(new Paragraph("Date of Incident: " + date));
            document.add(new Paragraph("Location: " + location));
            document.add(new Paragraph("Description:"));
            document.add(new Paragraph(description));


            statusLabel.setText("Report submitted! PDF saved as: " + fileName);


            descriptionArea.clear();
            locationField.clear();

        } catch (Exception e) {
            statusLabel.setText("Error occurred while creating PDF.");
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}