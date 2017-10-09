package com.async.service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import com.async.persistence.ChecksList;

public class CheckProcessorImpl {
	
	@POST
	@Path("/checks")
	public void processChecks(@Suspended AsyncResponse asyncResponse, ChecksList checks) {
		new Thread() {
			@Override
			public void run() {
				
				if(checks.getChecks() == null || checks.getChecks().size() == 0) {
					asyncResponse.resume(new BadRequestException());
				}
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				asyncResponse.resume(true);
			}
		}.start();
	}

}