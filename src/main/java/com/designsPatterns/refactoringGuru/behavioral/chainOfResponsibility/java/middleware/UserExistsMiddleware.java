package com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware;

import com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.Server;

public class UserExistsMiddleware extends Middleware {
	private Server server;

	public UserExistsMiddleware(Server server) {
		this.server = server;
	}

	public boolean check(String email, String password) {
		if (!this.server.hasUser(email)) {
			System.out.println("User email not found");
			return false;
		}
		if (!this.server.isValidPassword(email, password)) {
			System.out.println("Invalid password");
			return false;
		}
		System.out.println(">> user exists and password matches, continue");
		return this.checkNext(email, password);
	}
}
