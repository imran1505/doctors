package com.utils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	final static String senderEmailID = "md.ibrahim1508@gmail.com";
	final static String senderPassword = "purnea243583";
	final static String emailSMTPserver = "smtp.gmail.com";
	final static String emailServerPort = "465";
	final static String emailBody ="email Body";
	final static String emailSubject ="User Verification ";
	final static String emailSubject2 ="Password Recovery ";
	final static String emailSubjectInvitaion="Invite to join Contact Backup";
	

	public MailSender(String receiverEmailID, String emailSubject,
			String emailBody) {

		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");

		SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			// session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(emailBody);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					receiverEmailID));
			Transport.send(msg);
		} catch (Exception mex) {
			mex.printStackTrace();
		}

	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmailID, senderPassword);
		}
	}

//	public static void main(String[] args) {
//		MailSender mailSender = new MailSender("imransunday@gmail.com",
//				"Test Mail ajd", "Here goes the body");
//	}

	public static String sendRecoveryMail(String receiverEmailID,String type) {

		String verificationCode=null;
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");

		SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			String randomString= generateRandomCode();
			BigInteger uid = new BigInteger(receiverEmailID.getBytes());
			InetAddress addy = InetAddress.getLocalHost();
			String remoteIp = addy.getHostAddress();
			String html = "<a href='http://"+remoteIp+":8080/Doctor/recover.html?v="+randomString+"&uid="+uid + "&type=" +type +"'>Click here to recover yor password</a>";
			msg.setText(html, "UTF-8", "html");
			msg.setSubject(emailSubject2);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailID));
			Transport.send(msg);
			verificationCode=randomString;
		} catch (Exception mex) {
			mex.printStackTrace();
			verificationCode="ERROR";
			return verificationCode;
		}

	
		return verificationCode;
	}
	
	
	public static String generateRandomCode(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		System.out.println("random String generated"+output);
		return output;
	}

	
	public static void main(String[] args) {
		// test string
		String text = "imransunday@gmail.com";
		System.out.println("Test string = " + text);

		// convert to big integer
		BigInteger bigInt = new BigInteger(text.getBytes());
		String s = bigInt.toString();
		System.out.println(bigInt.toString());
		
		BigInteger number = new BigInteger(s);
		System.out.println(number);

		// convert back
		String textBack = new String(number.toByteArray());
		System.out.println("And back = " + textBack);
	}


	public static boolean sendInvitaion(String email, String username,String name) {


		boolean isMailSent=false;
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");

		SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			String html="<h2>Hi "+email+"</h2>,<br><br>";
			html+= "Your friend "+name.toUpperCase()+" has sent you invitation to try Contact Backup.<br><br>";
			html+= "<a href='http://localhost:8080/Doctor/home.jsp?'>CONTACT BACKUP</a>";
			msg.setText(html, "UTF-8", "html");
			msg.setSubject(emailSubjectInvitaion);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			Transport.send(msg);
			isMailSent=true;
		} catch (Exception mex) {
			mex.printStackTrace();
			isMailSent=false;
			return isMailSent;
		}

	
		return isMailSent;
	
	}
	
	public static String  sendVerificationMail(String receiverEmailID){


		String verificationCode=null;
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			String randomString= generateRandomCode();
			BigInteger uid = new BigInteger(receiverEmailID.getBytes());
			//System.out.println(bigInt.toString());
			InetAddress addy = InetAddress.getLocalHost();
			String remoteIp = addy.getHostAddress();
			System.out.println("remote ip:"+remoteIp);
			String html = "<a href='http://"+remoteIp+":8080/Doctor/verify.html?v="+randomString+"&uid="+uid+"'>Click here to activate your account</a>";
			msg.setText(html, "UTF-8", "html");
			//msg.setText(emailBody+ " " +randomString);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailID));
			Transport.send(msg);
			verificationCode=randomString;
		} catch (Exception mex) {
			mex.printStackTrace();
			verificationCode="ERROR";
			return verificationCode;
		}
		return verificationCode;
	
	}
}
