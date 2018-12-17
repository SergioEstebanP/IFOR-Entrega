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
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.*;

public class Handler2 {

    public static void lookForCreation (TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, DatePicker fromDate, DatePicker toDate, TextField fromPath) {
        StringBuffer sb = new StringBuffer();
        LocalDate fromDateValue = fromDate.getValue();
        LocalDate toDateValue = toDate.getValue();
        String fromDateString = Integer.toString(fromDateValue.getYear()) + "-" + Integer.toString(fromDateValue.getMonthValue())+ "-" + Integer.toString(fromDateValue.getDayOfMonth());
        String toDateString = Integer.toString(toDateValue.getYear()) + "-" + Integer.toString(toDateValue.getMonthValue())+ "-" + Integer.toString(toDateValue.getDayOfMonth());
        String stringCommand = "find " + fromPath.getText() + " -newerct \"" + fromDateString + "\" ! -newerct \"" + toDateString + "\"";
        ArrayList<String> fileList = new ArrayList<String>();
        CommonHandler.executeCommand(stringCommand, fileList, table);
    }

    public static void lookForModification (TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, DatePicker fromDate, DatePicker toDate, TextField fromPath) {
        StringBuffer sb = new StringBuffer();
        LocalDate fromDateValue = fromDate.getValue();
        LocalDate toDateValue = toDate.getValue();
        String fromDateString = Integer.toString(fromDateValue.getYear()) + "-" + Integer.toString(fromDateValue.getMonthValue())+ "-" + Integer.toString(fromDateValue.getDayOfMonth());
        String toDateString = Integer.toString(toDateValue.getYear()) + "-" + Integer.toString(toDateValue.getMonthValue())+ "-" + Integer.toString(toDateValue.getDayOfMonth());
        String stringCommand = "find " + fromPath.getText() + " -newermt \"" + fromDateString + "\" ! -newermt \"" + toDateString + "\"";
        ArrayList<String> fileList = new ArrayList<String>();
        CommonHandler.executeCommand(stringCommand, fileList, table);
    }

    public static void lookForAccess (TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, DatePicker fromDate, DatePicker toDate, TextField fromPath) {
        StringBuffer sb = new StringBuffer();
        LocalDate fromDateValue = fromDate.getValue();
        LocalDate toDateValue = toDate.getValue();
        String fromDateString = Integer.toString(fromDateValue.getYear()) + "-" + Integer.toString(fromDateValue.getMonthValue())+ "-" + Integer.toString(fromDateValue.getDayOfMonth());
        String toDateString = Integer.toString(toDateValue.getYear()) + "-" + Integer.toString(toDateValue.getMonthValue())+ "-" + Integer.toString(toDateValue.getDayOfMonth());
        String stringCommand = "find " + fromPath.getText() + " -newerat \"" + fromDateString + "\" ! -newerat \"" + toDateString + "\"";
        ArrayList<String> fileList = new ArrayList<String>();
        CommonHandler.executeCommand(stringCommand, fileList, table);
    }

    public static void changeToCreationScene(Label title, Button button, TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, DatePicker fromDate, DatePicker toDate, TextField fromPath) {
        title.setText("Search created files between a date range"); 
        button.setOnAction(e -> lookForCreation(table, col1, col2, fromDate, toDate, fromPath));
    }

    public static void changeToModificationScene(Label title, Button button, TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, DatePicker fromDate, DatePicker toDate, TextField fromPath) {
        title.setText("Search modificated files between a date range"); 
        button.setOnAction(e -> lookForModification(table, col1, col2, fromDate, toDate, fromPath));
    }

    public static void changeToAccessScene(Label title, Button button, TableView<Files> table, TableColumn<Files, String> col1, TableColumn<Files, String> col2, DatePicker fromDate, DatePicker toDate, TextField fromPath) {
        title.setText("Search last access files between a date range"); 
        button.setOnAction(e -> lookForAccess(table, col1, col2, fromDate, toDate, fromPath));
    }
}