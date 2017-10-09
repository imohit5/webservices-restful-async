package com.async.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.async.persistence.Check;
import com.async.persistence.ChecksList;

public class CheckProcessorClientTwo {
	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client
				.target("http://localhost:8080/webservices-restful-async/services/checkService/checks");

		AsyncInvoker asyncInvoker = target.request().async();
		
		ChecksList checksList = new ChecksList();
		List<Check> checks = new ArrayList<>();
		Check check1 = new Check();
		check1.setAccountNumber("1234");
		check1.setAmount(1234D);;
		check1.setCheckNumber("1234");;
		
		checks.add(check1);
		
		checksList.setChecks(checks);
		
		Future<Boolean> future = asyncInvoker.post(Entity.entity(checksList, MediaType.APPLICATION_ATOM_XML),
				new ChecksResponseCallbackHandler());
		
		
		
		
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}
}
