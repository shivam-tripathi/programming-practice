package com.designsPatterns.refactoringGuru.structural.Decorator.java.decorators;

import java.util.Base64;

import com.designsPatterns.refactoringGuru.structural.Decorator.java.dataSource.DataSource;

public class EncryptionDecorator extends DataSourceDecorator {
	public EncryptionDecorator(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void writeData(String data) {
		super.writeData(this.encode(data));
	}

	@Override
	public String readData() {
		return this.decode(super.readData());
	}

	private String encode(String data) {
		byte[] bytes = data.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] += (byte) 1;
		}
		return Base64.getEncoder().encodeToString(bytes);
	}

	private String decode(String encoded) {
		byte[] bytes = Base64.getDecoder().decode(encoded);
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] -= (byte) 1;
		}
		return new String(bytes);
	}
}
