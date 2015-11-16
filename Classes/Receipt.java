package thescrumbags.Classes;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * An abstract class that e-mails a sales receipt to a customer.
 * SalesReceipt, RentalReceipt, and ReturnReceipt all extend this class.
 * @author The Scrumbags
 */
public abstract class Receipt {
    
    protected static final String userName="scrumbagspos";
    protected static final String password="scrumbags3";
    protected final String[] recipients;
    protected String receiptBody;
    protected String emailSubject;
    protected GregorianCalendar date;
    
    /**
     * Constructor for Receipt.
     * Takes in e-mail address to send receipt to.
     * @param recipients the customers' e-mail addresses
     * @param body the body of the receipt
     */
    public Receipt(String[] recipients, String emailSubject, String body) {
        this.recipients=recipients;
        this.receiptBody=body;
        this.emailSubject=emailSubject;
    }
    
    public static void main(String[] args) {
        String[] toAddress={"bsc218@lehigh.edu", "crb217@lehigh.edu", "jjn217@lehigh.edu", "rav217@lehigh.edu", "tjm217@lehigh.edu"};
        //sendReceipt(toAddress, "test receipt", "this is a test receipt... sent from Receipt class");
    }
    
    //sets subject and body of email
    public abstract void makeReceipt(Transaction t);
    
    public String[] getRecipients() { return recipients; }
    
    public String getReceiptBody() { return receiptBody; } 
 
    
    public void sendReceipt() {
        emailReceipt(recipients, emailSubject, receiptBody);
    }
    
    /**
     * Sends the receipt to customer(s).
     * Register will call this method.
     * @param to list of email addresses 
     * @param subject subject of email
     * @param body the body of the email
     */
    public static void emailReceipt(String[] to, String subject, String body) {
        Properties props=System.getProperties();
        String host="smtp.gmail.com";
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
    
        Session session = Session.getDefaultInstance(props);
        MimeMessage mess = new MimeMessage(session);
        
        try {
            mess.setFrom(new InternetAddress(userName));
            InternetAddress[] toAddress=new InternetAddress[to.length];
            for(int i=0; i < to.length; i++) {
                InternetAddress ia=new InternetAddress(to[i]);
                mess.addRecipient(Message.RecipientType.TO, ia);
            }
            mess.setSubject(subject);
            mess.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, password);
            transport.sendMessage(mess, mess.getAllRecipients());
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
