package com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware;

public class CheckUserRoleMiddleware extends Middleware {
	public boolean check(String email, String password) {
		if (email.equals("admin@design.com")) {
			System.out.println(">> admin account");
			return true;
		}
		System.out.println(">> regular account");
		return this.checkNext(email, password);
	}
}
