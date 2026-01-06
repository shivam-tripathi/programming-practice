package com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware;

public class ThrottlingMiddleware extends Middleware {
	private int requestPerMinute;
	private int request;
	private long currentTime;

	public ThrottlingMiddleware(int requestPerMinute) {
		this.requestPerMinute = requestPerMinute;
		this.currentTime = System.currentTimeMillis();
	}

	public boolean check(String email, String password) {
		if (System.currentTimeMillis() > currentTime + 60_000) {
			this.request = 0;
			this.currentTime = System.currentTimeMillis();
		}

		this.request++;

		if (this.request > this.requestPerMinute) {
			System.out.println("request limit exceeded");
			return false;
		}

		System.out.println(">> user not throtted, continue");

		return checkNext(email, password);
	}
}
