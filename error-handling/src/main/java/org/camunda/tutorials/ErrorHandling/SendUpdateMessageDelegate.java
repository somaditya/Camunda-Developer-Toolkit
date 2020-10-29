package org.camunda.tutorials.ErrorHandling;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendUpdateMessageDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String busKey = (String)execution.getBusinessKey();
		Long budget = (Long)execution.getVariable("cost");
		
		execution.getProcessEngineServices().getRuntimeService()
			.createMessageCorrelation("budget-increased")
			.processInstanceBusinessKey(busKey)
			.setVariable("budget", budget)
			.correlate();

	}

}
