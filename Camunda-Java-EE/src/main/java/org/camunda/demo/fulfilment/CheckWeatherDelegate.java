package org.camunda.demo.fulfilment;

import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckWeatherDelegate implements JavaDelegate {
	
	static int count = 0;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.setVariable("name", "Somaditya Basak");
		execution.setVariable("count", ++count);
		
	}

}
