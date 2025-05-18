package LC1501_1800;
import java.util.*;
public class LC1743_RestoretheArrayFromAdjacentPairs {
    /**
     * There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do
     * remember every pair of adjacent elements in nums.
     *
     * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates
     * that the elements ui and vi are adjacent in nums.
     *
     * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either
     * as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
     *
     * Return the original array nums. If there are multiple solutions, return any of them.
     *
     * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
     * Output: [1,2,3,4]
     *
     * Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
     * Output: [-2,4,1,-3]
     *
     * Input: adjacentPairs = [[100000,-100000]]
     * Output: [100000,-100000]
     *
     * Constraints:
     *
     * nums.length == n
     * adjacentPairs.length == n - 1
     * adjacentPairs[i].length == 2
     * 2 <= n <= 105
     * -10^5 <= nums[i], ui, vi <= 10^5
     * There exists some nums that has adjacentPairs as its pairs.
     * @param adjacentPairs
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Integer, List<Integer>> map;
    HashMap<Integer, Integer> d;
    HashSet<Integer> set;
    int[] res;
    int idx;
    public int[] restoreArray(int[][] adjacentPairs) {
        map = new HashMap<>();
        d = new HashMap<>();
        set = new HashSet<>();
        int n = adjacentPairs.length + 1;
        res = new int[n];
        idx = 0;

        for (int[] e : adjacentPairs) {
            int a = e[0], b = e[1];
            map.putIfAbsent(a, new ArrayList<>());
            map.putIfAbsent(b, new ArrayList<>());
            map.get(a).add(b);
            map.get(b).add(a);
            d.put(a, d.getOrDefault(a, 0) + 1);
            d.put(b, d.getOrDefault(b, 0) + 1);
        }

        for (int key : d.keySet()) {
            if (d.get(key) == 1) {
                set.add(key);
                dfs(key);
                break;
            }
        }
        return res;
    }

    private void dfs(int u) {
        res[idx++] = u;
        for (int j : map.getOrDefault(u, new ArrayList<>())) {
            if (!set.add(j)) continue;
            dfs(j);
        }
    }
}