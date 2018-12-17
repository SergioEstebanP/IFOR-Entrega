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

public class Handler1 {
    
    public static void selectFile (FileChooser fc, Stage stage, TextField txt) {
        File file = fc.showOpenDialog(stage);
        String name = file.getName();
        String path = file.getPath();
        txt.setText(name);
    }

    public static void changeToDirectoriesScene(Label lbl1, Label lbl2, TextField txt1, TextField txt2, Button btn1, Button btn2, DirectoryChooser dc, Stage stage, Label title) {
        lbl1.setText("Directory 1");
        lbl2.setText("Directory 2");
        btn1.setText("Select Directory");
        btn2.setText("Select Directory");
        btn1.setOnAction(e -> CommonHandler.selectDir(dc, stage, txt1));
        btn2.setOnAction(e -> CommonHandler.selectDir(dc, stage, txt2));
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
        String stringCommand = "diff " + path1 + " " + path2;
        try {
            Process command = Runtime.getRuntime().exec("diff " + path1 + " " + path2);
            command.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(command.getInputStream()));
            String commandOutput = "";			
            while ((commandOutput = reader.readLine())!= null) {
                sb.append(commandOutput + "\n");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("IFOR-ENSICS");

            if (sb.toString().equals("")) {
                alert.setHeaderText("Process Complete");
                alert.setContentText("Differences not found.\nFiles are EQUAL.");
                output.setText("Files are NOT different");
            } else {
                alert.setHeaderText("Process Complete");
                alert.setContentText("Differences found.\nFiles are DIFERENT.");
                output.setText("Files ARE different: \n\n" + sb.toString());
            }
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}