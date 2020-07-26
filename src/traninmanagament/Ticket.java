/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traninmanagament;

import java.util.ArrayList;

/**
 * Ticket class
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class Ticket
{

    //Instances
    private ArrayList<Seat> list;
    private Train train;
    private String member;
    private String departure;
    private String arrival;
    private String note;

    /**
     * Creates an Ticket with the specified variables.
     */
    public Ticket(String member, ArrayList<Seat> list, Train train, String departure, String arrival, String note)
    {
        this.member = member;
        this.list = list;
        this.train = train;
        this.departure = departure;
        this.arrival = arrival;
        this.note = note;
    }

    //Getters and Setters
    public ArrayList<Seat> getList()
    {
        return list;
    }

    public String getSeats()
    {
        return list.toString();
    }

    public void setList(ArrayList<Seat> list)
    {
        this.list = list;
    }

    public Train getTrain()
    {
        return train;
    }

    public String getMemberMail()
    {
        return member;
    }

    public void setTicketID(String member)
    {
        this.member = member;
    }

    //@return String as a ticket details
    public String getTrainDetails()
    {
        String arc = arrival;
        String dpc = departure;
        String art = train.getArrivaltime();
        String dpt = train.getDeparturetime();
        String date = train.getDate();
        String seats = "";
        for(Seat s : list)
        {
            seats += s.getSeatNumber() + ", ";
        }
        seats.trim();
        return "\tHAVE A NICE TRAVEL!\n\tThank You For Choosing US!\nDeparture City: " + dpc + "\nArrival City: " + arc + "\nDeparture Time: " + dpt + "\nArrival Time: " + art
                + "\nDate: " + date + "\nSeats: " + seats;
    }
}
