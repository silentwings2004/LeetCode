package LC3601_3900;
import java.util.*;
public class LC3695_MaximizeAlternatingSumUsingSwaps {
    /**
     * You are given an integer array nums.
     *
     * You want to maximize the alternating sum of nums, which is defined as the value obtained by adding elements at
     * even indices and subtracting elements at odd indices. That is, nums[0] - nums[1] + nums[2] - nums[3]...
     *
     * You are also given a 2D integer array swaps where swaps[i] = [pi, qi]. For each pair [pi, qi] in swaps, you are
     * allowed to swap the elements at indices pi and qi. These swaps can be performed any number of times and in any order.
     *
     * Return the maximum possible alternating sum of nums.
     *
     * Input: nums = [1,2,3], swaps = [[0,2],[1,2]]
     * Output: 4
     *
     * Input: nums = [1,2,3], swaps = [[1,2]]
     * Output: 2
     *
     * Input: nums = [1,1000000000,1,1000000000,1,1000000000], swaps = []
     * Output: -2999999997
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= swaps.length <= 10^5
     * swaps[i] = [pi, qi]
     * 0 <= pi < qi <= nums.length - 1
     * [pi, qi] != [pj, qj]
     * @param nums
     * @param swaps
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int[] x : swaps) {
            int a = x[0], b = x[1];
            if (find(a) != find(b)) p[find(a)] = find(b);
        }

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int fa = find(i);
            map.putIfAbsent(fa, new ArrayList<>());
            map.get(fa).add(i);
        }

        long res = 0;
        for (List<Integer> v : map.values()) {
            Collections.sort(v, (o1, o2) -> nums[o2] - nums[o1]);
            int even = 0;
            for (int x : v) {
                if (x % 2 == 0) even++;
            }
            for (int i = 0; i < v.size(); i++) {
                if (i < even) res += nums[v.get(i)];
                else res -= nums[v.get(i)];
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}