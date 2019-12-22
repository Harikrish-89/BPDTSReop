package com.bpdts.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bpdts.client.exception.InvalidResponseException;
import com.bpdts.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BpdtsClientTest {

	@Mock
	private HttpClient mockClient;

	@InjectMocks
	private BpdtsClient testee;

	@SuppressWarnings("rawtypes")
	@Mock
	private HttpResponse mockResponse;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsers() throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenReturn(mockResponse);
		when(mockResponse.body()).thenReturn(new ObjectMapper().writeValueAsString(TestUtil.getUsersList()));
		when(mockResponse.statusCode()).thenReturn(200);
		assertThat(testee.getUsers().size(), is(1));
		assertThat(testee.getUsers().get(0).getId(), is(1L));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsersThrowsIOException() throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenThrow(new IOException("test"));
		assertThrows(IOException.class, () -> {
			testee.getUsers();
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsersThrowsExceptionWithInvalidResponse()
			throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenReturn(mockResponse);
		when(mockResponse.statusCode()).thenReturn(404);
		assertThrows(InvalidResponseException.class, () -> {
			testee.getUsers();
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsersByCity() throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenReturn(mockResponse);
		when(mockResponse.body()).thenReturn(new ObjectMapper().writeValueAsString(TestUtil.getUsersList()));
		when(mockResponse.statusCode()).thenReturn(200);
		assertThat(testee.getUsersByCity("London").size(), is(1));
		assertThat(testee.getUsersByCity("London").get(0).getId(), is(1L));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsersByCityThrowsIOException()
			throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenThrow(new IOException("test"));
		assertThrows(IOException.class, () -> {
			testee.getUsersByCity("London");
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsersByCityThrowsExceptionWithInvalidResponse()
			throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenReturn(mockResponse);
		when(mockResponse.statusCode()).thenReturn(404);
		assertThrows(InvalidResponseException.class, () -> {
			testee.getUsersByCity("London");
		});
	}

	@Test
	public void testEmptyConstructor() throws IOException, InterruptedException, InvalidResponseException {
		assertNotNull(new BpdtsClient());
	}
}
