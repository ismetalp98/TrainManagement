/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traninmanagament;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * This class send tickets or any other message as a mail to users 
 * @author Kerem
 * @date 24.12.2019
 */
public class JavaMail
{
    /*
     * sendMail() send tickets as a mail to users 
     */

    public static void sendMail(Ticket ticket)
    {
        try
        {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();

            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myEmail = "trainmanagementtest@gmail.com";
            String password = "alemdarla1";

            Session session = Session.getInstance(properties, new Authenticator()
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(myEmail, password);
                }
            });
            String recepient = ticket.getMemberMail();
            String ticketDetails = ticket.getTrainDetails();
            Message message = prepareMessage(session, myEmail, recepient, ticketDetails);
            Transport.send(message);
            System.out.println("Message sent succesfully");
        }
        catch(MessagingException ex)
        {
            System.out.println("Mail couldn't send");
        }

    }

    /*
     * sendMail() send any message as a mail to users 
     */
    public static void sendMail(String survey)
    {
        try
        {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();

            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myEmail = "trainmanagementtest@gmail.com";
            String password = "alemdarla1";

            Session session = Session.getInstance(properties, new Authenticator()
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(myEmail, password);
                }
            });
            String recepient = "trainmanagementtest@gmail.com";
            Message message = prepareMessage(session, myEmail, recepient, survey);
            Transport.send(message);
            System.out.println("Message sent succesfully");
        }
        catch(MessagingException ex)
        {
            System.out.println("Mail couldn't send");
        }

    }

    /*
     *Prepare the message and send mail to indicated user
     */
    private static Message prepareMessage(Session session, String myEmail, String recepient, String ticketDetails)
    {
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Your Ticket");
            message.setText(ticketDetails);
            return message;
        }
        catch(Exception ex)
        {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
