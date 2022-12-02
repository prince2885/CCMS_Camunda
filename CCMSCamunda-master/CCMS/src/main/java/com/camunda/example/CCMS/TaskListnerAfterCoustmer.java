package com.camunda.example.CCMS;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component("TaskListnerAfterCoustmer")
public class TaskListnerAfterCoustmer implements TaskListener {

	private final Logger LOGGER = Logger.getLogger(TaskListnerAfterCoustmer.class.getName());
	private static final String HOST = "smtp.gmail.com";
    private static final String USER = "saranshrayguru@gmail.com";
    private static final String PWD = "94379437";

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		//String val = (String)delegateTask.getVariableLocal("Coustmer");
		boolean coustValue = (boolean)delegateTask.getVariable("coustmer");
		String name = (String)delegateTask.getVariable("name");
		String email = (String)delegateTask.getVariable("email");
		//LOGGER.info("data:"+val);
		
		
		if(coustValue == true) {
			LOGGER.info("he is a coustmer");
			if(name != null && email != null) {
				String recipient = email;
				Properties props = new Properties();
                props.setProperty("mail.transport.protocol", "smtp");
                props.setProperty("mail.host", "smtp.gmail.com");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                props.put("mail.debug", "true");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(USER,PWD);
                            }
                        });
                
                try {


                  Transport transport = session.getTransport();
                  InternetAddress addressFrom = new InternetAddress(USER);

                  MimeMessage message = new MimeMessage(session);
                  message.setSender(addressFrom);
                  message.setSubject("your account: " + name);
                  message.setContent("has been XXXXXXXX" + "having a complain of"+"XXXXXX", "text/plain");
                  message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

                  transport.connect();
                  Transport.send(message);
                  transport.close();

                  LOGGER.info(" Email successfully sent coustmer '" + name + "' with address '" + recipient + "'.");

              } catch (Exception e) {
                  LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
              }
                
			}
			else {
				LOGGER.warning("Not sending email to user " + name + "', user has no email address.");
			}

			
			
		}
		else 
			LOGGER.info("not a coustmer");
		
	}

}
