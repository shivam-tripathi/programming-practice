import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Item {
	List<Integer> indexes = new ArrayList<>();
	List<Integer> values = new ArrayList<>();
	public String toString() {
		return String.format("%s %s", this.indexes.toString(), this.values.toString());
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int rowCount = sc.nextInt();
			int columnCount = sc.nextInt();

			List<Item> itemsList = new ArrayList<>();
			for (int i = 0; i < columnCount; i++) {
				itemsList.add(new Item());
			}

			for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
				int nonZeroCount = sc.nextInt();
				if (nonZeroCount == 0) {
					sc.nextLine();
					continue;
				}

				List<Integer> indexes = new ArrayList<>();
				for (int i = 0; i < nonZeroCount; i++) {
					int index = sc.nextInt();
					indexes.add(index);
				}
				for (int i = 0; i < nonZeroCount; i++) {
					int index = indexes.get(i) - 1;
					int value = sc.nextInt();
					itemsList.get(index).indexes.add(rowIndex);
					itemsList.get(index).values.add(value);
				}
			}

			System.out.printf("%d %d\n", columnCount, rowCount);
			for (Item item: itemsList) {
				System.out.printf("%d%s", item.indexes.size(), item.indexes.size() != 0 ? " " : "");
				System.out.println(item.indexes.stream().map(_index -> String.valueOf(_index + 1)).collect(Collectors.joining(" ")));
				System.out.println(item.values.stream().map(String::valueOf).collect(Collectors.joining(" ")));
			}
		}
	}
}