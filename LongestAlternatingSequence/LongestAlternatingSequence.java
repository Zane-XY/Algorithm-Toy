package codility.LongestAlternatingSequence;

import java.util.*;

/**
 * Created by zane.wang on 1/9/14.
 */
class LongestAlternatingSequenceJava {

    static boolean checkAlt(List<Integer> lst) {
        if (lst.size() >= 3) {
            int a = lst.get(0);
            int b = lst.get(1);
            int c = lst.get(2);
            return (a >= 0 && b <= 0 && c >= 0) || (a <= 0 && b >= 0 && c <= 0);
        } else {
            return false;
        }
    }

    static int maxLen(List<List<Integer>> lst) {
        Map<Integer, Integer> stats = new HashMap<Integer, Integer>();

        for (int i = 0; i < lst.size(); i++) {
            int j = i;
            while (j < lst.size() && checkAlt(lst.get(j))) {
                if (stats.containsKey(i)) {//pre is true
                    stats.put(i, stats.get(i) + 1);
                } else {
                    stats.put(i, 1);
                }
                j++;
            }
            i = j;
        }

        for (Map.Entry<Integer, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        List<Integer> lens = new ArrayList<Integer>(stats.values());
        int maxSeqLen = Collections.max(lens);
        int r = maxSeqLen > 0 ? maxSeqLen + 2 : 0;
        System.out.println(r);
        return r;
    }


    static <T> List<List<T>> sliding(List<T> lst, int size, int step) {
        List<List<T>> rs = new ArrayList<List<T>>();
        for (int i = 0; i < lst.size(); i += step) {
            // each step produce a window
            List<T> win = new ArrayList<T>();
            int upBound = i + size;
            for (int j = i, k = 0; j < lst.size() && j < upBound; j++, k++) {
                win.add(lst.get(j));
            }
            if (win.size() == size) {
                rs.add(win);
                //System.out.println(Arrays.toString(win.toArray()));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        maxLen(sliding(Arrays.asList(new Integer[]{5, 4, -3, 2, 0, 1, -1, 0, 2, -3, 4}), 3, 1));
    }
}
