package LC3301_3600;
import java.util.*;
public class LC3538_MergeOperationsforMinimumTravelTime {
    /**
     * You are given a straight road of length l km, an integer n, an integer k, and two integer arrays, position and
     * time, each of length n.
     *
     * The array position lists the positions (in km) of signs in strictly increasing order (with position[0] = 0 and
     * position[n - 1] = l).
     *
     * Each time[i] represents the time (in minutes) required to travel 1 km between position[i] and position[i + 1].
     *
     * You must perform exactly k merge operations. In one merge, you can choose any two adjacent signs at indices i
     * and i + 1 (with i > 0 and i + 1 < n) and:
     *
     * Update the sign at index i + 1 so that its time becomes time[i] + time[i + 1].
     * Remove the sign at index i.
     * Return the minimum total travel time (in minutes) to travel from 0 to l after exactly k merges.
     *
     * Input: l = 10, n = 4, k = 1, position = [0,3,8,10], time = [5,8,3,6]
     * Output: 62
     *
     * Input: l = 5, n = 5, k = 1, position = [0,1,2,3,5], time = [8,3,9,3,3]
     * Output: 34
     *
     * Constraints:
     *
     * 1 <= l <= 10^5
     * 2 <= n <= min(l + 1, 50)
     * 0 <= k <= min(n - 2, 10)
     * position.length == n
     * position[0] = 0 and position[n - 1] = l
     * position is sorted in strictly increasing order.
     * time.length == n
     * 1 <= time[i] <= 100
     * 1 <= sum(time) <= 100
     *
     * @param l
     * @param n
     * @param k
     * @param position
     * @param time
     * @return
     */
    // time = O(n^4), space = O(n^3)
    public int minTravelTime(int l, int n, int k, int[] position, int[] time) {
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + time[i - 1];

        int m = n - k;
        long[][][] f = new long[m + 1][n][n];
        long inf = (long)1E18;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], inf);
            }
        }
        for (int i = 1; i < n; i++) {
            long d = position[i] - position[0];
            f[2][0][i] = d * time[0];
        }

        for (int i = 2; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int u = j + 1; u < n; u++) {
                    if (f[i][j][u] == inf) continue;
                    for (int v = u + 1; v < n; v++) {
                        long ts = s[u + 1] - s[j + 1];
                        long d = position[v] - position[u];
                        f[i + 1][u][v] = Math.min(f[i + 1][u][v], f[i][j][u] + d * ts);
                    }
                }
            }
        }

        long res = inf;
        for (int i = 0; i < n; i++) res = Math.min(res, f[m][i][n - 1]);
        return (int)res;
    }
}
/**
 * 关键思路：一段连续的合并操作执行完后，合并到 time[i] 的时间是 time 的一个连续子数组，
 * 我们需要知道子数组的左端点是多少（右端点是 i），从而知道合并后的时间（每公里所需时间）是多少。
 * 从左到右模拟旅行的过程。我们需要知道如下信息：
 * 1. 还需要执行 leftK 次合并操作。
 * 2. 当前在 position[i]。
 * 3. 合并到 time[i] 的这段时间的左端点为 pre。
 * 定义 dfs(leftK,i,pre) 表示在上述情况下，完成剩余旅程需要的最小耗时。
 * 注意：每段路程的耗时是两部分的乘积：合并到 time[i] 的时间，当前位置到下一个位置的距离。这两个数据相对 i 是一左一右的关系，并不是都在 i 的右边!
 * 枚举下一个位置的下标 nxt=i+1,i+2,…,min(n−1,i+1+leftK)，这意味着我们执行了 nxt−i−1 次合并操作，问题变成：
 * 1. 还需要执行 leftK−(nxt−i−1) 次合并操作。
 * 2. 当前在 position[nxt]。
 * 3. 合并到 time[nxt] 的这段时间的左端点为 i+1。注意删除掉的下标范围是 [i+1,nxt−1]，这段时间合并到 time[nxt] 中。
 * 所以（对于下一趟路程来说）合并后的时间的下标范围为 [i+1,nxt]
 */