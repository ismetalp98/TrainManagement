/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import traninmanagament.Operations;

/**
 * FXML Controller class
 *
 * @author Kerem Alemdar
 * @date 24.12.2019
 */
public class SignUpController implements Initializable
{

    Operations operation = new Operations();
    static FXMLDocumentController cont = new FXMLDocumentController();

    @FXML
    private Parent fxml;

    @FXML
    private JFXTextField tf_name, tf_email, tf_lastName;

    @FXML
    private JFXPasswordField tf_pss;

    @FXML
    private VBox vbox;

    @FXML
    private Label lbl_error;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    /**
     * handlesingup method takes the information from 
     * user if the user wnts to apply for a membership 
     * for this application.
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void handlesingup(ActionEvent event)
    {
        String name = tf_name.getText().trim();
        String email = tf_email.getText().trim();
        String pss = tf_pss.getText().trim();
        String lastName = tf_lastName.getText().trim();

        if(tf_name.getText().equals("") || tf_email.getText().equals("") || tf_pss.getText().equals("") || !email.contains("@"))
        {
            tf_email.getStyleClass().add("wrong-credentials");
            tf_name.getStyleClass().add("wrong-credentials");
            tf_pss.getStyleClass().add("wrong-credentials");
            tf_lastName.getStyleClass().add("wrong-credentials");
            lbl_error.setText("* Invalid Name, E-Mail or Password");
        }
        else
        {
            try
            {
                boolean signup = operation.addMember(name, lastName, email, pss);
                if(signup)
                {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/FXMLDocument.fxml")));
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                }

            }
            catch(IOException ex)
            {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
