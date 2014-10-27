package codility.MaxDiffWithinSequence;

/**
 * Created by zane.wang on 1/9/14.
 */
class MaxDiffInSequence {
    public static int amplitude(int[] arr) {
        if (arr.length >= 2) {
            int min, max = 0;
            if (arr[0] >= arr[1]) {
                min = arr[1];
                max = arr[0];
            } else {
                min = arr[0];
                max = arr[1];
            }

            for (int i = 2; i < arr.length; i++) {
                if (arr[i] >= max) {
                    max = arr[i];
                }
                if (arr[i] <= min) {
                    min = arr[i];
                }
            }
            return max - min;
        } else if (arr.length == 1) {
            return arr[0];
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{0, -3, 9, 7, 100, -70};
        System.out.println(amplitude(input));

    }
}
