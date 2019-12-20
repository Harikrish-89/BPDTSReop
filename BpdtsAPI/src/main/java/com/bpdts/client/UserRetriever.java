package com.bpdts.client;

import java.io.IOException;

import com.bpdts.client.exception.InvalidResponseException;

public class UserRetriever {

	public static void main(String [] args) {
		BpdtsClient client = new BpdtsClient();
		try {
			System.out.println(client.getUsers().size());
		} catch (IOException | InterruptedException | InvalidResponseException e) {
			System.out.println("Unexpected error occured" + e.getMessage());
			e.printStackTrace();
		}
	}
}
