
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konyvtar.ui.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import konyvtar.database.AdatbazisKezelo;

/**
 * FXML Controller class
 *
 * @author Csicsek Máté
 */
public class MemberAddKezelo implements Initializable {

    AdatbazisKezelo kezelo;
    
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kezelo = AdatbazisKezelo.getInstance();
    }    


    @FXML
    private void cancel(javafx.event.ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMember(ActionEvent event) {
        String nName = name.getText();
        String nID = id.getText();
        String nMobile = mobile.getText();
        String nEmail = email.getText();
        
        Boolean flag = nName.isEmpty() || nID.isEmpty() || nMobile.isEmpty() || nEmail.isEmpty();
        
        if(flag){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("PLease Enter in all fields.");
            alert.showAndWait();
            return;
        }
        
        String st = "INSERT INTO MEMBER VALUES (" +
                "'" + nName + "'," +
                "'" + nID + "'," +
                "'" + nMobile + "'," +
                "'" + nEmail + "'" +
                ")";
        System.out.println(st);
        if (kezelo.execAction(st)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Saved.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR");
            alert.showAndWait();
        }
    }
}
