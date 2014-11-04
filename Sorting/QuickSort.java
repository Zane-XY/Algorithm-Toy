import java.util.Arrays;

/**
 * <p>
 * 1. Pick an element, called a pivot, from the array. Pivot is usually randomly picked.
 * 2. Reorder the array so that all elements with values less than the pivot come before the pivot, while all elements with values greater than the pivot come after it (equal values can go either way). After this partitioning, the pivot is in its final position. This is called the partition operation.
 * 3. Recursively apply the above steps to the sub-array of elements with smaller values and separately to the sub-array of elements with greater values.
 * <p/>
 * Created by zane.wang on 11/1/14.
 */
public class QuickSort {

    /**
     * global variable stores the array to sort, because it's hard to create sub-array base on index, so make arr global can avoid passing it
     * to recursive function
     */
    private int[] arr;

    private int[] quickSort(int[] items) {
        arr = items;
        sortPart(0, arr.length - 1);
        return arr;
    }

    /**
     * recursively sort by partition
     *
     * @param left
     * @param right
     */
    private void sortPart(int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            int pivotIndex = partitionByPivot(left, right);
            sortPart(left, pivotIndex - 1);
            sortPart(pivotIndex + 1, right);
        }
    }

    /**
     * choose the right most element as pivot
     */
    private int partitionByPivot(int left, int right) {
        int leftIndex = left - 1, rightIndex = right;
        int rightBound = right;
        int pivot = arr[rightBound];
        while (true) {
            while (arr[++leftIndex] < pivot) ; // nop
            while (arr[--rightIndex] > pivot && rightIndex > 0) ; // nop
            if (leftIndex >= rightIndex) {
                break;
            } else {
                swap(leftIndex, rightIndex, arr);
            }
        }

        swap(leftIndex, rightBound, arr);
        return leftIndex;
    }

    private int[] swap(int a, int b, int[] arr) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
        return arr;
    }


    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        System.out.println(Arrays.toString(sorter.quickSort(new int[]{4, 7, 8, 0, 5})));
    }
}
