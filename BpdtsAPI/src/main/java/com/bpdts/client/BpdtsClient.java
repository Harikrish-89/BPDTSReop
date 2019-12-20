package com.bpdts.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bpdts.client.exception.InvalidResponseException;
import com.bpdts.client.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BpdtsClient {
	private static final String USERS_PATH = "/users";
	private static final String CITY_PATH = "city/";
	private static final String BASE_URL = "https://bpdts-test-app.herokuapp.com/";

	public List<User> getUsersByCity(final String city)
			throws IOException, InterruptedException, InvalidResponseException {
		return getUsersFromApi(CITY_PATH + city + USERS_PATH);
	}

	public List<User> getUsers() throws IOException, InterruptedException, InvalidResponseException {
		return getUsersFromApi(USERS_PATH);
	}

	private List<User> getUsersFromApi(final String path)
			throws IOException, InterruptedException, InvalidResponseException {
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest usersRequest = HttpRequest.newBuilder().uri(URI.create(BASE_URL + path))
				.timeout(Duration.ofMinutes(1)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = client.send(usersRequest, BodyHandlers.ofString());
		if (response.statusCode() != 200) {
			throw new InvalidResponseException("Invalid request");
		}
		return Arrays.asList(new ObjectMapper().readValue(response.body(), User[].class));
	}
}
