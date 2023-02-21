package co.com.sendemailapi.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private final String username = "emailEmisor@gmail.com";
	private final String password = "claveEmailEmisor";
	private final String recipient = "emailDestino@gmail.com";
    
    
   	public void sendEmail() throws IOException {
    	   //Configura las propiedades del servidor SMTP de gmail
           Properties props = new Properties();
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.enable", "true");
           props.put("mail.smtp.host", "smtp.gmail.com");
           props.put("mail.smtp.port", "587");
        
           //Crea una sesión de correo electrónico autenticada
           Session session = Session.getInstance(props, new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
               }
           });
        
        try {
            // Crea un mensaje de correo electrónico
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Correo de prueba desde Spring");
            
            BodyPart messageBodyPart = new MimeBodyPart(); 
            messageBodyPart.setText("Hola,\n\n Este es un correo de prueba enviado desde una aplicación java Spring.");
                        
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File("archives/xxx.png"));
            attachmentPart.attachFile(new File("archives/kamasutra.pdf"));
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
           
            message.setContent(multipart);
            
            Transport.send(message);

            System.out.println("El correo ha sido enviado correctamente.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
