const { Variables } = require("camunda-external-task-client-js");
const { Client, logger } = require("camunda-external-task-client-js");

// configuration for the Client:
//  - 'baseUrl': url to the Workflow Engine
//  - 'logger': utility to automatically log important events
const config = { baseUrl: "http://localhost:8080/engine-rest", use: logger };

// create a Client instance with custom configuration
const client = new Client(config);

// susbscribe to the topic: 'creditScoreChecker'
client.subscribe("TypeOfShipping", async function({ task, taskService }) {
  // Put your business logic
  // complete the task
  var fba = true;

  const processVariables = new Variables();
  processVariables.set("fba", fba);

  await taskService.complete(task, processVariables);
});