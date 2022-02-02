package com.designsPatterns.refactoringGuru.structural.Decorator.java.decorators;

import com.designsPatterns.refactoringGuru.structural.Decorator.java.dataSource.DataSource;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataSourceDecorator implements DataSource {
	private DataSource wrapped;

	@Override
	public void writeData(String data) {
		wrapped.writeData(data);
	}

	@Override
	public String readData() {
		return wrapped.readData();
	}
}
