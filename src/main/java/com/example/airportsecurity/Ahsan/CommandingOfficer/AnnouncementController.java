package com.example.airportsecurity.Ahsan.CommandingOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;


public class AnnouncementController
{
    @FXML private TextArea messageArea;
    @FXML private Label statusLabel;

    @FXML private TableView<Announcement> announcementTable;
    @FXML private TableColumn<Announcement, String> messageColumn;

    private final ObservableList<Announcement> messages =
            FXCollections.observableArrayList();

    private final String FILE_NAME = "announcements.bin";

    @FXML
    public void initialize() {

        messageColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getMessage()
                )
        );

        announcementTable.setItems(messages);

        loadFromFile();
    }

    @FXML
    private void handleSend() {

        String msg = messageArea.getText().trim();

        if (msg.isEmpty()) {
            statusLabel.setText("Write a message first");
            return;
        }

        messages.add(new Announcement(msg));

        saveToFile();

        messageArea.clear();

        statusLabel.setText("Message sent successfully");
    }

    private void saveToFile() {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(new ArrayList<>(messages));

        } catch (Exception e) {
            statusLabel.setText("Error saving file");
        }
    }

    private void loadFromFile() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            ArrayList<Announcement> list =
                    (ArrayList<Announcement>) ois.readObject();

            messages.clear();
            messages.addAll(list);

        } catch (Exception e) {
            // first run → no file exists
        }
    }
}