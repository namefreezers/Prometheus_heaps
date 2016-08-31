import java.sql.Timestamp;

class Heap {
	int[] heap;
	int size;

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
	void maxHeapify(int i) {
		int p = left(i), q = right(i);
		int max;
		if (p <= size & heap[p] > heap[i])
			max = p;
		else
			max = i;
		if (q <= size & heap[q] > heap[max])
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
	int max(){
		return heap[1];
	}
	int extractMax(){
		int max = heap[1];
		heap[1] = heap[size];
		size--;
		maxHeapify(1);
		return max;
	}

}

class MinHeap extends Heap {
	void minHeapify(int i) {
		int p = left(i), q = right(i);
		int min;
		if (p <= size & heap[p] < heap[i])
			min = p;
		else
			min = i;
		if (q <= size & heap[q] < heap[min])
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
	
	int min(){
		return heap[1];
	}
	int extractMin(){
		int min = heap[1];
		heap[1] = heap[size];
		size--;
		minHeapify(1);
		return min;
	}
}

public class Main {

	public static void main(String[] args) {
		java.util.Date date = new java.util.Date();
		System.out.println(new Timestamp(date.getTime()));

	}

}
