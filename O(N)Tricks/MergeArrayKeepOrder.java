

/**
array a and b are sorted. Merge the two arrays while keeps the order.
require O(N).

key points: loop two arrays index range at the same time.
*/
public static int[] merge(int[] a, int[] b) {

		int[] answer = new int[a.length + b.length];
		int i = 0, j = 0, k = 0;

		while (i < a.length && j < b.length) {
			if (a[i] < b[j])
				answer[k++] = a[i++];

			else
				answer[k++] = b[j++];
		}

		while (i < a.length)
			answer[k++] = a[i++];

		while (j < b.length)
			answer[k++] = b[j++];

		return answer;
}