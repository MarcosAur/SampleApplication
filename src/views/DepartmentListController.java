/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import application.Program;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class DepartmentListController implements Initializable {

    private DepartmentService service;

    @FXML
    private TableView<Department> tableViewDepartment;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    private ObservableList<Department> observableList;

    @FXML
    private Button btnNew;

    public void setService(DepartmentService service) {
        this.service = service;
    }

    @FXML
    public void onBtnNewAction() {
        System.out.println("Clicked");
    }

    public void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        
        Stage stage = (Stage) Program.getScene().getWindow();

        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
        tableViewDepartment.prefWidthProperty().bind(stage.widthProperty());
        
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        
        List<Department> list = service.findAll();
        observableList = FXCollections.observableArrayList(list);
        tableViewDepartment.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setService(new DepartmentService());
        updateTableView();
        initializeNodes();
    }

}
