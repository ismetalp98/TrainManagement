/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ezgi Lena Sönmez
 * @date 24.12.2019
 */
public class ManagerPageController implements Initializable
{

    @FXML
    private JFXRadioButton radio1, radio2, radio3;

    @FXML
    private ImageView view;
    @FXML
    private DatePicker picker;

    public VBox pntrains;

    public Button b_return;
    @FXML
    private ToggleGroup group1;
    @FXML
    private ToggleGroup groupgender;
    @FXML
    private ToggleGroup groupage;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Image image1 = new Image("/stayla/radio1.png");
        view.setImage(image1);
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(5000);
                }
                catch(InterruptedException ex)
                {
                    Logger.getLogger(AppPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                setimages();
            }
        }, 0, 5000);
    }

    @FXML
    public void handleview(ActionEvent evt)
    {

        if(radio2.isSelected())
        {
            Image image = new Image("/stayla/radio2.png");
            view.setImage(image);
        }
        else if(radio3.isSelected())
        {
            Image image = new Image("/stayla/radio3.png");
            view.setImage(image);
        }
        else if(radio1.isSelected())
        {
            Image image = new Image("/stayla/radio1.png");
            view.setImage(image);
        }

    }

    /*
     The manager is shown the images
     */
    public void setimages()
    {
        if(radio1.isSelected())
        {
            Image image2 = new Image("/stayla/radio2.png");
            view.setImage(image2);
            radio2.setSelected(true);
        }
        else if(radio2.isSelected())
        {
            Image image3 = new Image("/stayla/radio3.png");
            view.setImage(image3);
            radio3.setSelected(true);
        }
        else if(radio3.isSelected())
        {
            Image image1 = new Image("/stayla/radio1.png");
            view.setImage(image1);
            radio1.setSelected(true);
        }
    }

    /*
     If the manager clicks Log Out button, the program directs them to the 
     sign in and sign up page.
     */
    @FXML
    public void handlelogout(ActionEvent evt)
    {
        try
        {
            Node node = (Node) evt.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/FXMLDocument.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        }
        catch(IOException ex)
        {
            Logger.getLogger(AppPageController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     If the manager clicks Exit button, the program terminates.
     */
    @FXML
    public void handleexit(ActionEvent evt)
    {
        System.exit(0);
    }

    @FXML
    public void handleSss(ActionEvent evt)
    {

    }

    /*
     If the manager clicks Search button, the train list will be shown to the manager
     and he/she will be able to add train, delete train, refresh the page, and
     edit the train.
     */
    @FXML
    public void handlesearch(ActionEvent evt) throws Exception
    {
        try
        {
            Node node = (Node) evt.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/SearchPane.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
        }

    }

    /*
     If the manager clicks My Profile page, the profile page of the manager will be 
     shown to them.
     */
    @FXML
    public void handleMyProfile(ActionEvent evt) throws Exception
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/MyProfile.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    //Minimize the window
    @FXML
    public void handleminimize(ActionEvent evt)
    {
        Node node = (Node) evt.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }
}
