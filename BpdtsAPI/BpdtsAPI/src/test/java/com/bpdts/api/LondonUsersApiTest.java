package com.bpdts.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bpdts.client.BpdtsClient;
import com.bpdts.client.exception.InvalidResponseException;
import com.bpdts.util.TestUtil;

public class LondonUsersApiTest {

	@Mock
	private BpdtsClient mockClient;

	@InjectMocks
	private LondonUsersApi testee;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSuccessfulRetrievalOfUsers() throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.getUsersByCity(anyString())).thenReturn(TestUtil.getUsersList());
		assertThat(testee.getLondonUsers().size(), is(1));
		assertThat(testee.getLondonUsers().get(0).getId(), is(1L));
	}

	@Test
	public void testFailureRetrievalOfUsers() throws IOException, InterruptedException, InvalidResponseException {
		when(mockClient.getUsersByCity(anyString())).thenThrow(new InvalidResponseException("test exception"));
		assertThrows(InvalidResponseException.class, () -> {
			testee.getLondonUsers();
		});
	}
}
