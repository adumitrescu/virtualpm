package com.vm.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * E-mail service for handling mail sending from application
 * 
 * @author adumitrescu
 *
 */
public class EmailServices {

	private static final String ADDRESS = "alligator.notifier@gmail.com";
	private static final String PASSWORD = "kovingBroj1";

	private static final String FROM_ADDRESS = "portal@alleview.com";
	private static final String FROM_MASK = "ALLeVIEW Support";

	public EmailServices() {

	}

	/**
	 * Send plain email
	 * 
	 * @param to
	 * @param replyTo
	 * @param subject
	 * @param bodyText
	 * @throws MessagingException
	 */
	public static void sendEmail(String to, String replyTo, String subject, String bodyText, boolean isHTML)
			throws MessagingException {


		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", 587);


		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(/* "","" */
						ADDRESS, PASSWORD);
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(FROM_ADDRESS, FROM_MASK));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			if (isHTML) {
				message.setContent(bodyText, "text/html; charset=utf-8");
			} else {
				message.setText(bodyText, "UTF-8");
			}

			// reply-to my email if exists
			if (replyTo != null) {
				message.setReplyTo(new javax.mail.Address[] { new javax.mail.internet.InternetAddress(replyTo) });
			}
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully to: " + to);
			// Notification.show(ViewUtils.getString("MESSAGE_SENT"));

		} catch (UnsupportedEncodingException uee) {
			System.out.println("Unsupported encoding exception: " + to);
		} catch (SendFailedException sfe) {
			System.out.println("Sending message failed to: " + to);
			throw sfe;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw mex;
		}

	}

	/**
	 * Utility method to send e-mail with attachment.
	 * 
	 * @param to
	 * @param replayTo
	 * @param subject
	 * @param bodyText
	 * @param isHTML
	 * @param attachment
	 * @param attachmentFileName
	 * @throws Exception
	 */
	public static void sendEmail(String to, String replayTo, String subject, String bodyText, boolean isHTML,
			byte[] attachment, String attachmentFileName) throws Exception {

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ADDRESS, PASSWORD);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_ADDRESS));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			if (isHTML) {
				messageBodyPart.setContent(bodyText, "text/html; charset=utf-8");
			} else {
				messageBodyPart.setText(bodyText);
			}
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			BodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new ByteArrayDataSource(attachment, "application/pdf");
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(attachmentFileName);
			multipart.addBodyPart(attachmentBodyPart);
			message.setContent(multipart);
			if (replayTo != null) {
				message.setReplyTo(new javax.mail.Address[] { new javax.mail.internet.InternetAddress(replayTo) });
			}
			Transport.send(message);
			System.out.println("Sent message successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
