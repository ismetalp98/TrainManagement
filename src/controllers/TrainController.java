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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import traninmanagament.Train;

/**
 * FXML Controller class
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class TrainController implements Initializable
{

    @FXML
    private Label dpt;
    @FXML
    private Label art;
    @FXML
    private Label date;
    @FXML
    private Label cost, lbl_idnumber;
    @FXML
    private Label dpc;
    @FXML
    private Label arc, lbl_direct;
    @FXML
    private static Button button;
    private static int trainNumber = 0;
    private static Train train;
    AppPageController cont = new AppPageController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    /**
     * Initializes the Train by datas taken from Database
     * @param train
     */
    public void setTrain(Train train)
    {
        if(cont.getDeparture().equals(train.getMiddlecity()))
        {
            dpt.setText(train.getMiddletime());
            art.setText(train.getArrivaltime());
            date.setText(train.getDate());
            cost.setText(train.getCost() + " TL");
            lbl_idnumber.setText(String.valueOf(train.getIdNumber()));
            if(train.getMiddlecity().equals("-"))
            {
                lbl_direct.setText("No Transfer Station");
            }
            else
            {
                lbl_direct.setText(train.getMiddlecity());
            }
        }
        else if(cont.getArrival().equals(train.getMiddlecity()))
        {
            dpt.setText(train.getDeparturetime());
            art.setText(train.getMiddletime());
            date.setText(train.getDate());
            cost.setText(train.getCost() + " TL");
            lbl_idnumber.setText(String.valueOf(train.getIdNumber()));
            if(train.getMiddlecity().equals("-"))
            {
                lbl_direct.setText("No Transfer Station");
            }
            else
            {
                lbl_direct.setText(train.getMiddlecity());
            }
        }
        else
        {
            dpt.setText(train.getDeparturetime());
            art.setText(train.getArrivaltime());
            date.setText(train.getDate());
            cost.setText(train.getCost() + " TL");
            lbl_idnumber.setText(String.valueOf(train.getIdNumber()));
            if(train.getMiddlecity().equals("-"))
            {
                lbl_direct.setText("No Transfer Station");
            }
            else
            {
                lbl_direct.setText("Transfer station: " + train.getMiddlecity());
            }
        }

        this.train = train;
    }

    /**
     * Chose the train that will be bought
     */
    @FXML
    public void handlebutton(ActionEvent evt)
    {
        try
        {
            trainNumber = Integer.valueOf(lbl_idnumber.getText());
            Node node = (Node) evt.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/Seats_1.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(TrainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getters
    public Train getTrain()
    {
        return train;
    }

    public int getTrainNumber()
    {
        return trainNumber;
    }

    public Button getButton()
    {
        return button;
    }
}
