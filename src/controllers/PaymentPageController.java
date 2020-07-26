/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import fxmls.SearchTrainsController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import traninmanagament.JavaMail;
import traninmanagament.Operations;
import traninmanagament.Seat;
import traninmanagament.Ticket;

/**
 * FXML Controller class
 *
 * @author Eylül ÇAĞLAR
 * @date 24.12.2019
 */
public class PaymentPageController implements Initializable
{

    @FXML
    private JFXTextField cardNumber;
    @FXML
    private Pane pnfront;
    @FXML
    private Label cardimage, validimage, imagecvc;
    @FXML
    private JFXTextField cvcNumber;
    @FXML
    private ImageView view1;
    @FXML
    private JFXTextField ownerName;
    @FXML
    private JFXComboBox<String> comboMonth;
    @FXML
    private JFXComboBox<String> comboYear;
    @FXML
    private JFXComboBox<String> comboMeal;
    @FXML
    private JFXTextArea notes;
    @FXML
    private Label error, money;

    static ArrayList<Seat> seats = new ArrayList<>();
    TrainController cont = new TrainController();
    SignInController scont = new SignInController();
    AppPageController acont = new AppPageController();
    SearchTrainsController tcont = new SearchTrainsController();
    Ticket ticket = null;
    Operations operation = new Operations();
    ObservableList<String> month = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
    ObservableList<String> year = FXCollections.observableArrayList("19", "20", "21", "22", "23", "24", "25");
    ObservableList<String> foodobserv = FXCollections.observableArrayList("-", "Red Meat  50TL", "White Meat   30TL", "Vegetarian   40TL", "Vegan   60TL");

    /**
     * Initializes the controller class.
     *
     * @param url the URL to display.
     * @param rb the ResourceBundle to display.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        comboYear.setItems(year);
        comboMonth.setItems(month);
        comboMeal.setItems(foodobserv);
        int totalmoney = Integer.valueOf(cont.getTrain().getCost()) * seats.size();
        money.setText(totalmoney + "");
    }

    /**
     * handlePay function can give opportunity to users that they can ennter
     * their credit card information.
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    public void handlePay(ActionEvent evt)
    {
        try
        {
            String cvc = cvcNumber.getText().trim();
            String card = cardNumber.getText().trim();
            String month = comboMonth.getValue();
            String year = comboYear.getValue();

            if(cvc.length() == 3 && card.length() == 16 && !month.isEmpty() && !year.isEmpty())
            {
                ticket = new Ticket(scont.getMemberemail(), seats, cont.getTrain(), acont.getDeparture(), acont.getArrival(), notes.getText());
                String ticketseats = "";
                for(Seat s : seats)
                {
                    s.setAvailable(false);
                    ticketseats += s.getSeatNumber() + ", ";
                    s.setArc(acont.getArrival());
                    operation.updateSeat(s);
                }
                new EmailSenderHelper1().start();
                operation.addTicket(scont.getMemberemail(), Date.valueOf(acont.getDate()), ticketseats, String.valueOf(cont.getTrain().getIdNumber()), acont.getDeparture(), acont.getArrival());
                Node node = (Node) evt.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/Fınısh.fxml")));
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                handleclose(evt);

            }
            else
            {
                System.out.println("Please fill the gaps!");
            }

        }
        catch(NullPointerException ex)
        {

        }
        catch(IOException ex)
        {

        }
    }

    /**
     * handleclose function turns the page to search train page back.
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    public void handleclose(ActionEvent evt)
    {
        try
        {
            seats.clear();
            Node node = (Node) evt.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/SearchTrains.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(PaymentPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * handleMeal function adds the fee for the meal if a user wats to eat red,
     * white meat or vegetarian meal or vegan meal
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    public void handleMeal(ActionEvent evt)
    {
        int mon = Integer.valueOf(money.getText());
        if(comboMeal.getValue().contains("Red"))
        {
            mon = Integer.valueOf(cont.getTrain().getCost()) * seats.size();
            mon += 50 * seats.size();
        }
        else if(comboMeal.getValue().contains("White"))
        {
            mon = Integer.valueOf(cont.getTrain().getCost()) * seats.size();
            mon += 30 * seats.size();
        }
        else if(comboMeal.getValue().contains("Vegetarian"))
        {
            mon = Integer.valueOf(cont.getTrain().getCost()) * seats.size();
            mon += 40 * seats.size();
        }
        else if(comboMeal.getValue().contains("Vegan"))
        {
            mon = Integer.valueOf(cont.getTrain().getCost()) * seats.size();
            mon += 60 * seats.size();
        }
        else if(comboMeal.getValue().equals("-"))
        {
            mon = Integer.valueOf(cont.getTrain().getCost()) * seats.size();
            mon += 0;
        }
        money.setText(String.valueOf(mon));
    }

    /**
     * handlecardnum function shows the card number on the card image
     *
     * @param event the KeyEvent to display.
     */
    @FXML
    public void handlecardnum(KeyEvent evt)
    {
        pnfront.setVisible(true);
        cardimage.setText(cardNumber.getText());
    }

    /**
     * handlevalidm function shows the card month valid on the card image
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    public void handlevalidm(ActionEvent evt)
    {
        String valid = comboMonth.getValue();
        valid += " / ";
        validimage.setText(valid);
    }

    /**
     * handlevalidy function shows the card year valid on the card image
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    public void handlevalidy(ActionEvent evt)
    {
        String valid = validimage.getText();
        valid += comboYear.getValue();
        validimage.setText(valid);
    }

    /**
     * handlecvcnum function shows the card cvc number on the card image
     *
     * @param event the KeyEvent to display.
     */
    @FXML
    public void handlecvcnum(KeyEvent evt)
    {
        pnfront.setVisible(false);
        imagecvc.setText(cvcNumber.getText());
    }

    // Inner class extends the Thread and send mail
    class EmailSenderHelper1 extends Thread
    {

        @Override
        public void run()
        {
            JavaMail.sendMail(ticket);
        }
    }

    public ArrayList<Seat> getSeats()
    {
        return seats;
    }
}
