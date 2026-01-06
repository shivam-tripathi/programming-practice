package com.designsPatterns.wiki.behavioral.chainOfResponsibility.java;

import com.designsPatterns.wiki.behavioral.chainOfResponsibility.java.Logger.LogLevel;

public class Demo {
	public static void main(String[] args) {
		Logger logger = Logger.consoleLogger(Logger.LogLevel.all())
				.appendNext(Logger.fileLogger(Logger.LogLevel.WARNING, Logger.LogLevel.ERROR))
				.appendNext(Logger.emailLogger(Logger.LogLevel.ERROR));

		// Handled ONLY by consoleLogger since the console has a LogLevel of all
		logger.message("Entering function ProcessOrder().", LogLevel.DEBUG);
		logger.message("Order record retrieved.", LogLevel.INFO);

		// Handled by consoleLogger and fileLogger since emailLogger
		logger.message("Unable to Process Order ORD1 Dated D1 For Customer C1.", LogLevel.WARNING);

		// Handled by all the loggers
		logger.message("Customer Address details missing in Organization DataBase.", LogLevel.ERROR);
	}
}
