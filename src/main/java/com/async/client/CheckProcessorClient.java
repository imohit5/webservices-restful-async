package com.async.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.async.persistence.ChecksList;

public class CheckProcessorClient {
	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client
				.target("http://localhost:8080/webservices-restful-async/services/checkService/checks");

		AsyncInvoker asyncInvoker = target.request().async();
		Future<Boolean> future = asyncInvoker.post(Entity.entity(new ChecksList(), MediaType.APPLICATION_ATOM_XML),
				Boolean.class);

		try {
			System.out.print(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {

			if (e.getCause() instanceof BadRequestException) {
				BadRequestException be = (BadRequestException) e.getCause();
				System.out.println("Checks Should be provided");
			}

			e.printStackTrace();
		}
	}
}
