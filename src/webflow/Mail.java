package webflow;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;




	public class Mail extends HttpServlet {


		
		String sub;
		private static final long serialVersionUID = 1L;
        Mail(String sub){
        	this.sub=sub;
        }
     
		/*
		 * 
		 * Authentication set to true
		 * Socket created
		 * subject from to message are added
		 * mime message is create using session object
		 * object for transport is created and simple mail transfer protocol is used to transferr
		 * the message to the respective mail server
		 * here it is gmail
		 * 
		 * 
		 * 
		 */
		
		public void SendMail(String message){
		
			
			
		
			Properties prop=System.getProperties();
			
			
			
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "465");
			prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			prop.put("mail.smtp.socketFactory.port","465");
			prop.put("mail.smtp.socketFactory.fallback","false");
			
			
			
			Session session=Session.getDefaultInstance(prop,null);
			
			
			Message mail=new MimeMessage(session);
			try{
				
				
				
				
						mail.setFrom(new InternetAddress("nachu.l.1204@gmail.com"));
						mail.setRecipient(Message.RecipientType.TO,new InternetAddress("nachu.l.1204@gmail.com"));
				
						mail.setSubject(sub);
						mail.setContent(message,"text/html");
						
						Transport trans=session.getTransport("smtp");
						
					
						trans.connect("smtp.gmail.com","nachu.l.1204@gmail.com","dummy");
						trans.sendMessage(mail, mail.getAllRecipients());
						System.out.println("Message Sent Successfully");
					}
					catch(Exception e){
						e.printStackTrace();
					}
			
			
		}
		
		

	}

