package org.camunda.tutorials.ErrorHandling;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class IncreaseBugetDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long budget = (Long)execution.getVariable("budget");
		
		// 500 million
		budget = budget + 500000000L;
		
		execution.setVariable("budget", budget);
		

	}

}
