package com.camunda.example.CCMS;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camunda.example.CCMS.curd.curdopeartionCCSM;

@Component("checkerTask")
public class CheckerTaskend implements TaskListener {

	@Autowired
	curdopeartionCCSM cs;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		
		String checkerComment = (String)delegateTask.getVariable("checkerComment");
		
		String name = (String)delegateTask.getVariable("name");
		
		cs.setcheckercomment(checkerComment, name);
		
		String makerComment = (String)delegateTask.getVariable("makerComment");
		
		cs.setmakercomment(makerComment, name);
		
		
	}

}
