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

public class MainHandler {
    public static void changeToDirectoriesScene(Label lbl1, Label lbl2, TextField txt1, TextField txt2, Button btn1, Button btn2, DirectoryChooser dc, Stage stage, Label title) {
        lbl1.setText("Directory 1");
        lbl2.setText("Directory 2");
        btn1.setText("Select Directory");
        btn2.setText("Select Directory");
        btn1.setOnAction(e -> Handler1.selectDirectory(dc, stage, txt1));
        btn2.setOnAction(e -> Handler1.selectDirectory(dc, stage, txt2));
        title.setText("Searching differences between directories");
    }

    public static void changeToFilesScene(Label lbl1, Label lbl2, TextField txt1, TextField txt2, Button btn1, Button btn2, FileChooser fc, Stage stage, Label title) {
        lbl1.setText("File 1");
        lbl2.setText("File 2");
        btn1.setText("Select File");
        btn2.setText("Select File");
        btn1.setOnAction(e -> Handler1.selectFile(fc, stage, txt1));
        btn2.setOnAction(e -> Handler1.selectFile(fc, stage, txt2));
        title.setText("Searching differences between files");
    }

    public static void lookForDiferences (TextField txt1, TextField txt2, TextArea output) {
        String path1 = txt1.getText();
        String path2 = txt2.getText();

        StringBuffer sb = new StringBuffer();

        try {
            Process command = Runtime.getRuntime().exec("diff " + path1 + " " + path2);
            command.waitFor();
        
            BufferedReader reader = new BufferedReader(new InputStreamReader(command.getInputStream()));
        
            String commandOutput = "";			
            while ((commandOutput = reader.readLine())!= null) {
                sb.append(commandOutput + "\n");
            }
            if (sb.toString().equals("")) {
                output.setText("Files are NOT different");
            } else {
                output.setText("Files ARE different: \n\n" + sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectDir (DirectoryChooser dc, Stage stage, TextField dirTxt) {
        Handler1.selectDirectory(dc, stage, dirTxt);
    }

    public static void lookForCreation (TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, TextField fromDate, TextField toDate, TextField fromPath) {
        StringBuffer sb = new StringBuffer();
        String stringCommand = "find " + fromPath.getText() + " -newerct \"" + fromDate.getText() + "\" ! -newerct \"" + toDate.getText() + "\"";
        ArrayList<String> fileList = new ArrayList<String>();
        try {
            Process command = Runtime.getRuntime().exec(new String[]{"bash", "-c", stringCommand});
        
            command.waitFor();
        
            BufferedReader reader = new BufferedReader(new InputStreamReader(command.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            String s;
            String commandOutput = "";			
            while ((commandOutput = reader.readLine())!= null) {
                fileList.add(commandOutput);
            }
            //output.setText(sb.toString());

            table.setItems(loadData(fileList));
            table.getColumns().addAll(col1, col2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void lookForModification (TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, TextField fromDate, TextField toDate, TextField fromPath) {
        StringBuffer sb = new StringBuffer();
        String stringCommand = "find " + fromPath.getText() + " -newermt \"" + fromDate.getText() + "\" ! -newermt \"" + toDate.getText() + "\"";
        System.out.println(stringCommand);
        ArrayList<String> fileList = new ArrayList<String>();
        try {
            Process command = Runtime.getRuntime().exec(new String[]{"bash", "-c", stringCommand});
        
            command.waitFor();
        
            BufferedReader reader = new BufferedReader(new InputStreamReader(command.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            String s;
            String commandOutput = "";			
            while ((commandOutput = reader.readLine())!= null) {
                fileList.add(commandOutput);
            }
            //output.setText(sb.toString());

            table.setItems(loadData(fileList));
            table.getColumns().addAll(col1, col2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void lookForAccess (TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, TextField fromDate, TextField toDate, TextField fromPath) {
        StringBuffer sb = new StringBuffer();
        String stringCommand = "find " + fromPath.getText() + " -newerat \"" + fromDate.getText() + "\" ! -newerat \"" + toDate.getText() + "\"";
        System.out.println(stringCommand);
        ArrayList<String> fileList = new ArrayList<String>();
        try {
            Process command = Runtime.getRuntime().exec(new String[]{"bash", "-c", stringCommand});
        
            command.waitFor();
        
            BufferedReader reader = new BufferedReader(new InputStreamReader(command.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            String s;
            String commandOutput = "";			
            while ((commandOutput = reader.readLine())!= null) {
                fileList.add(commandOutput);
            }

            table.setItems(loadData(fileList));
            table.getColumns().addAll(col1, col2);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static void changeToCreationScene(Label title, Button button, TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, TextField fromDate, TextField toDate, TextField fromPath) {
        title.setText("Search created files between a date range"); 
        button.setOnAction(e -> lookForCreation(table, col1, col2, fromDate, toDate, fromPath));
    }

    public static void changeToModificationScene(Label title, Button button, TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, TextField fromDate, TextField toDate, TextField fromPath) {
        title.setText("Search modificated files between a date range"); 
        button.setOnAction(e -> lookForModification(table, col1, col2, fromDate, toDate, fromPath));
    }

    public static void changeToAccessScene(Label title, Button button, TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, TextField fromDate, TextField toDate, TextField fromPath) {
        title.setText("Search last access files between a date range"); 
        button.setOnAction(e -> lookForAccess(table, col1, col2, fromDate, toDate, fromPath));
    }
}