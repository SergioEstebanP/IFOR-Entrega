import eventshandler.CommonHandler;
import eventshandler.Handler2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import main.java.Files;

public class Program2 extends Application {

    // variables needed in the program for controls
    private MenuBar upperMenuBar = new MenuBar();

    private Menu tools = new Menu("Tools");
    private Menu settingsMenu = new Menu("Settings");
    private Menu helpMenu = new Menu("Help");

    private MenuItem about = new MenuItem("Information");

    private MenuItem creation = new MenuItem("Creation Date");
    private MenuItem access = new MenuItem("Last Access Date");
    private MenuItem modification = new MenuItem("Modification Date");

    private DirectoryChooser dirChooser = new DirectoryChooser();

    private Label title = new Label("Searching files between a creation date range");

    private Stage stage;
    private Scene scene;

    private Label fromDate = new Label("From Date");
    private TextField fromTxt = new TextField();
    private Label toDate = new Label("To Date");
    private TextField toTxt = new TextField();

    private Label fromPath = new Label("From path");
    private TextField fromPathTxt = new TextField();
    private Button selectDir = new Button("Dir");
    private Button lookFor = new Button("Look for!");

    private TableView<Files> table = new TableView<Files>();

    private TableColumn<Files, String> colName = new TableColumn<Files, String>("Name");
    private TableColumn<Files, String> colPath = new TableColumn<Files, String>("Path");


    private Label copyPath = new Label("Copy in path");
    private TextField copyPathTxt = new TextField();
    private Button selectDir1 = new Button("Dir");
    private Button copy = new Button("Copy!");

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        // Configuration of the upper toolbar
        upperToolbarConfiguration(upperMenuBar);

        // Style configuration for the controls
        styleAndFormatConfiguration();

        colName.setCellValueFactory(new PropertyValueFactory<Files, String>("Name"));
        colPath.setCellValueFactory(new PropertyValueFactory<Files, String>("Path"));
        table.getColumns().addAll(colName, colPath);
        // Create the different panes to add to the program1 scene 
        BorderPane mainPanel = new BorderPane();
        HBox topPane = new HBox(upperMenuBar);
        mainPanel.setTop(topPane);
        GridPane centerPane = new GridPane();
        mainGridConfiguration(centerPane);
        mainPanel.setCenter(centerPane);
        scene = new Scene(mainPanel, 500, 380);
        stage.setScene(scene);

        // Create the scene and add all the primary elements
        stageConfiguration(stage);

        // Added main handler actions and events
        lookFor.setOnAction(e -> Handler2.lookForCreation(table, colName, colPath, fromTxt, toTxt, fromPathTxt));
        selectDir.setOnAction(e -> CommonHandler.selectDir(dirChooser, stage, fromPathTxt));
        selectDir1.setOnAction(e -> CommonHandler.selectDir(dirChooser, stage, copyPathTxt));
        copy.setOnAction(e -> CommonHandler.copyToGivenDir(copyPathTxt, table));

        // Added functionality to change between scences
        creation.setOnAction(e -> Handler2.changeToCreationScene(title, lookFor, table, colName, colPath, fromTxt, toTxt, fromPathTxt));
        modification.setOnAction(e -> Handler2.changeToModificationScene(title, lookFor, table, colName, colPath, fromTxt, toTxt, fromPathTxt));
        access.setOnAction(e -> Handler2.changeToAccessScene(title, lookFor, table, colName, colPath, fromTxt, toTxt, fromPathTxt));
    }

    private void mainGridConfiguration(GridPane grid) {
        // setting some style and appereance in the grid
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(title, 0, 0);
        grid.setColumnSpan(title, 4);
        grid.setHalignment(title, HPos.CENTER);

        grid.add(fromDate, 0, 1);
        grid.add(toDate, 1, 1);
        grid.add(fromTxt, 0, 2);
        grid.add(toTxt, 1, 2);

        grid.add(fromPath, 0, 3);
        grid.add(fromPathTxt, 1, 3);
        grid.add(selectDir, 2, 3);

        grid.add(lookFor, 0, 4);
        grid.setColumnSpan(lookFor, 4);
        grid.setHalignment(lookFor, HPos.CENTER);

        grid.add(table, 0, 5);
        grid.setColumnSpan(table, 4);

        grid.add(copyPath, 0, 6);
        grid.add(copyPathTxt, 1, 6);
        grid.add(selectDir1, 2, 6);
        grid.add(copy, 3, 6);
    }

	public static void main(String[] args) {
        launch();
    }

    private void stageConfiguration (Stage stage) {
        stage.setTitle("IFOR-ENSICS");
        stage.setResizable(true);
        stage.show();
    }

    private void upperToolbarConfiguration (MenuBar upperMenuBar) {
        // Create the menu bar and all the components
        upperMenuBar.getMenus().add(tools);
        upperMenuBar.getMenus().add(settingsMenu);
        upperMenuBar.getMenus().add(helpMenu);
        helpMenu.getItems().add(about);
        tools.getItems().add(creation);
        tools.getItems().add(access);
        tools.getItems().add(modification);
    }

    private void styleAndFormatConfiguration () {
        upperMenuBar.setPrefWidth(600);
        fromPathTxt.setEditable(false);
        copyPathTxt.setEditable(false);

    }


}