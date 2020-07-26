/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traninmanagament;

import java.util.ArrayList;

/**
 * Member class
 *
 * @author İsmet Alp EREN
 * @date 24.12.2019
 */
public class Member
{

    //Instances
    private String name;
    private String lastName;
    private String eMail;
    private String password;
    private ArrayList<Ticket> list;

    /**
     * Creates an member with the specified variables.
     */
    public Member(String name, String lastName, String eMail, String password)
    {
        this.name = name;
        this.lastName = lastName;
        this.eMail = eMail;
        this.password = password;
    }

    //Getter and Setters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String geteMail()
    {
        return eMail;
    }

    public void seteMail(String eMail)
    {
        this.eMail = eMail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public ArrayList<Ticket> getList()
    {
        return list;
    }

    public void setList(ArrayList<Ticket> list)
    {
        this.list = list;
    }

}
