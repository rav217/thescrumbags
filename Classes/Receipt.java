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
    private static final String password="scrumbags3";
    private final String recipient;
    
    public Receipt(String recipient) {
        this.recipient=recipient;
    }
    
    public static void main(String[] args) {
        String[] toAddress={"bsc218@lehigh.edu", "crb217@lehigh.edu", "jjn217@lehigh.edu", "rav217@lehigh.edu", "tjm217@lehigh.edu"};
        sendReceipt(toAddress, "test receipt", "this is a test receipt... sent from Receipt class");
    }
    
    public abstract void makeReceipt(Transaction t);
    
    public static void sendReceipt(String[] to, String subject, String body) {
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
            message.setFrom(new InternetAddress(userName));
            InternetAddress[] toAddress=new InternetAddress[to.length];
            for(int i=0; i < to.length; i++) {
                InternetAddress ia=new InternetAddress(to[i]);
                message.addRecipient(Message.RecipientType.TO, ia);
            }
            
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException a) {
            a.printStackTrace();
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
