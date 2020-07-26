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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import traninmanagament.Operations;
import traninmanagament.Train;

/**
 * FXML Controller class
 *
 * @author Ezgi Lena SÖNMEZ
 * @date 24.12.2019
 */
public class SearchPaneController implements Initializable
{

    @FXML
    private TableView<Train> tableView;
    @FXML
    private TableColumn<Train, String> dptcol;
    @FXML
    private TableColumn<Train, String> artcol;
    @FXML
    private TableColumn<Train, String> datecol;
    @FXML
    private TableColumn<Train, String> costcol;
    @FXML
    private TableColumn<Train, String> dpccol;
    @FXML
    private TableColumn<Train, String> arccol;
    @FXML
    private TableColumn<Train, String> mtcol;
    @FXML
    private TableColumn<Train, String> mccol;
    @FXML
    private TableColumn<Train, Integer> idnumcol;
    static Train train;

    ObservableList<Train> list = FXCollections.observableArrayList();
    Operations operation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        operation = new Operations();
        initCol();
        loadData();
    }

    /**
     * Returns a Stage object for viewing the table.
     *
     * @return tableView.getScene().getWindow() as Stage.
     * @see Stage
     */
    private Stage getStage()
    {
        return (Stage) tableView.getScene().getWindow();
    }

    /**
     * initCol() method shows the given information on the window to make user
     * understand which column for which information.
     */
    private void initCol()
    {
        idnumcol.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        dptcol.setCellValueFactory(new PropertyValueFactory<>("departuretime"));
        artcol.setCellValueFactory(new PropertyValueFactory<>("arrivaltime"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        costcol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dpccol.setCellValueFactory(new PropertyValueFactory<>("departurecity"));
        arccol.setCellValueFactory(new PropertyValueFactory<>("arrivalcity"));
        mccol.setCellValueFactory(new PropertyValueFactory<>("middlecity"));
        mtcol.setCellValueFactory(new PropertyValueFactory<>("middletime"));
    }

    /**
     * loadData() method gets the idnumber, departure time, intermediate time,
     * arrival time, date, cost, departure city, arrival city, and middle city
     * for loading new the trains.
     */
    public void loadData()
    {
        list.clear();
        String qu = "SELECT * FROM trains";
        ResultSet rs = operation.execQuery(qu);
        try
        {
            while(rs.next())
            {
                int idNumber = rs.getInt("idnumber");
                String dpt = rs.getString("departuretime");
                String mt = rs.getString("middletime");
                String art = rs.getString("arrivaltime");
                String date = rs.getString("date");
                String cost = rs.getString("cost");
                String dpc = rs.getString("departurecity");
                String arc = rs.getString("arrivalcity");
                String mc = rs.getString("middlecity");

                list.add(new Train(idNumber, dpt, art, mt, date, cost, dpc, arc, mc));

            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(SearchPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(list);
    }

    /**
     * handleAdd() method add the train on the window from AddTrain.fxml window
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    public void handleAdd(ActionEvent evt)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/AddTrain.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(SearchPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * handleRefresh() method refreshes the page if a train is not seen there
     * even if it is being added.
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void handleRefresh(ActionEvent event)
    {
        loadData();
    }

    /**
     * handleDelete() method deletes the train if it is not wanted.
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void handleDelete(ActionEvent event)
    {
        Train selected = tableView.getSelectionModel().getSelectedItem();
        if(selected == null)
        {
            JOptionPane.showMessageDialog(null, "Select Train");
        }
        else
        {
            operation.deleteSeats(selected.getIdNumber());
            operation.deleteTrain(selected);
        }
        loadData();
    }

    /**
     * handleEdit() method allows the manager to edit/changes the information an
     * uploaded train again.
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void handleEdit(ActionEvent event)
    {
        Train selected = tableView.getSelectionModel().getSelectedItem();
        if(selected == null)
        {
            JOptionPane.showMessageDialog(null, "Select Train");
        }
        else
        {
            try
            {
                SearchPaneController.train = selected;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/UpdateTrain.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();

            }
            catch(IOException ex)
            {
                Logger.getLogger(SearchPaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * handleClose() method allows the manager to back to the Manager Page.
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    private void handleClose(ActionEvent evt)
    {
        try
        {
            Node node = (Node) evt.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmls/ManagerPage.fxml")));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(SearchPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * handleminimize() method takes down the page.
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
     * handleExit() method for closing the window/ page.
     *
     * @param evt the ActionEvent to display.
     */
    @FXML
    public void handleExit(ActionEvent evt)
    {
        System.exit(0);
    }

    /**
     * Returns an Train object which has information.
     *
     * @return the train.
     * @see Train
     */
    public Train getTrain()
    {
        return train;
    }

    /**
     * setTrain() allows to sets set the information about train.
     *
     * @param train the setTrain to display.
     */
    public void setTrain(Train train)
    {
        SearchPaneController.train = train;
    }

}
