package info.simpll.fxprop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Application
 * @author Bhathiya
 */
public class MainApp extends Application {

    /**
     * Start Application
     * @param stage main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Java FX Property Class Builder");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
