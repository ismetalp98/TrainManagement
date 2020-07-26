/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traninmanagament;

/**
 * Seat class
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class Seat
{

    //Instances
    public String seatNumber;
    public boolean available;
    public int idNumber;
    public String middleCity;
    public String arc;

    /**
     * Creates an Seat with the specified variables.
     */
    public Seat(int idNumber, String middleCity, String arc, String seatNumber, boolean available)
    {
        this.idNumber = idNumber;
        this.seatNumber = seatNumber;
        this.available = available;
        this.arc = arc;
        this.middleCity = middleCity;
    }

    //Getters and Setters
    public String getSeatNumber()
    {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber)
    {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean avaible)
    {
        this.available = avaible;
    }

    public int getIdNumber()
    {
        return idNumber;
    }

    public void setIdNumber(int idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getMiddleCity()
    {
        return middleCity;
    }

    public void setMiddleCity(String middleCity)
    {
        this.middleCity = middleCity;
    }

    public String getArc()
    {
        return arc;
    }

    public void setArc(String arc)
    {
        this.arc = arc;
    }
}
