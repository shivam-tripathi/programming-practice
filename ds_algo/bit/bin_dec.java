import java.util.*;
import java.io.*;

class Main {
	static ArrayList<Integer> pow2 = new ArrayList<>();

	public static int binary2Decimal(String s, ArrayList<Integer> pow2) {
		int val = 0, size = s.length() - 1, index = 0;
		for (int i = size; i >= 0; i--) {
			val += (s.charAt(i) - '0') * pow2.get(index++);
		}
		return val;
	}

	public static String decimal2Binary(int number) {
		int temp = number;
		StringBuilder binary = new StringBuilder();
		while(temp != 0) {
			binary.append(temp % 2 == 0 ? '0' : '1');
			temp /= 2;
		}

		return new String(binary.reverse());
	}

    public static void main(String[] args) throws IOException {
		pow2.add(1);
		for (int i = 1; i < 20; i++) {
			pow2.add(pow2.get(i - 1) * 2);
		}

		String binIn = "101010101";
		System.out.println(binIn);
		int decOut = binary2Decimal(binIn, pow2);
		String binOut = decimal2Binary(decOut);
		System.out.println(binOut);
     }
}
