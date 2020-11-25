import java.util.*;

class MergeIntervalsSolution {
	boolean START = true;
	boolean END = false;
 	public int[][] merge(int[][] intervals) { // Don't review this, spagetti for first timer
 		class StackItem {
 			Integer val;
 			Boolean type;
 			StackItem(int val, boolean type) {
 				this.val = val;
 				this.type = type;
 			}
 		}
 		List<StackItem> ranges = new ArrayList<>();
 		for (int i = 0; i < intervals.length; i++) {
 			int[] interval = intervals[i];
 			ranges.add(new StackItem(interval[0], START));
 			ranges.add(new StackItem(interval[1], END));
 		}
 		Collections.sort(ranges, (a,b) -> {
 			if (a.val.equals(b.val)) {
 				if (a.type == START && b.type == END) {
 					return -1;
 				}
 				if (a.type == END && b.type == START) {
 					return 1;
 				}
 			}
 			return Integer.compare(a.val, b.val);
 		});
 		
 		// Stack<StackItem> stack = new Stack<>();
 		List<int[]> ans = new ArrayList<>();

 		StackItem start = null;
 		int startCount = 0;
 		for (int i = 0; i < ranges.size(); i++) {
 			StackItem item = ranges.get(i);
 			if (item.type == START) {
 				if (startCount == 0) {
 					start = item;
 				}
 				startCount++;
 			} else {
 				if (startCount == 1) {
 					ans.add(new int[] { start.val, item.val });
 				}
 				startCount--;
 			}
 		}

 		if (start != null) {
 			ans.add(new int[] { start.val, ranges.get(ranges.size() - 1).val });
 		}
 		
 		return ans.toArray(new int[ans.size()][2]);
 	}

 	int[][] merge2(int [][] intervals) { // Review this
 		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
 		int[] range = new int[] { intervals[0][0], intervals[0][1] };
 		List<int[]> ans = new ArrayList<>();
 		for (int i = 1; i < intervals.length; i++) {
 			int begin = intervals[i][0], end = intervals[i][1];
			if (range[1] < begin) { // if range end is before current begin, start a new range
				ans.add(range);
				range = new int[] { begin, end };
			} else {
				if (range[1] < end) { // if range end is less than current end, merge it
					range[1] = end;
				}
			}
 		}
 		ans.add(range);
 		return ans.toArray(new int[ans.size()][]);
 	}
}

class MergeIntervals {
	public static void main(String[] args) {
		int[][] intervals = new int[][] {
			new int[] {1,4},
			new int[] {0,2},
			new int[] {3,5},
		};

		System.out.println("[[1,4],[0,2],[3,5]]");

		for (int[] i : new MergeIntervalsSolution().merge2(intervals)) {
			System.out.println("("+i[0]+","+i[1]+")");
		}
	}
}
