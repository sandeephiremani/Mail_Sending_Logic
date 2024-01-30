package com.gmailsend;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String message = " This is mail is to check the security";
		String subject = " Confirmation";
		String to = "example@gmail.com";
		String from = "example@gmail.com";

//		sendmail(message,subject,to,from);
		sendAttachment(message, subject, to, from);
	}

//		This method is to send the Attachment with message
	private static void sendAttachment(String message, String subject, String to, String from) {
			
//		variable for gmail
		String host = "smtp.gmail.com";
		
//		Get the parameters propreties
		Properties properties = System.getProperties();
		
		
//		Setting the parametars
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		
//		Setp 1: Get the session
		
		Session session = Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("deppuv00@gmail.com", "xxxxxxxxx") ;
			}
		});
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		
		try {
			
			m.setFrom(from);
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
//			filepath
			String pathfile = "C:\\Users\\rs0095\\Pictures\\Screenshots\\Screenshot (4).png";
			
//		MimeMulti partfile we have option to set both message and attachment in one file
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
//			text
//			file
			
			MimeBodyPart textmime = new MimeBodyPart();
			
			MimeBodyPart fileMime = new MimeBodyPart();
			
			try {
				textmime.setText(message);
				
				File f = new File(pathfile);
				fileMime.attachFile(f);
				
				
				mimeMultipart.addBodyPart(textmime);
				mimeMultipart.addBodyPart(fileMime);
				
				m.setContent(mimeMultipart);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			Transport.send(m);
			System.out.println("Message sent successfully..");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

//	This is responsible for to send mail method only for message
	private static void sendmail(String message, String subject, String to, String from) {

//		variable for gamil
		String host = "smtp.gmail.com";

//		Get the system parametrs

		Properties properties = System.getProperties();
		System.out.println("Properties : " + properties);

//		setting importatant information to properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//		step 1 :get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("deppuv00@gmail.com", "hyfzqsqkukebyinw");
			}
		});
		session.setDebug(true);

//		Step 2 : compose the message [text,multi media]
		MimeMessage mimeMessage = new MimeMessage(session);

		try {
//			from the mail
			mimeMessage.setFrom(new InternetAddress(from));

//			adding recipicnt
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//			adding subject to subject
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);

//			send the message using transport

			Transport.send(mimeMessage);
			System.out.println("Send success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
