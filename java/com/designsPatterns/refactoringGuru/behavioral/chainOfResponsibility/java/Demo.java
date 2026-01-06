package com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java;

import com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware.CheckUserRoleMiddleware;
import com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware.Middleware;
import com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware.ThrottlingMiddleware;
import com.designsPatterns.refactoringGuru.behavioral.chainOfResponsibility.java.middleware.UserExistsMiddleware;

public class Demo {
	public static void main(String[] args) {
		Server server = new Server();

		Middleware userExistsMiddleware = new UserExistsMiddleware(server);
		Middleware throttlingMiddleware = new ThrottlingMiddleware(10);
		Middleware checkUserRolMiddleware = new CheckUserRoleMiddleware();
		userExistsMiddleware.linkWith(throttlingMiddleware).linkWith(checkUserRolMiddleware);

		server.setMiddleware(userExistsMiddleware);

		server.register("shivam", "password");
		server.register("siddhant", "12345");

		server.login("shivam", "password");
		server.login("siddhant", "54321");
	}
}
