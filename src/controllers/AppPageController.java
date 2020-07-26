/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import traninmanagament.JavaMail;
import traninmanagament.Operations;

/**
 * This class is a FXML AppPageController class which shows the main menu where
 * users are able to search trains, enter their profile, enter frequently ask
 * question page.
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class AppPageController implements Initializable
{

    @FXML
    private JFXRadioButton radio1, radio2, radio3, rmale, rfemale, r18, r24, r30, r100, r1, r2, r3, r4, r5;

    @FXML
    private ImageView view;

    @FXML
    private JFXComboBox<String> combodeparture;

    @FXML
    private ComboBox<String> comboarrival;

    @FXML
    private DatePicker picker;

    public VBox pntrains;

    public Button b_return;

    @FXML
    public Pane surveyPane;
    Operations operation;
    SignInController cont = new SignInController();

    ObservableList<String> list = FXCollections.observableArrayList("Ankara", "Edirne", "Eskişehir", "İstanbul", "İzmir", "Tekirdağ", "Kocaeli");
    static String arrival, departure;
    static LocalDate date;
    @FXML
    private ToggleGroup group1;
    @FXML
    private ToggleGroup groupgender;
    @FXML
    private ToggleGroup groupage;
    @FXML
    private Button exit;
    String survey = "YOUR CHOICES ARE: ";
    LocalDate now = LocalDate.now();

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

        combodeparture.setItems(list);
        comboarrival.setItems(list);

    }

    /*
     *This method puts the images to view.
     */
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
     handleLogout() method allows the user to log out from their accounts.
     Directs the user to sign in page.
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

    // When the user click EXIT button, the program ends.
    @FXML
    public void handleexit(ActionEvent evt)
    {
        System.exit(0);
    }

    //Show the FAQ page
    @FXML
    public void handleSss(ActionEvent evt)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/FAQ.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(AppPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     When the user choose every city and the date of the train and clicks Search
     button the train list is shown to the user so that they can choose train
     */
    @FXML
    public void handlesearch(ActionEvent evt) throws Exception
    {
        try
        {
            arrival = comboarrival.getValue();
            departure = combodeparture.getValue();
            date = picker.getValue();
            if(arrival.equals(departure))
            {
                JOptionPane.showMessageDialog(null, "Departure city and arrival city cannot be the same!");
            }
            else if(comboarrival.getValue().equals(null))
            {
                JOptionPane.showMessageDialog(null, "Choose Arrival City!");
            }
            else if(combodeparture.getValue().equals(null))
            {
                JOptionPane.showMessageDialog(null, "Choose Arrival City!");
            }
            else if(picker.getValue().equals(null))
            {
                JOptionPane.showMessageDialog(null, "Choose Arrival City!");
            }
            else
            {

                Node node = (Node) evt.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/SearchTrains.fxml")));
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }

        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null, "Please make your all selections!");
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Please make your all selections!");
        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
        }

    }

    /*
     This function shows the Profile Page to the user when they click
     My Profile button
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

    /*
     When the user sends a survey, an e-mail is generated and sent to the 
     admin
     */
    @FXML
    public void handlesendsurvey(ActionEvent evt)
    {

        if(rmale.isSelected())
        {
            survey += "\n Gender is: " + rmale.getText();
        }
        if(rfemale.isSelected())
        {
            survey += "\n Gender is: " + rfemale.getText();
        }
        if(r18.isSelected())
        {
            survey += "\n Age is: " + r18.getText();
        }
        if(r24.isSelected())
        {
            survey += "\n Age is: " + r24.getText();
        }
        if(r30.isSelected())
        {
            survey += "\n Age is: " + r30.getText();
        }
        if(r100.isSelected())
        {
            survey += "\n Age is: " + r100.getText();
        }
        if(r1.isSelected())
        {
            survey += "\n Point you give to us: " + r1.getText();
        }
        if(r2.isSelected())
        {
            survey += "\n Point you give to us: " + r2.getText();
        }
        if(r3.isSelected())
        {
            survey += "\n Point you give to us: " + r3.getText();
        }
        if(r4.isSelected())
        {
            survey += "\n Point you give to us: " + r4.getText();
        }
        if(r5.isSelected())
        {
            survey += "\n Point you give to us: " + r5.getText();
        }
        FadeTransition trainsiyion = new FadeTransition(Duration.millis(500), surveyPane);
        trainsiyion.setFromValue(1.0);
        trainsiyion.setToValue(0.0);
        trainsiyion.play();
        new EmailSenderHelper().start();
    }

    //Minimize the window
    @FXML
    public void handleminimize(ActionEvent evt)
    {
        Node node = (Node) evt.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    //Getter and Setters
    public String getArrival()
    {
        return arrival;
    }

    public String getDeparture()
    {
        return departure;
    }

    public LocalDate getDate()
    {
        return date;
    }

    //Send Email
    class EmailSenderHelper extends Thread
    {

        @Override
        public void run()
        {
            JavaMail.sendMail(survey);
        }
    }
}
