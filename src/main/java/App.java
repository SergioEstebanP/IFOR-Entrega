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

    private Menu toolsMenu = new Menu("Tools");
    private Menu settingsMenu = new Menu("Settings");
    private Menu helpMenu = new Menu("Help");

    private MenuItem program1 = new MenuItem("Program 1");
    private MenuItem program2 = new MenuItem("Program 2");
    private MenuItem program3 = new MenuItem("Program 3");

    private MenuItem version = new MenuItem("Information");

    @Override
    public void start(Stage primaryStage) {

        // Configuration of the upper toolbar
        upperToolbarConfiguration();

        // Create the different panes to add to the scene 
        BorderPane mainPanel = new BorderPane();
        HBox topPane = new HBox(upperMenuBar);
        mainPanel.setTop(topPane);

        // Create the scene and add all the primary elements
        Scene scene = new Scene(mainPanel, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("IFOR-ENSICS");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void upperToolbarConfiguration () {
        // Create the menu bar and all the components
        upperMenuBar.getMenus().add(toolsMenu);
        upperMenuBar.getMenus().add(settingsMenu);
        upperMenuBar.getMenus().add(helpMenu);

        toolsMenu.getItems().add(program1);
        toolsMenu.getItems().add(program2);
        toolsMenu.getItems().add(program3);

        toolsMenu.getItems().add(version);
    }

}