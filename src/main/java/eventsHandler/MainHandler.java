package eventshandler;

import eventshandler.program1handlers.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainHandler {
    public static void changeToDirectoriesScene(Label lbl1, Label lbl2, TextField txt1, TextField txt2, Button btn1, Button btn2, FileChooser fc, Stage stage) {
        lbl1.setText("Directory 1");
        lbl2.setText("Directory 2");
        btn1.setText("Select Directory");
        btn2.setText("Select Directory");
        btn1.setOnAction(e -> Handler1.selectDirectory(fc, stage, txt1));
        btn2.setOnAction(e -> Handler1.selectDirectory(fc, stage, txt2));
    }

    public static void changeToFilesScene(Label lbl1, Label lbl2, TextField txt1, TextField txt2, Button btn1, Button btn2, FileChooser fc, Stage stage) {
        lbl1.setText("File 1");
        lbl2.setText("File 2");
        btn1.setText("Select File");
        btn2.setText("Select File");
        btn1.setOnAction(e -> Handler1.selectFile(fc, stage, txt1));
        btn2.setOnAction(e -> Handler1.selectFile(fc, stage, txt2));
    }
}