package LC3901_4200;
import java.util.*;
public class LC4007_WidestPossibleFence {
    /**
     * You are given an integer array planks, where planks[i] represents the height of the ith wooden plank. Each plank
     * has a width of 1 unit.
     *
     * You want to build a fence consisting of planks that all have the same height.
     *
     * You may either use a plank as is, or combine exactly two distinct original planks into a single plank whose
     * height equals the sum of their heights. Each original plank can be used at most once, and not all original planks
     * need to be used.
     *
     * Return the maximum possible width of the fence that can be built.
     *
     * Input: planks = [1,3,2,5,7,5,4,2,1]
     * Output: 4
     *
     * Input: planks = [2,3,7]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= planks.length <= 1000
     * 1 <= planks[i] <= 10^9
     * @param planks
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int maximumWidth(int[] planks) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int x : planks) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }

        for (int a : cnt.keySet()) {
            for (int b : cnt.keySet()) {
                if (a < b) {
                    int v = Math.min(cnt.get(a), cnt.get(b));
                    map.put(a + b, map.getOrDefault(a + b, 0) + v);
                }
                if (a == b) {
                    int v = cnt.get(a) / 2;
                    map.put(a + b, map.getOrDefault(a + b, 0) + v);
                }
            }
        }
        int res = 0;
        for (int v : map.values()) res = Math.max(res, v);
        return res;
    }
}