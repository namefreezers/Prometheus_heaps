import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;

class Heap {
	int[] heap;
	int size;

	Heap(int n) {
		heap = new int[n + 2];
		size = 0;
	}

	int par(int i) {
		return i / 2;
	}

	int left(int i) {
		return i * 2;
	}

	int right(int i) {
		return i * 2 + 1;
	}
}

class MaxHeap extends Heap {
	MaxHeap(int n) {
		super(n);
	}

	void maxHeapify(int i) {
		int p = left(i), q = right(i);
		int max;
		if (p <= size && heap[p] > heap[i])
			max = p;
		else
			max = i;
		if (q <= size && heap[q] > heap[max])
			max = q;
		if (max != i) {
			int temp = heap[i];
			heap[i] = heap[max];
			heap[max] = temp;
			maxHeapify(max);
		}
	}

	void insert(int key) {
		size++;
		heap[size] = -2000000000;
		increaseKey(size, key);
	}

	void increaseKey(int i, int key) {
		heap[i] = key;
		while (par(i) != 0 & heap[par(i)] < heap[i]) {
			int temp = heap[par(i)];
			heap[par(i)] = heap[i];
			heap[i] = temp;
			i = par(i);
		}
	}

	int max() {
		return heap[1];
	}

	int extractMax() {
		int max = heap[1];
		heap[1] = heap[size];
		size--;
		maxHeapify(1);
		return max;
	}

}

class MinHeap extends Heap {
	MinHeap(int n) {
		super(n);
	}

	void minHeapify(int i) {
		int p = left(i), q = right(i);
		int min;
		if (p <= size && heap[p] < heap[i])
			min = p;
		else
			min = i;
		if (q <= size && heap[q] < heap[min])
			min = q;
		if (min != i) {
			int temp = heap[i];
			heap[i] = heap[min];
			heap[min] = temp;
			minHeapify(min);
		}
	}

	void insert(int key) {
		size++;
		heap[size] = 2000000000;
		decreaseKey(size, key);
	}

	void decreaseKey(int i, int key) {
		heap[i] = key;
		while (par(i) != 0 & heap[par(i)] > heap[i]) {
			int temp = heap[par(i)];
			heap[par(i)] = heap[i];
			heap[i] = temp;
			i = par(i);
		}
	}

	int min() {
		return heap[1];
	}

	int extractMin() {
		int min = heap[1];
		heap[1] = heap[size];
		size--;
		minHeapify(1);
		return min;
	}
}

class DoubleHeap {
	MinHeap min;
	MaxHeap max;

	DoubleHeap(int n) {
		min = new MinHeap((n + 1) / 2);
		max = new MaxHeap((n + 1) / 2);
	}

	void insert(int n) {
		if (n > min.min())
			min.insert(n);
		else
			max.insert(n);
		if (min.size - max.size > 1)
			max.insert(min.extractMin());
		else if ((min.size - max.size < -1))
			min.insert(max.extractMax());
	}

	int[] mediana() {
		if (min.size - max.size == 1) {
			int[] res = new int[1];
			res[0] = min.min();
			return res;
		}

		else if ((min.size - max.size == -1)) {
			int[] res = new int[1];
			res[0] = max.max();
			return res;
		}
		int[] res = new int[2];
		res[0] = max.max();
		res[1] = min.min();
		return res;

	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		java.util.Date date = new java.util.Date();
		System.out.println(new Timestamp(date.getTime()));

		BufferedReader in = new BufferedReader(new FileReader("data_examples_05/input_16_10000.txt"));
		int n = Integer.parseInt(in.readLine());
		DoubleHeap heap = new DoubleHeap(n);
		for (int i = 0; i < n; i++) {
			heap.insert(Integer.parseInt(in.readLine()));
//			if (i == 2014 | i == 9875) {
//				int[] med = heap.mediana();
//				for (int j : med)
//					System.out.print(j + " ");
//				System.out.println();
//			}
			if (i==2014){
				for (int j = 0; j<5; j++)
					System.out.print(heap.max.heap[j+1] + " ");
				System.out.println();
				for (int j = 0; j<5; j++)
					System.out.print(heap.min.heap[j+1] + " ");
				System.out.println();
			}
		}

		in.close();
	}

}
