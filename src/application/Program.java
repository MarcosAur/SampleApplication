package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import views.util.ChangeView;

/**
 *
 * @author marcos
 */
public class Program extends Application{
    
    private static Scene scene;
    public static ChangeView changeView = new ChangeView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        ScrollPane parent =loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sample Application");
        stage.show();
    }

    public static Scene getScene() {
        return scene;
    }
    
}
