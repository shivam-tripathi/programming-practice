package com.designsPatterns.wiki.behavioral.chainOfResponsibility.java;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Consumer;

@FunctionalInterface
public interface Logger {
	public enum LogLevel {
		INFO, DEBUG, WARNING, ERROR;

		public static LogLevel[] all() {
			return LogLevel.values();
		}
	}

	abstract void message(String msg, LogLevel severity);

	default Logger appendNext(Logger logger) {
		return (msg, severity) -> {
			message(msg, severity);
			logger.message(msg, severity);
		};
	}

	static Logger writeLogger(LogLevel[] levels, Consumer<String> consumer) {
		EnumSet<LogLevel> set = EnumSet.copyOf(Arrays.asList(levels));
		return (msg, severity) -> {
			if (set.contains(severity)) {
				System.out.print(severity + " >> ");
				consumer.accept(msg);
			}
		};
	}

	static Logger consoleLogger(LogLevel... levels) {
		return Logger.writeLogger(levels, msg -> System.out.println("console :: " + msg));
	}

	static Logger emailLogger(LogLevel... levels) {
		return Logger.writeLogger(levels, msg -> System.out.println("email :: " + msg));
	}

	static Logger fileLogger(LogLevel... levels) {
		return Logger.writeLogger(levels, msg -> System.out.println("file :: " + msg));
	}
}
