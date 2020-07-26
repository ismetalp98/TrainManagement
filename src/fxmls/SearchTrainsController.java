package fxmls;

import controllers.AppPageController;
import controllers.TrainController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import traninmanagament.Operations;
import traninmanagament.Train;

/**
 * FXML Controller class
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class SearchTrainsController implements Initializable
{

    @FXML
    public VBox pntrains;

    @FXML
    public Button b_return;

    @FXML
    public Label lbl_departure, lbl_arrival;

    Operations operation = new Operations();
    AppPageController cont = new AppPageController();

    List<Train> listOfTrains;

     /**
     * Initializes the controller class. 
     *
     * @param url the URL to display. 
     * @param rb the ResourceBundle to display.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listOfTrains = new ArrayList<>();
        lbl_arrival.setText(cont.getArrival());
        lbl_departure.setText(cont.getDeparture());
        System.out.println(cont.getDate());
        loadTrains();
        int size = listOfTrains.size();
        try
        { //load task items to vbox
            Node[] nodes = new Node[size];
            for(int i = 0; i < nodes.length; i++)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Train.fxml"));
                TrainController controller = new TrainController();
                loader.setController(controller);
                nodes[i] = loader.load();
                pntrains.getChildren().add(nodes[i]);
                controller.setTrain(listOfTrains.get(i));
            }

        }
        catch(IOException ex)
        {
            Logger.getLogger(SearchTrainsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * handleclose method gets back the 
     * search train page to Home page for the user
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
            //stage.setMaximized(true);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/AppPage_1.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(SearchTrainsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     /**
     * handleexit method closes the window.
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
     * handleexit method closes the window.
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    public void handleexit(ActionEvent evt)
    {
        System.exit(0);
    }

    /**
     * loadTrains method gets the information
     * about trains and loads the train to the listOfTrains.
     * 
     * this methos gets
     * idnumber, departuretime, arrivaltime, middletime, date, cost,
     * departurecity, arrivalcity, and middlecity.
     */
    public void loadTrains()
    {

        String asd = "SELECT * FROM trains";
        ResultSet rs = operation.execQuery(asd);
        try
        {
            while(rs.next())
            {
                int idNumber = rs.getInt("idnumber");
                String dpt = rs.getString("departuretime");
                String art = rs.getString("arrivaltime");
                String mt = rs.getString("middletime");
                String date = rs.getString("date");
                String cost = rs.getString("cost");
                String dpc = rs.getString("departurecity");
                String arc = rs.getString("arrivalcity");
                String mc = rs.getString("middlecity");
                if((dpc.equals(lbl_departure.getText()) || mc.equals(lbl_departure.getText())) && (arc.equals(lbl_arrival.getText()) || mc.equals(lbl_arrival.getText())) && date.equals(String.valueOf(cont.getDate())))
                {
                    listOfTrains.add(new Train(idNumber, dpt, art,mt, date, cost, dpc, arc, mc));
                }
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(SearchTrainsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
