import java.awt.TextArea;
import java.awt.TextField;

import eventshandler.MainHandler;
import eventshandler.program1handlers.*;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Program1 extends Application {

    // variables needed in the program for controls
    private MenuBar upperMenuBar = new MenuBar();

    private Menu tools = new Menu("Tools");
    private Menu settingsMenu = new Menu("Settings");
    private Menu helpMenu = new Menu("Help");

    private MenuItem about = new MenuItem("Information");

    private MenuItem file = new MenuItem("Compare Files");
    private MenuItem dir = new MenuItem("Compare Dirs");

    private Button searchDiferences = new Button("Compare files");
    private FileChooser fileChooser = new FileChooser();
    private DirectoryChooser dirChooser = new DirectoryChooser();

    private Label title = new Label("Compare files");

    private Stage stage;

    // variables needed for the Program 1 the text in the buttons, labels and
    // other elements change depending on the program selected
    private Label file1Lbl = new Label("File 1");
    private Label file2Lbl = new Label("File 2");
    private Button file1UploadBtn = new Button("Select File");
    private Button file2UploadBtn = new Button("Select File");
    private TextField file1Path = new TextField("");
    private TextField file2Path = new TextField("");
    private TextArea diferences = new TextArea();
    private Scene scene1;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        // Configuration of the upper toolbar
        upperToolbarConfiguration(upperMenuBar);

        // Style configuration for the controls
        styleAndFormatConfiguration();

        // Create the different panes to add to the program1 scene 
        BorderPane mainPanel = new BorderPane();
        HBox topPane = new HBox(upperMenuBar);
        mainPanel.setTop(topPane);
        GridPane centerPane1 = new GridPane();
        mainGridConfiguration(centerPane1);
        mainPanel.setCenter(centerPane1);
        scene1 = new Scene(mainPanel, 500, 380);
        stage.setScene(scene1);

        file1UploadBtn.setOnAction(e -> Handler1.selectFile(fileChooser, primaryStage, file1Path));
        file2UploadBtn.setOnAction(e -> Handler1.selectFile(fileChooser, primaryStage, file2Path));


        // Create the scene and add all the primary elements
        stageConfiguration(stage);

        // change the scenes
        dir.setOnAction(e -> MainHandler.changeToDirectoriesScene(file1Lbl, file2Lbl, file1Path, file2Path, file1UploadBtn, file2UploadBtn, dirChooser, stage, title));
        file.setOnAction(e -> MainHandler.changeToFilesScene(file1Lbl, file2Lbl, file1Path, file2Path, file1UploadBtn, file2UploadBtn, fileChooser, stage, title));
        searchDiferences.setOnAction(e -> MainHandler.lookForDiferences(file1Path, file2Path, diferences));
    }

    private void mainGridConfiguration(GridPane grid) {
        // setting some style and appereance in the grid
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        
        // adding some buttons
        grid.add(title, 0, 0);
        grid.setColumnSpan(title, 3);
        grid.setHalignment(title, HPos.CENTER);
        grid.add(file1Lbl, 0, 1);
        grid.add(file1Path, 1, 1);
        grid.add(file1UploadBtn, 2, 1);
        grid.setHalignment(file1UploadBtn, HPos.CENTER);
        grid.add(file2Lbl, 0, 2);
        grid.add(file2Path, 1, 2);
        grid.add(file2UploadBtn, 2, 2);
        grid.setHalignment(file2UploadBtn, HPos.CENTER);
        grid.add(searchDiferences, 0, 3);
        grid.setColumnSpan(searchDiferences, 3);
        grid.setHalignment(searchDiferences, HPos.CENTER);
        grid.add(diferences, 0, 4);
        grid.setColumnSpan(diferences, 3);
        grid.setHalignment(diferences, HPos.CENTER);
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
        tools.getItems().add(dir);
        tools.getItems().add(file);
    }

    private void styleAndFormatConfiguration () {
        upperMenuBar.setPrefWidth(600);

        file1Path.setPrefWidth(250);
        file2Path.setPrefWidth(250);
        file1Path.setEditable(false);
        file2Path.setEditable(false);

        diferences.setEditable(false);
    }

}