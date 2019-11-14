import java.util.*;

class BinaryHeap {
    int capacity;
    int size;
    int[] heap;

    BinaryHeap(int cap) {
        capacity = cap;
        size = 0;
        heap = new int[cap];
    }

    int getParent(int index) {
        return (index - 1) / 2;
    }

    int getLeft(int index) {
        return 2 * index + 1;
    }

    int getRight(int index) {
        return 2 * index + 1;
    }

    void addKey(int key) {
        size++;
        int index = size-1;
        heap[index] = key;

        while (getParent(index) >= 0 && heap[getParent(index)] > key) {
            int temp = heap[getParent(index)];
            heap[getParent(index)] = key;
            heap[index] = temp;
            index = getParent(index);
        }
    }

    void printHeap() {
        System.out.print("HEAP:\t");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int size = 20;
        BinaryHeap binaryHeap = new BinaryHeap(size);
        for (int i = 0; i < size; i++) {
            binaryHeap.addKey(random.nextInt(size) * (random.nextBoolean() ? -1 : 1));
        }
        binaryHeap.printHeap();
    }
}