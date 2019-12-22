package com.bpdts.util;

import java.util.ArrayList;
import java.util.List;

import com.bpdts.client.model.User;

public class TestUtil {
	
	public static List<User> getUsersList() {
		List<User> users = new ArrayList<User>();
		User userOne = new User();
		userOne.setId(1L);
		userOne.setEmail("test@email.com");
		users.add(userOne);
		return users;
	}

}
