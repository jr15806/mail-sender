
import com.sun.net.ssl.internal.ssl.Provider;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Afsari on 8/24/14.
 */
public class Test {
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";


/*public static void main(String args[]) throws Exception {

Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

new GoogleTest().sendSSLMessage(sendTo, emailSubjectTxt,emailMsgTxt, emailFromAddress);
System.out.println("Sucessfully Sent mail to All Users");
}*/

    public static void main(String ... args){
        try{
        //Security.addProvider(new Provider());
        new Test().sendSSLMessage("ix56@yahoo.com","test mail", "Salam Mehdi jann", "jr15806");
            System.out.println("Sucessfully Sent mail to All Users");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendSSLMessage(String recipients, String subject,
                               String message, String from) throws MessagingException {
        boolean debug = true;

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("jr15806@gmail.com", "D321Mo#@!");
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress addressTo;
        addressTo = new InternetAddress(recipients);
        msg.setRecipient(Message.RecipientType.TO, addressTo);

// Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");

        Transport.send(msg);
    }
}
