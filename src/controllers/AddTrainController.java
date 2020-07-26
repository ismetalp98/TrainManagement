/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import traninmanagament.Operations;

/**
 * FXML AddTrainController class which allows the manager 
 * to add a train to train list.
 * 
 * @author Eylül Çağlar
 * @date 24.12.2019
 */
public class AddTrainController implements Initializable
{

    //variables
    @FXML
    private JFXComboBox<String> combodpt, comboarrival, combointt;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private DatePicker datepicker;
    @FXML
    private JFXTextField tf_cost;
    @FXML
    private JFXComboBox<String> cb_dpc;
    @FXML
    private JFXComboBox<String> cb_arc;
    @FXML
    private JFXComboBox<String> cb_mc;
    @FXML
    private JFXTextField tf_idnumber;
    @FXML
    private Label lbl_situtation;

    ObservableList<String> listinter = FXCollections.observableArrayList("-", "Ankara", "Edirne", "Eskişehir", "İstanbul", "İzmir", "Tekirdağ", "Kocaeli");
    ObservableList<String> list = FXCollections.observableArrayList("Ankara", "Edirne", "Eskişehir", "İstanbul", "İzmir", "Tekirdağ", "Kocaeli");
    ObservableList<String> timelist = FXCollections.observableArrayList("-", "00.00", "00.30", "01.00", "01.30", "02.00", "02.30", "03.00", "03.30", "04.00", "04.30", "05.00", "05.30", "06.00", "06.30", "07.00", "07.30", "08.00", "08.30", "09.00", "09.30", "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.00", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30", "17.00", "17.30", "18.00", "18.30", "19.00", "19.30", "20.00", "20.30", "21.00", "21.30", "22.00", "22.30", "23.00", "23.30");
    Operations operation;
    LocalDate nowdate = LocalDate.now();

     /**
     * Initializes the controller class. 
     *
     * @param url the URL to display. 
     * @param rb the ResourceBundle to display.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        operation = new Operations();
        cb_arc.setItems(list);
        cb_dpc.setItems(list);
        cb_mc.setItems(listinter);
        comboarrival.setItems(timelist);
        combodpt.setItems(timelist);
        combointt.setItems(timelist);
    }

     /**
     * handleAdd function performs what should the program do when 
       the user clicks Add button.
     *
     * @param event the ActionEvent to display.
     */
    @FXML
    private void handleAdd(ActionEvent event)
    {
        try
        {
            int trainidnumber = Integer.valueOf(tf_idnumber.getText());
            String traindpt = combodpt.getValue();
            String trainart = comboarrival.getValue();
            String mt = combointt.getValue();
            String middlecity = cb_mc.getValue();
            LocalDate traindate = datepicker.getValue();
            String date = String.valueOf(traindate);
            System.out.println(date);
            String traincost = tf_cost.getText();
            String traindpc = cb_dpc.getValue();
            String trainarc = cb_arc.getValue();

            if(trainarc.equals(traindpc))
            {
                JOptionPane.showMessageDialog(null, "Chose diffrent city!");
            }
            else if(trainarc.equals(middlecity))
            {
                JOptionPane.showMessageDialog(null, "Chose diffrent city!");
            }
            else if(traindpc.equals(middlecity))
            {
                JOptionPane.showMessageDialog(null, "Chose diffrent city!");
            }
            if(trainart.equals(traindpt))
            {
                JOptionPane.showMessageDialog(null, "Chose diffrent time!");
            }
            else if(trainart.equals(mt))
            {
                JOptionPane.showMessageDialog(null, "Chose diffrent time!");
            }
            else if(traindpt.equals(mt))
            {
                JOptionPane.showMessageDialog(null, "Chose diffrent time!");
            }
            else if(trainart.compareTo(traindpt) < 0)
            {
                JOptionPane.showMessageDialog(null, "Arrival cannot be earlier than Deparure Time!");
            }

            else if(trainart.compareTo(mt) < 0 && !mt.equals("-"))
            {
                JOptionPane.showMessageDialog(null, "Arrival cannot be earlier than Intermediate Arrival Time!");
            }
            else if(mt.compareTo(traindpt) < 0 && !mt.equals("-"))
            {
                JOptionPane.showMessageDialog(null, "Intermediate Arrival Time cannot be earlier than Deparure Time!");
            }

            else if(traindate.compareTo(nowdate) < 0)
            {
                JOptionPane.showMessageDialog(null, "Train Date connot be earlier than today!");
            }
            else
            {
                boolean added = operation.addTrain(trainidnumber, traindpt, trainart, mt, date, traincost, traindpc, trainarc, middlecity);
                if(added)
                {
                    if(middlecity.equals("-"))
                    {
                        for(int i = 0; i < 20; i++)
                        {
                            operation.addSeat(trainidnumber, "-", trainarc, String.valueOf(i + 1), true);
                        }
                    }
                    else
                    {
                        for(int i = 0; i < 20; i++)
                        {
                            operation.addSeat(trainidnumber, middlecity, trainarc, String.valueOf(i + 1), true);
                        }
                    }
                    lbl_situtation.setText("Voyage Added");
                }
            }
        }
        catch(NullPointerException ex)
        {
            lbl_situtation.setText("Fill all the gaps! ");
            JOptionPane.showMessageDialog(null, "Fill all the gaps! ");
        }
        catch(NumberFormatException ex)
        {
            lbl_situtation.setText("Fill all the gaps! ");
            JOptionPane.showMessageDialog(null, "Fill all the gaps! ");
        }
    }

    /**
     * handleReset() function performs the actions when the user click reset button.
     *
     * @param evet the ActionEvent to display.
     */
    @FXML
    private void handleReset(ActionEvent event)
    {

    }

}
