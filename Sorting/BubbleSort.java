import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * 1. Compare each pair of adjacent elements from the beginning of an array and,
 * if they are in reversed order, swap them. 
 * 2. If at least one swap has been done, repeat step 1.
 * 
 * You can imagine that on every step big bubbles float to the surface and stay
 * there. At the step, when no bubble moves, sorting stops. Let us see an
 * example of sorting an array to make the idea of bubble sort clearer.
 * 
 * @author zane.wang
 *
 */
public class BubbleSort
{

	public static <T> T[] bubbleSort(T[] items, Comparator<? super T> c) {
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			// compare and swap every adjacent pairs
			for (int j = 0; j < items.length - 1; j++) {
				if (c.compare(items[j], items[j + 1]) > 0) {
					T t = items[j];
					items[j] = items[j + 1];
					items[j + 1] = t;
					swapped = true;
				}
			}
		}
		return items;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(bubbleSort(new Integer[] { 5, 1, 4,
				2, 8 }, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		})));
	}
}
