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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.services.DepartmentService;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class MainViewController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuRegistration;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem itemAbout;

    @FXML
    private MenuItem itemSeller;

    @FXML
    private MenuItem itemDepartment;

    @FXML
    public void onItemAboutAction() {
        Program.changeView.loadView("/views/AboutView.fxml", x -> {});
    }

    public void onItemDepartmentAction() {
        Program.changeView.loadView("/views/DepartmentList.fxml", ((DepartmentListController controll) -> {
            controll.setService(new DepartmentService());
            controll.updateTableView();
            
        }));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
