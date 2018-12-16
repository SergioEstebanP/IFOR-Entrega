package eventshandler;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import main.java.Files;

import java.io.*;
import java.util.ArrayList;
import javafx.collections.*;

public class CommonHandler {
    
    public static void selectDir (DirectoryChooser dc, Stage stage, TextField txt) {
        File file = dc.showDialog(stage);
        String name = file.getName();
        String path = file.getPath();
        txt.setText(path);
    }

    public static ObservableList<Files> loadData(ArrayList<String> fileList) {
        ObservableList<Files> data = FXCollections.observableArrayList();
        for (int i=0; i<fileList.size(); i++) {
            data.add(new Files(fileList.get(i)));
        }
        return data;
    }

    public static void copyToGivenDir(TextField path, TableView<Files> table) {
        ObservableList<Files> sel, items;
        items = table.getItems();
        sel = table.getSelectionModel().getSelectedItems();
        for (Files m : sel) {
            items.remove(m);
            StringBuffer sb = new StringBuffer();
            String stringCommand = "cp -ra " + m.getPath() + " " + path.getText();
            ArrayList<String> fileList = new ArrayList<String>();
            try {
                Process command = Runtime.getRuntime().exec(new String[]{"bash", "-c", stringCommand});
                command.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("IFOR-ENSICS");
            alert.setHeaderText("Copy Done");
            alert.setContentText("Copy done successfully!");
            alert.show();
        }
    }

    public static void executeCommand (String stringCommand, ArrayList<String> fileList, TableView<Files> table) {
        try {
            Process command = Runtime.getRuntime().exec(new String[]{"bash", "-c", stringCommand});
            command.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(command.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            String commandOutput = "";			
            while ((commandOutput = reader.readLine())!= null) {
                fileList.add(commandOutput);
            }
            table.setItems(loadData(fileList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showInformation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("IFOR-ENSICS");
        alert.setHeaderText("Forensic Software for Students");
        alert.setContentText("Author: Sergio Esteban Pellejero\nVersion: 1.0");
        alert.show();
    }
}