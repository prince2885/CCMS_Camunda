package com.camunda.example.CCMS;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.camunda.example.CCMS.curd.curdopeartionCCSM;




@Component("maker")
public class makerFunctionality implements JavaDelegate {
	
	
	@Autowired
	curdopeartionCCSM ccsm;
	fluxdata x,y,z,a;
	String name_present;

	Map<String, Object> variables = new HashMap<String,Object>();
	private final Logger LOGGER = Logger.getLogger(makerFunctionality.class.getName());
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String courtid =(String) execution.getVariable("courtid");
		String name = (String) execution.getVariable("name");
		String adharno = (String) execution.getVariable("adharno");
		String email = (String)execution.getVariable("email");
		
		variables = execution.getVariables();
		LOGGER.info(variables.toString());
		//x = ccsm.getOne("saransh");
		//x.getAdharno();
		//ccsm.up
		
		if(courtid != null  && name != null && adharno != null && email != null) {
			 x = ccsm.findBycourtid(courtid);
			 y = ccsm.findByname(name);
			 z = ccsm.findByadharno(adharno);
			 a = ccsm.findByemail(email);
			 if(x.equals(y) && y.equals(z) && z.equals(x) && z.equals(a) ) {
				 		execution.setVariable("yes", "YES");
					//LOGGER.info(x.getName());
					name_present = "true";
					execution.setVariableLocal("Coustmer", name_present);
			 }
			 
			  	else {
			  		execution.setVariable("yes", "NO");
					name_present = "false";
					execution.setVariableLocal("Coustmer", name_present);
				   }
			 
			
		}
		/*if(name != null){
			 y = ccsm.findByname(name);
			execution.setVariable("yes", "ok");
			LOGGER.info(y.getName());
			
		}
		if(adharno != null) {
			 z = ccsm.findByadharno(adharno);
			execution.setVariable("yes", "YES");
			LOGGER.info(z.getName());
		}*/
		
		//LOGGER.info(x.getName());
		
		
	}
	
//Tocheck::: Creat rest call from Delegate	
/*@RequestMapping("/getVariables")
	public Map<String, Object> getVariables(){
	
	LOGGER.info("data:::::"+variables.toString());
		//variables =;
		return variables;
	}
	
	
	@RequestMapping("/CheckCoust")
	public boolean check() {
		name_present = true;
		return name_present;
	}*/
}
