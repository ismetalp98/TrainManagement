/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kerem Alemdar 
 * @date 24.12.2019
 */
public class FAQController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;

     /**
     * Initializes the controller class. 
     *
     * @param url the URL to display. 
     * @param rb the ResourceBundle to display.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

     /**
     * onlineTicket function shows the answer for 
     * the question about online tickets
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void onlineTicket(ActionEvent event) {
        pageLoad("/fxmls/onlineTicket");
    }

    /**
     * ticketChange function shows the answer for 
     * the question about online tickets
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void ticketChange(ActionEvent event) {
        pageLoad("/fxmls/ticketChange");
    }

    /**
     * trainFeatures function shows the answer for 
     * the question about online tickets
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void trainFeatures(ActionEvent event) {
        pageLoad("/fxmls/trainFeatures");
    }

    /**
     * yourQuestions function shows the answer for 
     * the question about online tickets
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void yourQuestions(ActionEvent event) {
        pageLoad("/fxmls/yourQuestions");
    }

    /**
     * load the scene to the stage
     *
     * @param page the String to display.
     */
    private void pageLoad(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FAQController.class.getName()).log(Level.SEVERE, null, ex);
        }
            borderPane.setCenter(root);
    }


}
