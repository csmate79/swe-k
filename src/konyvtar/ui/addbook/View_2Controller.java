/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konyvtar.ui.addbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import konyvtar.database.AdatbazisKezelo;
import konyvtarkezelo.ui.konyvlista.KonyvListaKezelo;


/**
 * FXML Controller class
 *
 * @author csicsek
 */
public class View_2Controller implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField publisher;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    
    private Boolean isInEditMode = Boolean.FALSE;
    
    AdatbazisKezelo adatbaziskezelo;
    
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adatbaziskezelo = AdatbazisKezelo.getInstance();
        
        checkData();
    }
    
        public void inflateUI(KonyvListaKezelo.Book book) {
            title.setText(book.getTitle());
            id.setText(book.getId());
            author.setText(book.getAuthor());
            publisher.setText(book.getPublisher());
            id.setEditable(false);
            isInEditMode = Boolean.TRUE;
    }

    @FXML
    private void addBook(javafx.event.ActionEvent event){
        String bookID = id.getText();
        String bookAuthor = author.getText();
        String bookName = title.getText();
        String bookPublisher = publisher.getText();
        
        if(bookID.isEmpty() || bookAuthor.isEmpty() || bookName.isEmpty() || bookAuthor.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Kérlek töltsd ki a boxokat.");
            alert.showAndWait();
            return;
        }
/*        stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "  id varchar(200) primary key,\n"
                        + "  title varchar(200),\n"
                        + "  author varchar(200),\n"
                        + "  publisher varchar(200),\n"
                        + "  isAvail boolean default true"
                        + " )");
*/        
        String qu = "INSERT INTO BOOK VALUES (" +
                "'" + bookID + "',"+
                "'" + bookName + "',"+
                "'" + bookAuthor + "'," +
                "'" + bookPublisher + "'," +
                "" + "true" + "" +
                ")";
        System.out.println(qu);
        if (adatbaziskezelo.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sikeres");
            alert.showAndWait();
        } else  { //error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nem sikerült");
            alert.showAndWait();
        }
    }
    

    private void checkData() {
        String qu = "SELECT title FROM BOOK";
        ResultSet rs = adatbaziskezelo.execQuery(qu);
        try {
            while (rs.next()) {
                String titlex = rs.getString("title");
                System.out.println(titlex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancel(javafx.event.ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}

