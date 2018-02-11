package webflow;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
public class Demohandler {
  /*
   * There should be no change in the Apploication context and datasource till the end
   * 
   * 
   */
	final static ApplicationContext ctx=new ClassPathXmlApplicationContext("config2.xml");
    final static DataSource ds = (DataSource)ctx.getBean("dataSource");
   
    static Handler Obj;
	
	public Handler initFlow(){
		return new Handler();
	}
	
	
	
	
	/*
	 * It calls the Handler which is DAO  and has only one method which can query all of your inputs
	 * @input=nothing(make a call)
	 * @output=success then display the Employee details
	 * @output=Failure then prints no matching records found and and redirects to Employee details
	 * 
	 */
	
	
	
	public String display(){

		Logger logger = Logger.getLogger(Demohandler.class);
		logger.debug("Display method Called!");
		
		
		
		Obj =new Handler().display();         
		if(!Obj.getEmployees().isEmpty()){
			System.out.println("you got it");
			return "success";
		}
		return "failure";
	 }
	 /*
	  * Send Mail of all the Employee details Queried 
	  * @input=emailid,password,Employee bean list
	  * @output=send mail and also returns the object for display in JSP file
	  * 
	  * 	  */
	public Handler getobject(){
		
		Logger logger = Logger.getLogger(Demohandler.class);
		logger.setLevel(Level.DEBUG);
		logger.debug("Get Object method Called and mail will be sent Automatically!");
		
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<Obj.getEmployees().size();i++){
		     
			 sb.append(Obj.getEmployees().get(i).getName());
			 sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getEid());
		     sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getMno());
		     sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getSalary());
		     sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getArea());
		     sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getCity());
		     sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getStreet());
		     sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getState());
		     sb.append(",");
		     sb.append(Obj.getEmployees().get(i).getPin());
		     sb.append("            ");
		
		}
		System.out.println(sb.toString());
		new Mail("Employee Details").SendMail(sb.toString());
		return Obj;
	}

	
	
}