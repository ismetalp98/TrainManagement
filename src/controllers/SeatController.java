/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import traninmanagament.Operations;
import traninmanagament.Seat;

/**
 * FXML Controller class
 *
 * @author Mustafa Yaşar
 * @date 24.12.2019
 */
public class SeatController implements Initializable
{

    @FXML
    private Label num;
    @FXML
    private ImageView view;

    boolean chosed;
    boolean thischosed;
    static PaymentPageController scont = new PaymentPageController();
    AppPageController cont = new AppPageController();
    Seat seat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        chosed = false;
        thischosed = false;
    }

    //Set Seats by datas taken from database
    //@param Seat
    public void setTrain(Seat seat)
    {
        num.setText(seat.getSeatNumber());
        if(seat.isAvailable() == false)
        {
            view.setImage(new Image("/Icon/armchair1.png"));
            chosed = true;
            if(seat.getMiddleCity().equals(cont.getDeparture()) && seat.getArc().equals(cont.getDeparture()))
            {
                view.setImage(new Image("/Icon/armchair.png"));
                chosed = false;
            }
        }
        else
        {
            view.setImage(new Image("/Icon/armchair.png"));
        }
        this.seat = seat;
    }

    //Toggle button; if seat is selected cahnge the seat color if click again return previous color
    @FXML
    public void handlebutton(javafx.scene.input.MouseEvent evt)
    {

        if(!chosed)
        {
            if(thischosed == false)
            {
                view.setImage(new Image("/Icon/armchair2.png"));
                thischosed = true;
                scont.seats.add(seat);
                System.out.println(scont.seats.toString());
            }
            else
            {
                view.setImage(new Image("/Icon/armchair.png"));
                thischosed = false;
                scont.seats.remove(seat);
                System.out.println(scont.seats.toString());
            }
        }
    }

}
