import java.util.Arrays;
import java.util.Random;

/**
 * Created by zane.wang on 4/15/2014.
 */

/**
 * Give an array of any type, return a shuffled array
 */
public class KnuthShuffle {
    /**
     * shuffle
     * @param arr takes array of any reference type, because generics doesn't support primitive type
     * @param <T>
     * @return
     */
    public static <T> T[] shuffle(T[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int ri = r.nextInt(arr.length);
            T v = arr[ri];
            arr[ri] = arr[i];
            arr[i] = v;
        }
        return arr;
    }

    public static void main(String[] args) {
        Integer[] in = new Integer[] {2,3,4,6};

        shuffle(in);
        System.out.println(Arrays.toString(in));

    }
}
