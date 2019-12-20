package com.bpdts.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bpdts.client.BpdtsClient;
import com.bpdts.client.exception.InvalidResponseException;
import com.bpdts.client.model.User;

@RestController
@RequestMapping(path = "/")

public class LondonUsersApi {

	private final BpdtsClient client;

	public LondonUsersApi(BpdtsClient client) {
		this.client = client;
	}

	@RequestMapping(path = "/londonUsers", method = RequestMethod.GET, produces = "application/json")
	public List<User> getLondonUsers() throws IOException, InterruptedException, InvalidResponseException {
		return client.getUsersByCity("London");
	}
}
