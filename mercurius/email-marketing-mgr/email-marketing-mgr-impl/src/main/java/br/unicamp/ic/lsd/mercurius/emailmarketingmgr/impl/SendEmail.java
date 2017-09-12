package br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

public class SendEmail {

	private class SMTPAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
        	// TODO Change "user@email.com", "password" by an actual user and password
        	// from gmail in order to authenticate
            return new PasswordAuthentication("user@gmail.com", "password");
        }
    }

	public void sendEmail(List<Customer> recipientsList, List<Product> products) throws MessagingException {

        // Get system properties
        Properties props = System.getProperties();
        props = new Properties();
        	// TODO Set gmail user email below
            props.put("mail.smtp.user", "user@gmail.com");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");


        SMTPAuthenticator auth = new SMTPAuthenticator();
        
        Session session = Session.getInstance(props, auth);
        	session.setDebug(false);

        MimeMessage msg = new MimeMessage(session);
        	// TODO Change "from@email.com" by the actual sender email
        	msg.setFrom(new InternetAddress("from@email.com"));
        	// TODO Change "recipient@email.com" by the actual recipient email
        	msg.addRecipient(Message.RecipientType.TO, new InternetAddress("recipient@email.com"));
        	// TODO Subject
        	msg.setSubject("This is a subject");
        	// TODO Format text. By default, it's send to send an email with a list
        	// of customers and a set of products with description
        	String text = new String("The clients are: ");
        	for(Customer recipient: recipientsList)
        		text += recipient.getEmailAddress() + " ";
        	text += "\nThe products are:\n";
        	for(Product product: products)
        		text += product.getName() + ": " + product.getDetails() + "\n";
        	// Sends text
        	msg.setText(text);
            
            
            Transport transport = session.getTransport("smtps");
            // TODO Set email and password same as in authentication
            transport.connect("smtp.gmail.com", 465, "user@gmail.com", "password");
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();  

   }
}