package com.designsPatterns.refactoringGuru.structural.Decorator.java.dataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileDataSource implements DataSource {
	private String name;

	@Override
	public void writeData(String data) {
		File file = new File(name);
		try (OutputStream fos = new FileOutputStream(file)) {
			fos.write(data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String readData() {
		File file = new File(name);
		try (InputStream fis = new FileInputStream(file)) {
			byte[] bytes = fis.readAllBytes();
			return new String(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
