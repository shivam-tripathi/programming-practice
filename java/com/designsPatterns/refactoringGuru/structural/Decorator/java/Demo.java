package com.designsPatterns.refactoringGuru.structural.Decorator.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.designsPatterns.refactoringGuru.structural.Decorator.java.dataSource.DataSource;
import com.designsPatterns.refactoringGuru.structural.Decorator.java.dataSource.FileDataSource;
import com.designsPatterns.refactoringGuru.structural.Decorator.java.decorators.CompressionDecorator;
import com.designsPatterns.refactoringGuru.structural.Decorator.java.decorators.EncryptionDecorator;

public class Demo {
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream(new File("temp.json"));
		byte[] bytes = is.readAllBytes();

		DataSource src = new CompressionDecorator(new EncryptionDecorator(new FileDataSource("demo.json.enc")));
		src.writeData(new String(bytes));
		String out = src.readData();

		OutputStream os = new FileOutputStream(new File("demo.json"));
		os.write(out.getBytes());
	}
}
