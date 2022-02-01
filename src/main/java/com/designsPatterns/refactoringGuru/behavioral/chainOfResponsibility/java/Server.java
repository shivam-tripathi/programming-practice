package com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java;

import java.util.HashMap;
import java.util.Map;

import com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware.Middleware;

import lombok.Setter;

public class Server {
	Map<String, String> users = new HashMap<>();
	@Setter
	private Middleware middleware;

	public boolean hasUser(String email) {
		return users.containsKey(email);
	}

	public boolean isValidPassword(String email, String password) {
		return password != null && password.equals(this.users.get(email));
	}

	public void register(String email, String password) {
		if (this.users.containsKey(email)) {
			throw new Error("User already exists");
		}
		this.users.put(email, password);
	}

	public boolean login(String email, String password) {
		if (middleware.check(email, password)) {
			System.out.printf("Login successful for %s with password \"%s\"\n", email, password);
			return true;
		}
		System.out.printf("Login failed for %s with password \"%s\"\n", email, password);
		return false;
	}
}
