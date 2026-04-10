package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;

public class DutyScheduleController
{
    @javafx.fxml.FXML
    private TableColumn<Schedule, String> locationCol;
    @javafx.fxml.FXML
    private TableColumn<Schedule, String> shiftCol;
    @javafx.fxml.FXML
    private TableColumn<Schedule, String> dateCol;
    @javafx.fxml.FXML
    private TextField officerIDField;
    @javafx.fxml.FXML
    private TableView<Schedule> scheduleTable;
    @javafx.fxml.FXML
    private TableColumn<Schedule, String> officerIDCol;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        shiftCol.setCellValueFactory(new PropertyValueFactory<>("shiftTime"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        officerIDCol.setCellValueFactory(new PropertyValueFactory<>("officerID"));

        File file = new File("schedules.bin");
        if (!file.exists()) {
            createDummyFile();
        }
    }

    private void createDummyFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("schedules.bin"))) {
            oos.writeObject(new Schedule("2026-04-27", "6AM-2PM", "Main Entry", "OF-001"));
            oos.writeObject(new Schedule("2026-04-27", "2PM-10PM", "Cargo Gate", "OF-002"));
            oos.writeObject(new Schedule("2026-04-28", "6AM-2PM", "VIP Lounge", "OF-001"));
            oos.writeObject(new Schedule("2026-04-29", "10PM-6AM", "Baggage Checkpoint", "OF-004"));
            oos.writeObject(new Schedule("2026-04-28", "2PM-10PM", "Cafeteria Entry", "OF-003"));
            oos.writeObject(new Schedule("2026-04-30", "6AM-2PM", "Main Entry", "OF-005"));

            System.out.println("Schedule file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void checkScheduleButton(ActionEvent actionEvent) {
        String searchID = officerIDField.getText();
        scheduleTable.getItems().clear();
        boolean found = false;

        if (searchID.isEmpty()) {
            statusLabel.setText("Please enter your Officer ID");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("schedules.bin"))) {
            while (true) {
                Schedule s = (Schedule) ois.readObject();
                if (s.getOfficerID().equalsIgnoreCase(searchID)) {
                    scheduleTable.getItems().add(s);
                    found = true;
                }
            }
        } catch (EOFException e) {
            // End of file reached
            if (!found) statusLabel.setText("No schedule found for this ID.");
            else statusLabel.setText("Schedule loaded!");
        } catch (Exception e) {
            statusLabel.setText("Error reading schedule database.");
        }
    }
}