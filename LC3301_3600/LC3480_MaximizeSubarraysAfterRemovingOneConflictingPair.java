package LC3301_3600;
import java.util.*;
public class LC3480_MaximizeSubarraysAfterRemovingOneConflictingPair {
    /**
     * You are given an integer n which represents an array nums containing the numbers from 1 to n in order.
     * Additionally, you are given a 2D array conflictingPairs, where conflictingPairs[i] = [a, b] indicates that a and
     * b form a conflicting pair.
     *
     * Remove exactly one element from conflictingPairs. Afterward, count the number of non-empty subarrays of nums
     * which do not contain both a and b for any remaining conflicting pair [a, b].
     *
     * Return the maximum number of subarrays possible after removing exactly one conflicting pair.
     *
     * A subarray is a contiguous, non-empty sequence of elements within an array.
     *
     * Input: n = 4, conflictingPairs = [[2,3],[1,4]]
     * Output: 9
     *
     * Input: n = 5, conflictingPairs = [[1,2],[2,5],[3,5]]
     * Output: 12
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= conflictingPairs.length <= 2 * n
     * conflictingPairs[i].length == 2
     * 1 <= conflictingPairs[i][j] <= n
     * conflictingPairs[i][0] != conflictingPairs[i][1]
     * @param n
     * @param conflictingPairs
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        List<Integer>[] q = new List[n + 1];
        for (int i = 0; i <= n; i++) q[i] = new ArrayList<>();
        for (int[] x : conflictingPairs) {
            int a = x[0], b = x[1];
            q[Math.min(a, b)].add(Math.max(a, b));
        }

        long res = 0;
        int b0 = n + 1, b1 = n + 1;
        long[] extra = new long[n + 2];
        long maxExtra = 0;
        for (int a = n; a > 0; a--) {
            List<Integer> p = q[a];
            Collections.sort(p);
            if (p.size() > 2) p = p.subList(0, 2);
            for (int b : p) {
                if (b < b0) {
                    b1 = b0;
                    b0 = b;
                } else if (b < b1) b1 = b;
            }
            res += b0 - a;
            extra[b0] += b1 - b0;
            maxExtra = Math.max(maxExtra, extra[b0]);
        }
        return res + maxExtra;
    }
}
/**
 * 1. 求子数组个数
 * 滑动窗口：外层循环枚举子数组的左端点(或者右端点)，去计算有多少个合法的右端点
 * n = 5
 * 枚举左端点 = 1, 2, 3, 4, 5, ..., n, 去看有多少个合法的右端点
 * 如果对应的右端点，分别为 3,3,1,2,1
 * 合法子数组的个数 = 3 + 3 + 1 + 2 + 1
 * 2. 左端点在 i 的时候，合法右端点有 b0 - i 个
 *    其中 b0 就是 a >= i 的 pairs 中的最小的 b
 *    把所有的 b0 - i 加起来，就是在不删除的情况下，合法子数组的个数
 * 3. 倒着枚举 a = n, n - 1, ... 1, 同时维护对应的 b 的最小值，即为 b0
 * 4. 额外维护 b 的次小值 b1
 *    当左端点为 i 的时候，如果删除 b0 的时候，额外多了 b1 - b0 个子数组的右端点
 */