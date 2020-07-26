/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import traninmanagament.Operations;
import traninmanagament.Seat;
import traninmanagament.Ticket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mıstafa Yaşar
 * @date 24.12.2019
 */
public class SeatsController implements Initializable
{

    @FXML
    public HBox pnseats1, pnseats2, pnseats3, pnseats4;
    @FXML
    public Label lbl_train;
    TrainController cont = new TrainController();
    AppPageController acont = new AppPageController();
    SignInController scont = new SignInController();
    Operations operation = new Operations();
    Ticket ticket = null;
    ArrayList<Seat> listOfSeat;
    static ArrayList<Seat> seats = new ArrayList<>();
    PaymentPageController pcont = new PaymentPageController();

    /**
     * Initializes the controller class. Load the Seats to HBoxes
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        listOfSeat = new ArrayList<>();
        loadSeat();
        lbl_train.setText(cont.getTrainNumber() + "");
        try
        {
            Node[] nodes = new Node[listOfSeat.size()];
            for(int i = 0; i < nodes.length; i++)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/Seat.fxml"));
                SeatController controller = new SeatController();
                loader.setController(controller);
                nodes[i] = loader.load();

                if(i % 4 == 0)
                {
                    pnseats1.getChildren().add(nodes[i]);
                }
                if(i % 4 == 1)
                {
                    pnseats2.getChildren().add(nodes[i]);
                }
                if(i % 4 == 2)
                {
                    pnseats3.getChildren().add(nodes[i]);
                }
                if(i % 4 == 3)
                {
                    pnseats4.getChildren().add(nodes[i]);
                }

                controller.setTrain(listOfSeat.get(i));
            }
        }
        catch(IOException ex)
        {
            Logger.getLogger(SeatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * handlenext method opens the Payment Page if it be clicked.
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    public void handlenext(ActionEvent evt)
    {
        if(pcont.getSeats().size() == 0)
        {
            JOptionPane.showMessageDialog(null, "Chose seat!");
        }
        else
        {
            try
            {
                Node node = (Node) evt.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/PaymentPage.fxml")));
                stage.setScene(scene);
                stage.show();
            }
            catch(IOException ex)
            {
                Logger.getLogger(SeatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * handleclose method turns the page to the Search Trains Page
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    public void handleclose(ActionEvent evt)
    {
        try
        {
            Node node = (Node) evt.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/SearchTrains.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(SeatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * handleminimize method takes down the Seats page.
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    public void handleminimize(ActionEvent evt)
    {
        Node node = (Node) evt.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * handleexit method closes the Seats page.
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    public void handleexit(ActionEvent evt)
    {
        System.exit(0);
    }

    /**
     * handleAdd method provides to load the seats whenever the user choses a
     * seat.
     *
     * this method gets the id number, seat number, middle city, available, and
     * arrivalcity.
     */
    public void loadSeat()
    {
        String qu = "SELECT * FROM seats WHERE idnumber='" + cont.getTrainNumber() + "'";
        ResultSet rs = operation.execQuery(qu);
        try
        {
            while(rs.next())
            {
                int idNumber = rs.getInt("idnumber");
                String number = rs.getString("seatnumber");
                String middleCity = rs.getString("middlecity");
                String avaible = rs.getString("available");
                String arc = rs.getString("arrivalcity");
                listOfSeat.add(new Seat(idNumber, middleCity, arc, number, Boolean.valueOf(avaible)));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(SeatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
