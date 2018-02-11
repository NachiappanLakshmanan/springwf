package webflow;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Validate {
	
	
	/*
	 * validates the users input
	 * if arr size is 1 and all then prepares query accordingly
	 * if arr size is zero return an exception 
	 * else groups by a particular clause which can be anything except employee id
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
 public String Check(String s){
	    Logger logger = Logger.getLogger(Handler.class);
		logger.setLevel(Level.DEBUG);
	    logger.debug("Validating user input!");
	 
	    
	 String ar[]=s.split(",");
	 if(ar.length==0){
		 return "exception";
	 }
	 else if(ar.length==1){
		 if(ar[0].toLowerCase().equals("all")){
			 return "all";
		 }
	 }
	 else{
		  return "groupby";  
	 }
	 
	 

	return "invalid";
 }
}
