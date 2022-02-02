package com.designsPatterns.refactoringGuru.structural.Decorator.java.decorators;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.designsPatterns.refactoringGuru.structural.Decorator.java.dataSource.DataSource;
import com.designsPatterns.refactoringGuru.structural.Decorator.java.dataSource.FileDataSource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompressionDecorator extends DataSourceDecorator {
	int compressionLevel = 6;

	public CompressionDecorator(DataSource dataSource) {
		super(dataSource);
	}

	private String compress(String data) {
		byte[] bytes = data.getBytes();

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Deflater deflater = new Deflater(this.compressionLevel);
			deflater.setInput(bytes);
			deflater.finish();

			byte[] tmp = new byte[Math.min(bytes.length, 1024)];
			while (!deflater.finished()) {
				int size = deflater.deflate(tmp);
				baos.write(tmp, 0, size);
			}
			deflater.end();
			return Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String decompress(String compressed) {
		byte[] bytes = Base64.getDecoder().decode(compressed);

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Inflater inflater = new Inflater();
			inflater.setInput(bytes);

			byte[] tmp = new byte[1024 * 5];
			while (!inflater.finished()) {
				int size = inflater.inflate(tmp);
				baos.write(tmp, 0, size);
			}
			return new String(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String data = "Hello World!";
		CompressionDecorator cd = new CompressionDecorator(new FileDataSource("test.txt"));
		String compressed = cd.compress(data);
		String decompressed = cd.decompress(compressed);
		System.out.println("data = " + data + " " + data.length() + " compressed = " + compressed + " "
				+ compressed.length() + " decompressed = " + decompressed);
	}
}
