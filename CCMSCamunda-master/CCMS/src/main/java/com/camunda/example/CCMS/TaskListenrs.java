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
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;
import org.springframework.stereotype.Component;

@Component("Task")
public class TaskListenrs implements TaskListener {

	//mail_send to the user who_ever claim the Task
	private final Logger LOGGER = Logger.getLogger(TaskListenrs.class.getName());
	 private static final String HOST = "smtp.gmail.com";
	    private static final String USER = "saranshrayguru@gmail.com";
	    private static final String PWD = "94379437";
	@Override
	
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		
		//d
		
		
		String TaskId = delegateTask.getId();
		String Assigned_user = delegateTask.getAssignee();
		String TaskName = delegateTask.getName();
		LOGGER.info("The Task "+TaskName+" having Id "+TaskId+" Assigned to "+Assigned_user);
		//hdbewuidenio
		
		if(Assigned_user != null) {
			
			//get User Info>>
			IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
			User user = identityService.createUserQuery().userId(Assigned_user).singleResult();
			//Group g =identityService.createGroupQuery().groupName("MAKER").singleResult();
		
			
			
			if(user != null) {
				//Setting Email Server
				String recipient = user.getEmail();
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
                  message.setSubject("Task assigned: " + delegateTask.getName());
                  message.setContent("Please complete: http://localhost:8080/camunda/app/tasklist/default/#/task=" + TaskId, "text/plain");
                  message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

                  transport.connect();
                  Transport.send(message);
                  transport.close();

                  LOGGER.info("Task Assignment Email successfully sent to user '" + Assigned_user + "' with address '" + recipient + "'.");

              } catch (Exception e) {
                  LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
              }
                
			}
			else {
				LOGGER.warning("Not sending email to user " + Assigned_user + "', user has no email address.");
			}
			
		}
		else {
			LOGGER.warning("Not sending email to user " + Assigned_user + "', user is not enrolled with identity service.");
		}
		
	}

}
