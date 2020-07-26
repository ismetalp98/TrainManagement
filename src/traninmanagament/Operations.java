package traninmanagament;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Operations class
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class Operations
{

    //Instances
    private Connection con = null;

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    //Constructer
    public Operations()
    {
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_name + "?useUnicode=true&characterEncoding=utf8";

        try
        {

            Class.forName("com.mysql.jdbc.Driver");

        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Driver Can Not Found....");
        }

        try
        {
            con = DriverManager.getConnection(url, Database.user_name, Database.password);
            System.out.println("Connected...");

        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Check the Internet Connection!!!");
            //ex.printStackTrace();
        }
    }

    /**
     * add Member to Database with given features
     */
    public boolean addMember(String name, String lastName, String email, String password)
    {
        String sql = "Insert Into members (name,lastname,email,password,survey) VALUES(?,?,?,?,?)";

        try
        {
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, "false");

            preparedStatement.executeUpdate();

            return true;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "E-Mail already exist");
        }
        return false;
    }

    /**
     * control the database weather Member is existing with given features
     */
    public boolean singin(String email, String password)
    {
        String sql = "Select * From members where email = ? and password = ?";
        try
        {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * add Train to Database with given features
     */
    public boolean addTrain(int idNumber, String dpt, String art, String mt, String date, String cost, String dpc, String arc, String mc)
    {
        String sql = "Insert Into trains (idnumber,departuretime,arrivaltime,middletime,date,cost,departurecity,arrivalcity,middlecity) VALUES(?,?,?,?,?,?,?,?,?)";
        try
        {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idNumber);
            preparedStatement.setString(2, dpt);
            preparedStatement.setString(3, art);
            preparedStatement.setString(4, mt);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, cost);
            preparedStatement.setString(7, dpc);
            preparedStatement.setString(8, arc);
            preparedStatement.setString(9, mc);
            preparedStatement.executeUpdate();
            return true;

        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Train Already Exist!");
        }
        return false;
    }

    /**
     * update the seats in the Database
     */
    public void updateSeat(Seat seat)
    {
        try
        {
            String update = "UPDATE seats SET available=?, arrivalcity=? WHERE seatnumber=? AND idnumber=?";
            preparedStatement = con.prepareStatement(update);
            preparedStatement.setString(1, String.valueOf(seat.isAvailable()));
            preparedStatement.setString(2, seat.getArc());
            preparedStatement.setString(3, seat.getSeatNumber());
            preparedStatement.setInt(4, seat.getIdNumber());
            preparedStatement.executeUpdate();

        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * another update method to update seat for another purposes
     */
    public void updateSeatTrain(int idNumber, String middleCity, String arrivalcity, String seatnumber, boolean available)
    {
        try
        {
            String update = "UPDATE seats SET available=?, arrivalcity=?, middlecity=? WHERE seatnumber=? AND idnumber=?";
            preparedStatement = con.prepareStatement(update);
            preparedStatement.setString(1, String.valueOf(available));
            preparedStatement.setString(2, arrivalcity);
            preparedStatement.setString(3, middleCity);
            preparedStatement.setString(4, seatnumber);
            preparedStatement.setInt(5, idNumber);
            preparedStatement.executeUpdate();

        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * update the Member in the Database
     */
    public void updateMember(boolean survey, String email)
    {
        try
        {
            String update = "UPDATE members SET survey=? WHERE email=?";
            preparedStatement = con.prepareStatement(update);
            preparedStatement.setString(1, String.valueOf(survey));
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println("Problrm");
        }
    }

    /**
     * add Seat to the Database
     */
    public void addSeat(int idNumber, String middleCity, String arrivalcity, String number, boolean available)
    {
        String sql = "Insert Into seats (idnumber,middlecity,arrivalcity,seatnumber,available) VALUES(?,?,?,?,?)";
        try
        {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idNumber);
            preparedStatement.setString(2, middleCity);
            preparedStatement.setString(3, arrivalcity);
            preparedStatement.setString(4, number);
            preparedStatement.setString(5, String.valueOf(available));
            preparedStatement.executeUpdate();

        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete train from Database
     */
    public void deleteTrain(Train train)
    {
        try
        {
            String deleteStatement = "DELETE FROM trains WHERE idNumber = ?";
            preparedStatement = con.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, train.getIdNumber());
            preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete Seat from Database
     */
    public void deleteSeats(int idnumber)
    {
        try
        {
            String deleteStatement = "DELETE FROM seats WHERE idnumber=?";
            preparedStatement = con.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, idnumber);
            preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * update the Train in the Database
     */
    public boolean updateTrain(int idNumber, String dpt, String art, String date, String cost, String dpc, String arc, String mc)
    {
        try
        {
            String update = "UPDATE trains SET departuretime=?,arrivaltime=?,date=?,cost=?,departurecity=?,arrivalcity=?,middlecity=? WHERE idnumber=?";
            preparedStatement = con.prepareStatement(update);
            preparedStatement.setString(1, dpt);
            preparedStatement.setString(2, art);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, cost);
            preparedStatement.setString(5, dpc);
            preparedStatement.setString(6, arc);
            preparedStatement.setString(7, mc);
            preparedStatement.setInt(8, idNumber);
            preparedStatement.executeUpdate();
            return true;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * add Ticket to the Database
     */
    public void addTicket(String membermail, java.sql.Date date, String seats, String trainid, String departure, String arrival)
    {
        try
        {
            String sql = "Insert Into tickets (membermail,date,seats,trainidnumber,departure,arrival) VALUES(?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, membermail);
            preparedStatement.setDate(2, date);
            preparedStatement.setString(3, seats);
            preparedStatement.setString(4, trainid);
            preparedStatement.setString(5, departure);
            preparedStatement.setString(6, arrival);
            preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return result to search tables in the Databes
     */
    public ResultSet execQuery(String query)
    {
        ResultSet result = null;
        try
        {
            preparedStatement = con.prepareStatement(query);
            result = preparedStatement.executeQuery(query);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
        }
        return result;
    }
}
