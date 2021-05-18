/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import application.Program;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class DepartmentListController implements Initializable {

    @FXML
    private TableView<Department> tableViewDepartment;
    
    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department,String> tableColumnName;
    
    @FXML
    private Button btnNew;
    
    @FXML 
    public void onBtnNewAction(){
        System.out.println("Clicked");
    }
    
    public void initializeNodes(){
        tableColumnId.setCellFactory(new PropertyValueFactory("Id"));
        tableColumnName.setCellFactory(new PropertyValueFactory("Name"));
        Stage stage = (Stage) Program.getScene().getWindow();
        
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
        tableViewDepartment.prefWidthProperty().bind(stage.widthProperty());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }    
    
}
