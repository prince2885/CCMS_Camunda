package com.camunda.example.CCMS;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("ValueUpdate")
public class coustmerValueUpdate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		boolean coustm = (boolean)execution.getVariable("coustmer");
		
		if(coustm == true) {
			execution.setVariable("yes", "YES");
		}
		else {
			execution.setVariable("yes", "NO");
		}
		
	}

}
