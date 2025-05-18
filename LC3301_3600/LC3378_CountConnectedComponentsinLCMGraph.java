package LC3301_3600;
import java.util.*;
public class LC3378_CountConnectedComponentsinLCMGraph {
    /**
     * You are given an array of integers nums of size n and a positive integer threshold.
     *
     * There is a graph consisting of n nodes with the ith node having a value of nums[i]. Two nodes i and j in the
     * graph are connected via an undirected edge if lcm(nums[i], nums[j]) <= threshold.
     *
     * Return the number of connected components in this graph.
     *
     * A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no
     * vertex of the subgraph shares an edge with a vertex outside of the subgraph.
     *
     * The term lcm(a, b) denotes the least common multiple of a and b.
     *
     * Input: nums = [2,4,8,3,9], threshold = 5
     * Output: 4
     *
     * Input: nums = [2,4,8,3,9,12], threshold = 10
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * All elements of nums are unique.
     * 1 <= threshold <= 2 * 10^5
     * @param nums
     * @param threshold
     * @return
     */
    // time = O(tlogt), space = O(t)
    int[] p;
    public int countComponents(int[] nums, int threshold) {
        p = new int[threshold + 1];
        for (int i = 0; i <= threshold; i++) p[i] = i;
        for (int x : nums) {
            for (int i = 1;; i++) {
                if (1L * i * x > threshold) break;
                int y = x * i;
                p[find(y)] = find(x);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        int cnt = 0;
        for (int x : nums) {
            if (x > threshold) cnt++;
            else set.add(find(x));
        }
        return set.size() + cnt;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * 1. LCM 转成 GCD
 * 2. 枚举 GCD 及其倍数
 */