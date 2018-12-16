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

public class Handler3 {
    public static void lookForExtension (TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, TextField extension, TextField fromPath) {
        StringBuffer sb = new StringBuffer();
        String stringCommand = "find " + fromPath.getText() + " " + "-type f -name '*." + extension.getText() + "'";
        ArrayList<String> fileList = new ArrayList<String>();
        CommonHandler.executeCommand(stringCommand, fileList, table);
    }
}