/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konyvtarkezelo.ui.belepes;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import konyvtarkezelo.beallitasok.Preferences;
import konyvtarkezelo.ui.main.MainKezelo;


/**
 * FXML Controller class
 *
 * @author Csicsek Máté
 */
public class BelepesKezelo implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField jelszo;
    
    Preferences preference;
    
    @FXML
    private Label titleLabel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preference = Preferences.getPreferences();
    }    

    @FXML
    private void loginButtAct(ActionEvent event) {
        titleLabel.setText("Könyvtárkezelő belépés");
        titleLabel.setStyle("-fx-background-color:lightgrey");
        
        String uname = username.getText();
        String pword = jelszo.getText();
        
        if(uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())){
            ((Stage)username.getScene().getWindow()).close();
            loadMain();
        } else {
            titleLabel.setText("Hibás próbálkozás");
            titleLabel.setStyle("-fx-background-color:#d32f2f");
        }
    }

    @FXML
    private void cancelButtAct(ActionEvent event) {
        System.exit(0);
    }
    
    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/konyvtarkezelo/ui/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Könyvtárkezelő");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainKezelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
