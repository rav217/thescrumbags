package thescrumbags.Classes;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.math.BigDecimal;

/**
 * An abstract class that e-mails a sales receipt to a customer.
 * SalesReceipt, RentalReceipt, and ReturnReceipt all extend this class.
 * @author The Scrumbags
 */
public abstract class Receipt {
    
    protected static final String userName="scrumbagspos";
    protected static final String password="scrumbags3";
    protected String receiptBody;
    protected GregorianCalendar date;
    
    public static void main(String[] args) {
        Sale s=new Sale();
        ProductDescription pd=new ProductDescription(1, new Money(new BigDecimal(190)), "test");
        LineItem l=new LineItem(pd, 3);
        s.makeLineItem(l);
        Receipt r=s.makeNewReceipt();
        System.out.print(r.getReceiptBody());
    }
    
    /**
     * Constructor for Receipt.
     */
    public Receipt() {
        date=new GregorianCalendar();
        receiptBody="";
    }
    
    //sets subject and body of email
    public abstract void makeReceiptBody(Transaction t);
    
    public String getReceiptBody() { return receiptBody; } 
    
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
