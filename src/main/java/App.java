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

public class App extends Application {

    // variables needed in the program for controls
    private MenuBar upperMenuBar = new MenuBar();
    private MenuBar upperMenuBar2 = new MenuBar();

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

    // variables needed for the Program 2 part A: copy by creation date 
    private Label fromDate = new Label("From Date");
    private TextField fromDateTxt = new TextField("");
    private Label toDate = new Label("To Date");
    private TextField toDateTxt = new TextField("");
    private Label fromPath = new Label("From Path");
    private TextField fromPathTxt = new TextField("");
    private Button selectDirectory = new Button("Select Dir");
    private Button startSearch = new Button("Search!");
    private TextArea output2 = new TextArea();
    private Label copyLabel = new Label("Copy files in:");
    private TextField copyTxt = new TextField("");
    private Button selectDirCopy = new Button("Dir");
    private Button copy = new Button("Copy");
    private Scene scene2;
    private Stage stage2;

    // variables needed for the Program 2 part B: copy by modification date

    // variables needed for the Program 2 part C: copy by last access date



    // variables needed for the Program 3 part A: find by extension

    // variables needed for the Program 3 part B: find by magic numbers

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


        upperToolbarConfiguration(upperMenuBar2);
        BorderPane mainPanel2 = new BorderPane();
        HBox topPane2 = new HBox(upperMenuBar2);
        mainPanel2.setTop(topPane2);
        GridPane centerPane2 = new GridPane();
        mainPanel2.setCenter(centerPane2);
        scene2 = new Scene(mainPanel2, 500, 380);

        // Create the scene and add all the primary elements
        stageConfiguration(stage);

        // change the scenes
        p1Dire.setOnAction(e -> MainHandler.changeToDirectoriesScene(file1Lbl, file2Lbl, file1Path, file2Path, file1UploadBtn, file2UploadBtn, dirChooser, primaryStage, title));
        p1Dire.setOnAction(e -> stage.setScene(scene1));
        p1File.setOnAction(e -> MainHandler.changeToFilesScene(file1Lbl, file2Lbl, file1Path, file2Path, file1UploadBtn, file2UploadBtn, fileChooser, primaryStage, title));
        p2CreateDate.setOnAction(e -> stage.setScene(scene2));
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

    private void styleAndFormatConfiguration () {
        upperMenuBar.setPrefWidth(600);
        file1Path.setPrefWidth(250);
        file2Path.setPrefWidth(250);
    }

}