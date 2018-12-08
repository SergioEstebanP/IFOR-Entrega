package eventshandler.program1handlers;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.File;

public class Handler1 {
    public static void selectDirectory (FileChooser fc, Stage stage, TextField txt) {
    }

    public static void selectFile (FileChooser fc, Stage stage, TextField txt) {
        File file = fc.showOpenDialog(stage);
        String name = file.getName();
        String path = file.getPath();
        txt.setText(path);
    }
}