package webflow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service
@EnableScheduling
public class Handler implements Serializable{


	private static final long serialVersionUID = 3251465729028633190L;
	static String location;

	/*
	 * return the string that has been got from user as an input
	 * @output=returns user input(String)
	 * 
	 */
	public String getLocation() {
		return location;
		
	}
	/*
	 * setter method the user input
	 * @input=string as input from user
	 */

	public void setLocation(String location) {
		
		this.location = location;
	}
	private List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
	
/*
 * return list of employee objects
 * @output=list of employee objects
 * 
 */
	public List<EmployeeBean> getEmployees() {
		return employees;
	}

	/*
	 * set list of employee objects
	 * @input list
	 * @output doesnt retuen anything
	 * 
	 */
	public void setEmployees(List<EmployeeBean> employees) {
		this.employees = employees;
	}
	
	/*
	 * A scheduler that runs every month first day
	 * takes all as input and sends different mails
	 *for each of the available locations in the databse 
	 * 
	 * 
	 */
	@Scheduled(fixedRate = 100000)
	@Scheduled(cron="0 0 0 1 1/1 *")
	public void Schedule(){
		System.out.println("Handleer called");
		location="all";
		Handler Object=display();
		
       
        
        Map<String,List<String>> map=new HashMap<>();
		
		for(int i=0;i<Object.getEmployees().size();i++){
			     StringBuilder sb=new StringBuilder();
			     
			     sb.append(Object.getEmployees().get(i).getName());
			     sb.append(Object.getEmployees().get(i).getEid());
			     sb.append(",");
			     sb.append(Object.getEmployees().get(i).getMno());
			     sb.append(",");
			     sb.append(Object.getEmployees().get(i).getSalary());
			     sb.append(",");
			     sb.append(Object.getEmployees().get(i).getArea());
			     sb.append(",");
			     sb.append(Object.getEmployees().get(i).getCity());
			     sb.append(",");
			     sb.append(Object.getEmployees().get(i).getStreet());
			     sb.append(",");
			     sb.append(Object.getEmployees().get(i).getState());
			     sb.append(",");
			     sb.append(Object.getEmployees().get(i).getPin());
			     
			     String a=Object.getEmployees().get(i).getArea();
			     
			     if(map.containsKey(a)){
			    	       
			              map.get(a).add(sb.toString());
			     }else{
			    	 List<String> list=new ArrayList<>();
			    	 list.add(sb.toString());
			    	 map.put(a,list);
			     }
			 
		
		}
		
		for(String ch:map.keySet()){
			  StringBuilder sb=new StringBuilder();
			  for(String c:map.get(ch)){
				  sb.append(c);
				  sb.append("             ");
			  }
			  System.out.println(sb.toString());
		      new Mail(ch).SendMail(sb.toString());
		}
	}
	
	
	/*
	 * 
	 * It does all the database connection and querying returns an object
	 * @input=input from the user
	 * @output=return an object of Employeebean list
	 * 
	 * 
	 */
	
	
	
	public Handler display(){

		Logger logger = Logger.getLogger(Handler.class);
		logger.setLevel(Level.ALL);
		System.out.println(location);
		
		logger.debug("Display Method called Which prepares Queries!");
		
		
		String s=location;
		
		String st=new Validate().Check(s);
		String groupby="";
		
		Handler Obj = new Handler();;
		String sql="";

		int flag=0;
		String ar[]=s.split(",");
		
		StringBuilder sb=new StringBuilder();
		
		if(st.equals("groupby")){
			groupby=ar[0];
			
			for(int i=1;i<ar.length;i++){
				sb.append("'");
				sb.append(ar[i]);
				sb.append("'");
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			String q=sb.toString();
			flag=1;
		    sql = "select name,user.eid,mno,salary,street,area,city,state,pin  from user inner join location  where  user.eid=location.eid  and "+groupby+" in ("+q+")";
		}
		else if(st.equals("all")){
			flag=1;
			sql="select name,user.eid,mno,salary,street,area,city,state,pin  from user inner join location  where  user.eid=location.eid ";
		}
		
		
		if(flag==1){
	    try{new Demohandler().ds.getConnection().setAutoCommit(false);}catch(Exception e){}
		    
	        JdbcTemplate jdbc=new JdbcTemplate(new Demohandler().ds);
	        List<EmployeeBean> employees  = jdbc.query(sql,	new BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class));
		    
		    Obj.setEmployees(employees);
		
		    return Obj;
		
	    }
		return Obj;
	}

	
}
