/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traninmanagament;

/**
 * Train class
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class Train
{

    //Instances
    int idNumber;
    String departuretime;
    String arrivaltime;
    String date;
    String cost;
    String departurecity;
    String arrivalcity;
    String middlecity;
    String middletime;

    /**
     * Creates an Train with the specified variables.
     */
    public Train(int idNumber, String departuretime, String arrivaltime, String middletime, String date, String cost, String departurecity, String arrivalcity, String middlecity)
    {
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
        this.date = date;
        this.cost = cost;
        this.departurecity = departurecity;
        this.arrivalcity = arrivalcity;
        this.idNumber = idNumber;
        this.middlecity = middlecity;
        this.middletime = middletime;
    }

    //Getters and Setters
    public String getDeparturetime()
    {
        return departuretime;
    }

    public void setDeparturetime(String departuretime)
    {
        this.departuretime = departuretime;
    }

    public String getArrivaltime()
    {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime)
    {
        this.arrivaltime = arrivaltime;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    public String getDeparturecity()
    {
        return departurecity;
    }

    public void setDeparturecity(String departurecity)
    {
        this.departurecity = departurecity;
    }

    public String getArrivalcity()
    {
        return arrivalcity;
    }

    public void setArrivalcity(String arrivalcity)
    {
        this.arrivalcity = arrivalcity;
    }

    public int getIdNumber()
    {
        return idNumber;
    }

    public void setIdNumber(int idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getMiddlecity()
    {
        return middlecity;
    }

    public void setMiddlecity(String middlecity)
    {
        this.middlecity = middlecity;
    }

    public String getMiddletime()
    {
        return middletime;
    }

    public void setMiddletime(String middletime)
    {
        this.middletime = middletime;
    }    
}
