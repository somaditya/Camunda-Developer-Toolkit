package com.somaditya.camunda;

import java.util.logging.Logger;
import java.awt.Desktop;
import java.net.URI;

import org.camunda.bpm.client.ExternalTaskClient;

public class FulfillShipment {
	private final static Logger LOGGER = Logger.getLogger(FulfillShipment.class.getName());

	public static void main(String[] args) {
		ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
				.asyncResponseTimeout(10000) // long polling timeout
				.build();

		// subscribe to an external task topic as specified in the process

		// FBA client
		client.subscribe("FBAmazon").lockDuration(1000) // the default lock duration is 20 seconds, but you can override
				// this
				.handler((externalTask, externalTaskService) -> {
					// Put your business logic here

					// Get a process variable

					LOGGER.info("Fulfilled By Amazon...");

					// Complete the task
					externalTaskService.complete(externalTask);
				}).open();

		// FBM client
		client.subscribe("FBMerchant").lockDuration(1000) // the default lock duration is 20 seconds, but you can
				// override
				// this
				.handler((externalTask, externalTaskService) -> {
					// Put your business logic here

					// Get a process variable

					LOGGER.info("Fulfilled By Merchant...");

					// Complete the task
					externalTaskService.complete(externalTask);
				}).open();
	}
}
