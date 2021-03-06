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
public class Receipt {
    
    protected static final String userName="scrumbagspos";
    protected static final String password="scrumbags3";
    protected String receiptBody;
    protected Calendar date;
    
    /**
     * Constructor for Receipt.
     */
    public Receipt() {
        date=GregorianCalendar.getInstance();
        receiptBody="";
    }
    
    /*
    public static void main(String[] args) {
        Sale s=new Sale();
        ProductDescription pd=new ProductDescription(1, new Money(new BigDecimal(190)), "condoms");
        ProductDescription pd1=new ProductDescription(1, new Money(new BigDecimal(75)), "blow");
        s.makeLineItem(pd, 3);
        s.makeLineItem(pd1, 1);
        Receipt r=s.makeNewReceipt();
        System.out.print(r.getReceiptBody());
    }
    */
    //sets subject and body of email
    public void makeReceiptBody(Transaction t) {
        receiptBody+="SCRUMBAGS POS RECEIPT\n";
        receiptBody+=date.getTime().toString()+"\n\n";
        receiptBody+="Transaction ID:\t"+t.getId()+"\n\n";
        receiptBody+="----------------------------------------------\n\n";
        String str=String.format("%-20s %-15s %-10s", "DESCRIPTION", "PRICE", "TOTAL");
        receiptBody+=str+"\n";
        if(t.getLineItemsLength()>0) {
            for(LineItem l: t.getLineItems()) {
                receiptBody+=l.toString()+"\n";
            }
        }
        receiptBody+="----------------------------------------------\n\n";
        receiptBody+="SUBTOTAL\t"+t.getSubtotal().toString()+"\n";
        receiptBody+="TAX     \t"+t.getTax().toString()+"\n";
        receiptBody+="TOTAL   \t"+t.getTotal().toString()+"\n";

        if(t.isCredit) {
            String cc="************"+t.getCCNum().substring(12);
            receiptBody+=cc;
        }
    }
    
    public String getReceiptBody() { return receiptBody; } 
    
    /**
     * Sends the receipt to customer(s).
     * Register will call this method.
     * @param to list of email addresses 
     * @param subject subject of email
     * @param body the body of the email
     * @return if the email went through
     */
    public static boolean emailReceipt(String to, String subject, String body) throws AddressException, MessagingException {
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
            InternetAddress ia=new InternetAddress(to);
            mess.addRecipient(Message.RecipientType.TO, ia);
            mess.setSubject(subject);
            mess.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, password);
            transport.sendMessage(mess, mess.getAllRecipients());
            transport.close();
            return true;
        }
        catch (AddressException ex) {
            throw ex;
        }
        catch (MessagingException ex) {
            throw ex;
        }
    }
}
