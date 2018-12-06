import java.awt.TextArea;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    // variables needed in the program for controls
    private MenuBar upperMenuBar = new MenuBar();

    private Menu program1 = new Menu("Program 1");
    private Menu program2 = new Menu("Program 2");
    private Menu program3 = new Menu("Program 3");
    private Menu settingsMenu = new Menu("Settings");
    private Menu helpMenu = new Menu("Help");

    private MenuItem p1File = new MenuItem("File Diferrences");
    private MenuItem p1Dire = new MenuItem("Directories Diferrences");

    private MenuItem p2CreateDate = new MenuItem("Copy by Creation Date");
    private MenuItem p2ModifiDate = new MenuItem("Copy by Modification Date");
    private MenuItem p2LastAcDate = new MenuItem("Copy by Last Access Date");

    private MenuItem p3Extension = new MenuItem("Find by Extension");
    private MenuItem p3MagicNumb = new MenuItem("Find by Magic Numbers");

    private MenuItem about = new MenuItem("Information");

    // variables needed for the Program 1 the text in the buttons, labels and
    // other elements change depending on the program selected
    private Label file1Lbl = new Label("File 1");
    private Label file2lbl = new Label("File 2");

    private Button file1UploadBtn = new Button("Select File");
    private Button file2UploadBtn = new Button("Select File");

    private TextField file1Path = new TextField("");
    private TextField file2Path = new TextField("");

    private TextArea diferences = new TextArea();

    // variables needed for the Program 2 part A: copy by creation date 

    // variables needed for the Program 2 part B: copy by modification date

    // variables needed for the Program 2 part C: copy by last access date



    // variables needed for the Program 3 part A: find by extension

    // variables needed for the Program 3 part B: find by magic numbers

    @Override
    public void start(Stage primaryStage) {

        // Configuration of the upper toolbar
        upperToolbarConfiguration();

        // Create the different panes to add to the scene 
        BorderPane mainPanel = new BorderPane();
        HBox topPane = new HBox(upperMenuBar);
        mainPanel.setTop(topPane);

        HBox file1Elements = new HBox(file1Lbl, file1Path, file1UploadBtn);
        HBox file2Elements = new HBox(file2Lbl, file2Path, file2UploadBtn);
        HBox textElements = new HBox(diferences);

        VBox centerPane = new VBox(file1Elements, file2Elements, textElements);

        mainPanel.setCenter(centerPane);
        // Create the scene and add all the primary elements
        stageConfiguration(primaryStage, mainPanel);
    }

    public static void main(String[] args) {
        launch();
    }

    private void stageConfiguration (Stage primaryStage, BorderPane mainPanel) {
        Scene scene = new Scene(mainPanel, 420, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("IFOR-ENSICS");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private void upperToolbarConfiguration () {
        // Create the menu bar and all the components
        upperMenuBar.getMenus().add(program1);
        upperMenuBar.getMenus().add(program2);
        upperMenuBar.getMenus().add(program3);
        upperMenuBar.getMenus().add(settingsMenu);
        upperMenuBar.getMenus().add(helpMenu);

        program1.getItems().add(p1Dire);
        program1.getItems().add(p1File);

        program2.getItems().add(p2CreateDate);
        program2.getItems().add(p2ModifiDate);
        program2.getItems().add(p2LastAcDate);

        program3.getItems().add(p3Extension);
        program3.getItems().add(p3MagicNumb);

        helpMenu.getItems().add(about);
    }

}