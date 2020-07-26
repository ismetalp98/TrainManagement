/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML FinishController class that shows if the payment is successful or not.
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class FınıshController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    /*
     If the user clicks Home Page button, they will be directed to the home page.
     */
    @FXML
    private void handleHomePage(ActionEvent evt)
    {
        try
        {
            Node node = (Node) evt.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/AppPage_1.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(FınıshController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     If the user clicks Exit button, the program terminates.
     */
    @FXML
    private void handleExit(ActionEvent event)
    {
        System.exit(0);
    }

}
