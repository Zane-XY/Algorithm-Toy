

   /**
	 * give an new int[] {1,4,5,-3,-5}, move all the negative ints before
	 * positive, require O(N)
	 * 
	 * key points, loop  the array from both sides,  decrement only when necessary
	 * @param arr
	 * @return
	 */
	public static int[] partitionBySigns(int[] arr) {

		for (int i = 0, j = arr.length - 1; i < arr.length; i++) {
			if (arr[i] > 0) {
				if (arr[j] < 0) {
					int t = arr[i];
					arr[i] = arr[j];
					arr[j] = t;
					j--;
				}
			}
		}
		return arr;
	}