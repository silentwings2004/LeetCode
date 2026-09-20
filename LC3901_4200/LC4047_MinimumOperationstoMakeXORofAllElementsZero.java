package LC3901_4200;
import java.util.*;
public class LC4047_MinimumOperationstoMakeXORofAllElementsZero {
    /**
     * You are given an integer array nums consisting of positive integers.
     *
     * You may perform the following operation any number of times:
     *
     * Choose two distinct indices i and j such that nums[i] != nums[j], and replace either nums[i] or nums[j] with
     * nums[i] ^ nums[j], where ^ denotes the bitwise XOR.
     * Return the minimum number of operations required to make the bitwise XOR of all elements in nums equal to 0. If
     * it is impossible, return -1.
     *
     * Input: nums = [8,1,4,8,2]
     * Output: 3
     *
     * Input: nums = [1,2,4]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 2000
     * @param nums
     * @return
     */
    // time = O(n + v * m), space = O(v)  v = 2048, m <= min(n, v)
    final int N = 2048;
    public int minOperations(int[] nums) {
        int n = nums.length, tot = 0;
        for (int x : nums) tot ^= x;
        if (tot == 0) return 0;

        boolean allEqual = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) return -1;
        if (n <= 11) {
            if (computeRank(nums) == n) return -1;
        }

        boolean[] st = new boolean[N];
        List<Integer> p = new ArrayList<>();
        for (int x : nums) {
            if (!st[x]) {
                st[x] = true;
                p.add(x);
            }
        }

        int[] dist = new int[N];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        dist[0] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : p) {
                int w = u ^ v;
                if (dist[w] == -1) {
                    dist[w] = dist[u] + 1;
                    if (w == tot) return dist[w];
                    q.offer(w);
                }
            }
        }
        return -1;
    }

    private int computeRank(int[] nums) {
        int[] b = new int[11];
        int r = 0;
        for (int x : nums) {
            int v = x;
            for (int i = 10; i >= 0; i--) {
                if ((v >> i & 1) == 0) continue;
                if (b[i] == 0) {
                    b[i] = v;
                    r++;
                    break;
                }
                v ^= b[i];
            }
        }
        return r;
    }
}
