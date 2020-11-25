import java.util.*;
import java.util.stream.*;
import java.io.*;

class UglyNumbers {
	static List<Integer> list = new ArrayList<>() {{ add(1); }};
	static int[] index = new int[] {1, 1, 1};
	static Map<Integer, Integer> indexMap = new HashMap<>(Map.of(2, 1, 3, 1, 5, 1));

	static int  getMultiple(int number) {
		return number * indexMap.get(number);
	}

	static int getNumber(int n) {
		if (n < list.size()) {
			return list.get(n - 1);
		}

		while (list.size() < n) {
			int toAdd = getMultiple(2), threeMultiple = getMultiple(3), fiveMultiple = getMultiple(5);
			int activeNumber = 2;
			if (threeMultiple < toAdd && threeMultiple < fiveMultiple) {
				toAdd = threeMultiple;
				activeNumber = 3;
			} else if (fiveMultiple < toAdd) {
				toAdd = fiveMultiple;
				activeNumber = 5;
			}

			if (!list.get(list.size() - 1).equals(toAdd)) {
				list.add(toAdd);
			}
			indexMap.put(activeNumber, indexMap.get(activeNumber) + 1);
		}

		System.out.println(list);

		return list.get(n - 1);
	}
}


class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int limit = sc.nextInt();
		System.out.println(UglyNumbers.getNumber(limit));
	}
}
