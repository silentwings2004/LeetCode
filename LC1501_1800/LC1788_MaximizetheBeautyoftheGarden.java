package LC1501_1800;
import java.util.*;
public class LC1788_MaximizetheBeautyoftheGarden {
    /**
     * There is a garden of n flowers, and each flower has an integer beauty value. The flowers are arranged in a line.
     * You are given an integer array flowers of size n and each flowers[i] represents the beauty of the ith flower.
     *
     * A garden is valid if it meets these conditions:
     *
     * The garden has at least two flowers.
     * The first and the last flower of the garden have the same beauty value.
     * As the appointed gardener, you have the ability to remove any (possibly none) flowers from the garden. You want
     * to remove flowers in a way that makes the remaining garden valid. The beauty of the garden is the sum of the
     * beauty of all the remaining flowers.
     *
     * Return the maximum possible beauty of some valid garden after you have removed any (possibly none) flowers.
     *
     * Input: flowers = [1,2,3,1,2]
     * Output: 8
     *
     * Input: flowers = [100,1,1,-3,1]
     * Output: 3
     *
     * Input: flowers = [-1,-2,0,-1]
     * Output: -2
     *
     * Constraints:
     *
     * 2 <= flowers.length <= 10^5
     * -10^4 <= flowers[i] <= 10^4
     * It is possible to create a valid garden by removing some (possibly none) flowers.
     * @param flowers
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int maximumBeauty(int[] flowers) {
        int n = flowers.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (flowers[i - 1] < 0 ? 0 : flowers[i - 1]);

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(flowers[i], new ArrayList<>());
            map.get(flowers[i]).add(i);
        }

        int res = Integer.MIN_VALUE;
        for (int key : map.keySet()) {
            int sz = map.get(key).size();
            if (sz == 1) continue;
            int l = map.get(key).get(0) + 1, r = map.get(key).get(sz - 1) - 1;
            res = Math.max(res, s[r + 1] - s[l] + key * 2);
        }
        return res;
    }

    // S2: prefix sum + BIT
    final int M = 100010;
    int[] tr;
    public int maximumBeauty2(int[] flowers) {
        tr = new int[M];
        int n = flowers.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + flowers[i - 1];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            if (!map.containsKey(flowers[i])) map.put(flowers[i], i);
            if (flowers[i] < 0) add(i + 1, flowers[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int l = i, r = map.get(flowers[i - 1]) + 1;
            if (l == r) continue;
            int sum = s[r] - s[l - 1], neg = query(r - 1) - query(l);
            res = Math.max(res, sum - neg);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i < M; i += lowbit(i)) tr[i] += c;
    }

    private int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}