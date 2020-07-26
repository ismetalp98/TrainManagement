package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import traninmanagament.Operations;

/**
 * FXML Controller class
 *
 * @author Kerem Alemdar
 * @date 24.12.2019
 */
public class SignInController implements Initializable
{

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Label lbl_error;

    @FXML
    private Button b_singin;

    Operations operation = new Operations();
    public static String memberemail = "";

    /**
     * Initializes the controller class. 
     *
     * @param url the URL to display. 
     * @param rb the ResourceBundle to display.
     */
    public void initialize(URL url, ResourceBundle rb)
    {
    }

     /**
     * handleSingIn function provides 
     * user to fill the gaps for singing in to the home page
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    public void handleSingIn(ActionEvent event)
    {

        String email = tf_email.getText();
        String pss = tf_password.getText();
        if(tf_email.getText().equals("") || pss.trim() == "")
        {
            tf_email.getStyleClass().add("wrong-credentials");
            tf_password.getStyleClass().add("wrong-credentials");
            lbl_error.setText("* Fill the gaps correctly! ");
        }
        else if(!email.isEmpty() && !pss.isEmpty())
        {
            boolean singin = operation.singin(email, pss);
            if(singin)
            {
                try
                {
                    memberemail = email;
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    if(email.equals("admin") && pss.equals("admin"))
                    {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/ManagerPage.fxml")));
                        scene.setFill(Color.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else
                    {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/AppPage_1.fxml")));
                        scene.setFill(Color.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
                catch(IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
            else
            {
                lbl_error.setText("Account not found");
            }
        }
    }

     /**
     * Returns an String object for
     * getting the mail of members.
     * @return the memberemail.
     * @see String
     */
    public String getMemberemail()
    {
        return memberemail;
    }
}
