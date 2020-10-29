package org.camunda.demo.fulfilment.messaging;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RequestL2 implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String ticketDesc = (String) execution.getVariable("ticketDesc");
		
		execution.getProcessEngineServices().getRuntimeService()
			.createMessageCorrelation("RequestL2")
			.setVariable("ticketDesc", ticketDesc)
			.correlate();

	}

}
