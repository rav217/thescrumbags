/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author The Scrumbags
 */
public abstract class Receipt {
    
    private static final String userName="scrumbagspos";
    private static final String password="scrumbags";
    private final String recipient;
    
    public Receipt(String recipient) {
        this.recipient=recipient;
    }
    
    public abstract void makeReceipt(Transaction t);
    
    public void sendReceipt() {
        Properties props=System.getProperties();
        String host="smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("Your scrumbags receipt");
            message.setText("");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            
        }
        catch (MessagingException mex) {
            
        }
    }
}
