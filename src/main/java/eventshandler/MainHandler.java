package eventshandler;

import eventshandler.program1handlers.*;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.*;

public class MainHandler {
    public static void changeToDirectoriesScene(Label lbl1, Label lbl2, TextField txt1, TextField txt2, Button btn1, Button btn2, DirectoryChooser dc, Stage stage) {
        lbl1.setText("Directory 1");
        lbl2.setText("Directory 2");
        btn1.setText("Select Directory");
        btn2.setText("Select Directory");
        btn1.setOnAction(e -> Handler1.selectDirectory(dc, stage, txt1));
        btn2.setOnAction(e -> Handler1.selectDirectory(dc, stage, txt2));
    }

    public static void changeToFilesScene(Label lbl1, Label lbl2, TextField txt1, TextField txt2, Button btn1, Button btn2, FileChooser fc, Stage stage) {
        lbl1.setText("File 1");
        lbl2.setText("File 2");
        btn1.setText("Select File");
        btn2.setText("Select File");
        btn1.setOnAction(e -> Handler1.selectFile(fc, stage, txt1));
        btn2.setOnAction(e -> Handler1.selectFile(fc, stage, txt2));
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
}