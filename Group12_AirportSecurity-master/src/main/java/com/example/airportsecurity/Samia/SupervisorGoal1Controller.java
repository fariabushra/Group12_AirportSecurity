package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;

public class SupervisorGoal1Controller {

    @FXML private TableView<Incident> incidentTable;
    @FXML private TableColumn<Incident, String> titleColumn;
    @FXML private TableColumn<Incident, String> descColumn;
    @FXML private TableColumn<Incident, String> dateColumn;

    private ObservableList<Incident> incidentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        loadIncidents();
    }

    private void loadIncidents() {
        incidentList.clear();
        File f = new File("IncidentReports.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    incidentList.add((Incident) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        incidentTable.setItems(incidentList);
    }

    @FXML
    void approveSelectedOnAction(ActionEvent event) {
        Incident selected = incidentTable.getSelectionModel().getSelectedItem();

        if (selected != null) {

            incidentList.remove(selected);


            rewriteBinFile();

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Incident approved and removed from pending list.");
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please select an incident from the table first!");
            a.showAndWait();
        }
    }

    private void rewriteBinFile() {
        File f = new File("IncidentReports.bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            for (Incident i : incidentList) {
                oos.writeObject(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}