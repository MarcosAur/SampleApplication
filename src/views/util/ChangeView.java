package views.util;

import application.Program;
import java.io.IOException;
import java.util.function.Consumer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author marcos
 */
public class ChangeView {
    
    public synchronized void loadView(String path){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            VBox newVbox = loader.load();
            
            Scene oldScene = Program.getScene();
            VBox oldVbox = (VBox) ((ScrollPane) oldScene.getRoot()).getContent();
            
            Node node = oldVbox.getChildren().get(0);
            
            oldVbox.getChildren().clear();
            oldVbox.getChildren().add(node);
            oldVbox.getChildren().addAll(newVbox.getChildren());
            
        } catch (IOException ex) {
            Alerts.showAlert("Error", "Corrupted Archive", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
}
