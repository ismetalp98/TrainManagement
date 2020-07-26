/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import traninmanagament.Member;
import traninmanagament.Operations;

/**
 * FXML Controller class
 *
 * @author Eylül Çağlar
 * @date 24.12.2019
 */
public class MyProfileController implements Initializable
{

    @FXML
    private TableView<Tickets> tableView;
    @FXML
    private TableColumn<Tickets, String> dpcol;
    @FXML
    private TableColumn<Tickets, String> seatscol;
    @FXML
    private TableColumn<Tickets, String> arcol;
    @FXML
    private TableColumn<Tickets, String> traincol;
    @FXML
    private TableColumn<Tickets, String> datecol;
    @FXML
    private TableColumn<Tickets, String> conditioncol;

    @FXML
    private Label lbl_name, lbl_lastName, lbl_eMail;

    Operations operation = new Operations();
    SignInController cont = new SignInController();
    ObservableList<Tickets> list = FXCollections.observableArrayList();
    Member member;
    LocalDate nowdate = LocalDate.now();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setMember();
        initCol();
        loadTickets();
    }

    /*
     The initcol() initilize the colums for adding tickets to tickets table
     */
    private void initCol()
    {
        seatscol.setCellValueFactory(new PropertyValueFactory<>("seats"));
        traincol.setCellValueFactory(new PropertyValueFactory<>("idnum"));
        dpcol.setCellValueFactory(new PropertyValueFactory<>("dp"));
        arcol.setCellValueFactory(new PropertyValueFactory<>("ar"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        conditioncol.setCellValueFactory(new PropertyValueFactory<>("condition"));
    }

    /*
     The setMember() method finds the user from the database and adjusts the 
     information of the member in My Profile page.
     */
    public void setMember()
    {
        loadMember();
        lbl_name.setText(member.getName());
        lbl_lastName.setText(member.getLastName());
        lbl_eMail.setText(member.geteMail());
    }

    /*
     loadMember() method loads every member from the database.
     */
    public void loadMember()
    {
        String qu = "SELECT * FROM members";
        ResultSet rs = operation.execQuery(qu);
        try
        {
            while(rs.next())
            {
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                if(email.equals(cont.getMemberemail()))
                {
                    member = new Member(name, lastName, email, password);
                }
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(MyProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     loadTickets() method loads every ticket that the user purchased so that they 
     will be shown to the user in My Profile page.
     */
    public void loadTickets()
    {
        list.clear();
        try
        {
            String sql = "SELECT * FROM tickets WHERE membermail='" + cont.getMemberemail() + "'";
            ResultSet rs = operation.execQuery(sql);
            while(rs.next())
            {
                String date = rs.getString("date");
                String seats = rs.getString("seats");
                seats = seats.substring(0, seats.length() - 2);
                String trainidnumber = rs.getString("trainidnumber");
                String departure = rs.getString("departure");
                String arrival = rs.getString("arrival");

                list.add(new Tickets(date, seats, trainidnumber, departure, arrival));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(MyProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(list);
    }

    //Ticket Class to add TableView
    public class Tickets
    {

        public String seats;
        public String idnum;
        public String dp;
        public String ar;
        public String date;
        public String condition;

        public Tickets(String date, String seats, String idnum, String dp, String ar)
        {
            this.date = date;
            this.seats = seats;
            this.idnum = idnum;
            this.dp = dp;
            this.ar = ar;
            if(String.valueOf(nowdate).compareTo(date) > 0)
            {
                condition = "Expired";
            }
            else
            {
                condition = "Active";
            }
        }

        public String getSeats()
        {
            return seats;
        }

        public void setSeats(String seats)
        {
            this.seats = seats;
        }

        public String getIdnum()
        {
            return idnum;
        }

        public void setIdnum(String idnum)
        {
            this.idnum = idnum;
        }

        public String getDp()
        {
            return dp;
        }

        public void setDp(String dp)
        {
            this.dp = dp;
        }

        public String getAr()
        {
            return ar;
        }

        public void setAr(String ar)
        {
            this.ar = ar;
        }

        public String getDate()
        {
            return date;
        }

        public void setDate(String date)
        {
            this.date = date;
        }

        public String getCondition()
        {
            return condition;
        }

        public void setCondition(String condition)
        {
            this.condition = condition;
        }
    }
}
