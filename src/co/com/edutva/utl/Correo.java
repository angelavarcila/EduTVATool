package co.com.edutva.utl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class Correo {
	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	public Correo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean enviarMensaje(String destinatario, String mensaje, String asunto){
		try {	
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"labtdiunicauca",
									"correo_labtdi");
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("labtdiunicauca"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject(asunto);
			message.setText(mensaje);

			Transport.send(message);			
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
			return false;
		}
		return true;
	}
	
}
