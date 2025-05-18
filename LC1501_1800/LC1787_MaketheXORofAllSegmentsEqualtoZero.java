package LC1501_1800;
import java.util.*;
public class LC1787_MaketheXORofAllSegmentsEqualtoZero {
    /**
     * You are given an array nums and an integer k. The XOR of a segment [left, right] where left <= right is the XOR
     * of all the elements with indices between left and right, inclusive: nums[left] XOR nums[left+1] XOR ... XOR
     * nums[right].
     *
     * Return the minimum number of elements to change in the array such that the XOR of all segments of size k is
     * equal to zero.
     *
     * Input: nums = [1,2,0,3,0], k = 1
     * Output: 3
     *
     * Input: nums = [3,4,5,2,1,7,3,4,7], k = 3
     * Output: 3
     *
     * Input: nums = [1,2,4,1,2,5,1,2,6], k = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 2000
     * 0 <= nums[i] < 2^10
     * @param nums
     * @param k
     * @return
     */
    final int M = 1024, INF = (int) 1e8;
    int[] s;
    public int minChanges(int[] nums, int k) {
        int n = nums.length, m = (n + k - 1) / k;
        s = new int[M];
        int[][] f = new int[k + 1][M];
        for (int i = 0; i <= k; i++) Arrays.fill(f[i], INF);
        f[0][0] = 0;

        int sum  = 0, minv = INF;
        for (int i = 1; i <= k; i++) {
            Arrays.fill(s, 0);
            int len = m;
            if (n % k != 0 && n % k < i) len--;
            for (int j = 0; j < len; j++) {
                s[nums[j * k + i - 1]]++;
            }
            int maxv = 0;
            for (int j = 0; j < M; j++) {
                if (s[j] > 0) maxv = Math.max(maxv, s[j]);
            }
            sum += len - maxv;
            minv = Math.min(minv, maxv);

            for (int j = 0; j < M; j++) {
                for (int u = 0; u < len; u++) {
                    int x = nums[u * k + i - 1], cost = len - s[x];
                    f[i][j] = Math.min(f[i][j], f[i - 1][j ^ x] + cost);
                }
            }
        }
        return Math.min(sum + minv, f[k][0]);
    }
}
/**
 * a^s = 0, b^s = 0;
 * (a^s)^(b^s) = a^b = 0 => a = b
 * 以k为周期
 * 折叠起来后让每个数变得相同，至少需要操作多少次
 * 可以用贪心来做
 * 不考虑亦或和的情况下是独立的 => 把一列中所有的数变成重数
 * 1. 某一列用了一个全新的数 => 贪心 可以变成任何数 => 变成其他数的xor，这样整个区间的xor = 0 => 选哪一列？重数最少的一列 minvi
 * 2. 每一列都用原来的数 => 直接dp 前i列变成亦或和 = j，总操作次数最少是多少？枚举第i列的选法即可。
 * 列数 * 每次选法数量 = n => O(n^2)
 */