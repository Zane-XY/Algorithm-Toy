import java.util.Arrays;
import java.util.Comparator;

/**
 * The algorithm divides the input list into two parts: the sublist of items
 * already sorted, which is built up from left to right at the front (left) of
 * the list, and the sublist of items remaining to be sorted that occupy the
 * rest of the list. Initially, the sorted sublist is empty and the unsorted
 * sublist is the entire input list. The algorithm proceeds by finding the
 * smallest (or largest, depending on sorting order) element in the unsorted
 * sublist, exchanging it with the leftmost unsorted element (putting it in
 * sorted order), and moving the sublist boundaries one element to the right.
 * 
 * @author zane.wang
 *
 */

public class SelectionSort {

	public static <T> T[] selectionSort(T[] items, Comparator<? super T> c) {
		for (int i = 0; i < items.length - 1; i++) {
			for (int j = i + 1; j < items.length; j++) {
				if (c.compare(items[i], items[j]) > 0) {
					T t = items[i];
					items[i] = items[j];
					items[j] = t;
				}
			}
		}
		return items;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(selectionSort(new Integer[] { 5, 1,
				4, 2, 8 }, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		})));
	}
}
