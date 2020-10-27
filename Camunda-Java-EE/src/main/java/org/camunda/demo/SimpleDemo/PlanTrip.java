package org.camunda.demo.SimpleDemo;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * This is an easy adapter implementation 
 * illustrating how a Java Delegate can be used 
 * from within a BPMN 2.0 Service Task.
 */
public class PlanTrip implements JavaDelegate {
 
  private final Logger LOGGER = Logger.getLogger(PlanTrip.class.getName());
  
  public void execute(DelegateExecution execution) throws Exception {
    
    LOGGER.info("\n\n  ... LoggerDelegate invoked by "
            + "processDefinitionId=" + execution.getProcessDefinitionId()
            + ", activtyId=" + execution.getCurrentActivityId()
            + ", activtyName='" + execution.getCurrentActivityName() + "'"
            + ", processInstanceId=" + execution.getProcessInstanceId()
            + ", businessKey=" + execution.getProcessBusinessKey()
            + ", executionId=" + execution.getId()
            // print process variables
            + "\n*************************************************"
            + "\nName: " + execution.getVariable("name")
            + "\nTemp: " + execution.getVariable("temp") + "°C"
            + "\nCount: " + execution.getVariable("count")
            + "\n*************************************************"
            + "\nHurray! Let's go for picnic."
            + " \n\n");
    
  }

}
