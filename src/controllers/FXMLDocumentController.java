package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Kerem ALEMDAR
 * @date 24.12.2019
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private Pane vbox;

    private Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.1), vbox);
        t.setToX(vbox.getLayoutX() * 9.5);
        t.play();
        t.setOnFinished((e) ->
        {
            try
            {
                fxml = FXMLLoader.load(getClass().getResource("/fxmls/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch(IOException ex)
            {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /*
     If the user wants to sign up, when they click the sign up button
     the page that will allow the user to create an account is opened.
     */
    @FXML
    private void open_signup(ActionEvent event)
    {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.5), vbox);
        t.setToX(10);
        t.play();
        t.setOnFinished((e) ->
        {
            try
            {
                fxml = FXMLLoader.load(getClass().getResource("/fxmls/SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch(IOException ex)
            {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    /*
     If the user clicks Sign In button, the page they will allow the user to 
     enter their information is opened. If they enter wrong e-mail or password
     an error is shown.
     */
    @FXML
    public void open_signin(ActionEvent event)
    {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.5), vbox);
        t.setToX(vbox.getLayoutX() * 9.5);
        t.play();
        t.setOnFinished((e) ->
        {
            try
            {
                fxml = FXMLLoader.load(getClass().getResource("/fxmls/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch(IOException ex)
            {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    /*
    If the user clicks exit, the program terminates
    */
    @FXML
    private void handleexit(ActionEvent event)
    {
        System.exit(0);
    }
    
    /*
     If the user clicks minimize button, the program is minimized.
     */
    @FXML
    public void handleminimize(ActionEvent event)
    {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

}
