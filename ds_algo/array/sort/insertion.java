
import java.util.*;

class Main {
	static void selectionSort(int[] arr) {
        int end = 0;
        while(end < arr.length - 1) {
            int item = arr[end + 1];
            int pointer = end;
            while(pointer >=0 && arr[pointer] > item) {
                arr[pointer + 1] = arr[pointer];
                pointer--;
            }
            arr[pointer+1] = item;
            end++;
        }
    }

	public static void main(String args[]) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++	) {
            System.out.println(arr[i]);
        }
    }
}
