package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;

public class SupervisorGoal2Controller {

    @FXML private TableView<LostItem> lostTable;
    @FXML private TableColumn<LostItem, String> nameCol;
    @FXML private TableColumn<LostItem, String> catCol;
    @FXML private TableColumn<LostItem, String> locCol;

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("locationFound"));
        loadItems();
    }

    private void loadItems() {
        ObservableList<LostItem> list = FXCollections.observableArrayList();
        File f = new File("LostAndFound.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    list.add((LostItem) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) {
            System.err.println("Read error.");
        }
        lostTable.setItems(list);
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}