package com.patterns.decorator;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;

class Main {
    public static void main(String[] args) throws IOException {
        int c;
        try (InputStream in = new LowerCaseInputStream(new FileInputStream("test.txt"))) {
            while ((c = in.read()) >= 0) {
                System.out.printf("%c", (char)c);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
