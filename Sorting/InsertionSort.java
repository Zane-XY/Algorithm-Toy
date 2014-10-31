import java.util.Arrays;
import java.util.Comparator;

/**
 * Insertion sort iterates, consuming one input element each repetition, and
 * growing a sorted output list. Each iteration, insertion sort removes one
 * element from the input data, finds the location it belongs within the sorted
 * list, and inserts it there. It repeats until no input elements remain.
 * 
 * @author zane.wang
 *
 */
public class InsertionSort
{

	public static <T> T[] insertionSort(T[] items, Comparator<? super T> c) {
		for (int i = 1; i < items.length; i++) {
			for (int j = i; j > 0 && c.compare(items[j - 1], items[j]) > 0; j--) {
				T t = items[j];
				items[j] = items[j - 1];
				items[j - 1] = t;
			}
		}
		return items;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(insertionSort(new Integer[] { 5, 1,
				4, 2, 8 }, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		})));
	}
}
