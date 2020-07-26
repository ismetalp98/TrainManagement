/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import traninmanagament.JavaMail;


 /**
 * FXML Controller class
 *
 * @author Eylül Çağlar
 * @date 24.12.2019
 */
public class YourQuestionsController implements Initializable
{

    @FXML
    private JFXTextArea tf_question;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        
    }    
    
    //Send the question taken from user to company owner
    @FXML
    public void handleSend(ActionEvent evt)
    {
        new EmailSenderHelper1().start();
    }
    
    //Send Mail
    class EmailSenderHelper1 extends Thread
    {
        @Override
        public void run()
        {
            System.out.println("anan");
            JavaMail.sendMail(tf_question.getText());
        }
    }
}
