package com.async.client;

import javax.ws.rs.client.InvocationCallback;

public class ChecksResponseCallbackHandler implements InvocationCallback<Boolean> {

	@Override
	public void completed(Boolean response) {
		System.out.println("Success" + response);
	}

	@Override
	public void failed(Throwable throwable) {
		throwable.printStackTrace();
	}

}
