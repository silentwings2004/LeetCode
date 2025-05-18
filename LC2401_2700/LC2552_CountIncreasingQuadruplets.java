package LC2401_2700;
import java.util.*;
public class LC2552_CountIncreasingQuadruplets {
    /**
     * Given a 0-indexed integer array nums of size n containing all numbers from 1 to n, return the number of
     * increasing quadruplets.
     *
     * A quadruplet (i, j, k, l) is increasing if:
     *
     * 0 <= i < j < k < l < n, and
     * nums[i] < nums[k] < nums[j] < nums[l].
     *
     * Input: nums = [1,3,2,4,5]
     * Output: 2
     *
     * Input: nums = [1,2,3,4]
     * Output: 0
     *
     * Constraints:
     *
     * 4 <= nums.length <= 4000
     * 1 <= nums[i] <= nums.length
     * All the integers of nums are unique. nums is a permutation.
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] cs = new int[n][n], cb = new int[n][n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i + 1; j < n; j++) cb[i][j] = nums[i] < nums[j] ? ++cnt : cnt;
        }

        for (int i = n - 1; i >= 0; i--) {
            int cnt = 0;
            for (int j = i - 1; j >= 0; j--) cs[j][i] = nums[i] > nums[j] ? ++cnt : cnt;
        }

        long res = 0;
        for (int j = 1; j < n - 2; j++) {
            for (int k = j + 1; k < n - 1; k++) {
                if (nums[k] > nums[j]) continue;
                res += (cs[0][k] - cs[j][k]) * (cb[j][n - 1] - cb[j][k]);
            }
        }
        return res;
    }

    // S2: BIT
    // time = O(n^2*logn), space = O(n)
    final int N = 4010;
    int n;
    public long countQuadruplets2(int[] nums) {
        n = nums.length;
        int[] tr1 = new int[N];
        int[] tr2 = new int[N];

        long res = 0;
        for (int j = 0; j < n; j++) {
            tr2 = new int[N];
            for (int k = n - 1; k > j; k--) {
                if (nums[k] < nums[j]) {
                    res += (long) (query(tr1, nums[k] - 1)) * (query(tr2, n) - query(tr2, nums[j]));
                }
                add(tr2, nums[k], 1);
            }
            add(tr1, nums[j], 1);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int[] tr, int x, int c) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] += c;
    }

    private long query(int[] tr, int x) {
        long res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }

    // S3: dp
    // time = O(n^2), space = O(n^2)
    public long countQuadruplets3(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n + 2][n + 2];
        int[][] g = new int[n + 2][n + 2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums[i - 1] < j) f[i][j] = f[i - 1][j] + 1;
                else f[i][j] = f[i - 1][j];
            }
        }

        for (int i = n; i > 0; i--) {
            for (int j = 1; j <= n; j++) {
                if (nums[i - 1] > j) g[i][j] = g[i + 1][j] + 1;
                else g[i][j] = g[i + 1][j];
            }
        }

        long res = 0;
        for (int j = 1; j <= n; j++) {
            for (int k = j + 1; k <= n; k++) {
                if (nums[j - 1] > nums[k - 1]) {
                    res += f[j - 1][nums[k - 1]] * g[k + 1][nums[j - 1]];
                }
            }
        }
        return res;
    }

    // S4
    // time = O(n), space = O(n)
    public long countQuadruplets4(int[] nums) {
        int n = nums.length;
        long cnt4 = 0;
        long[] cnt3 = new long[n];
        for (int l = 2; l < n; l++) {
            long cnt2 = 0;
            for (int j = 0; j < l; j++) {
                if (nums[j] < nums[l]) {
                    cnt4 += cnt3[j];
                    cnt2++;
                } else cnt3[j] += cnt2;
            }
        }
        return cnt4;
    }
}
/**
 * 从数据规模来看，我们可以尝试n^2的时间复杂度。
 * 我们可以遍历两个变量，然后看其他两个变量能否快速得到
 * 通过尝试，我们可以试图遍历j和k的位置。
 * 一旦确定之后，就意味着我们需要在[1:j-1]里找有多少个小于nums[k]的元素，以及在[k+1:n]里找有多少个大于nums[j]的元素。
 * 将两者乘起来，就是类似(x,j,k,x)的组合的数目。
 * how many elements in [1:j] whose value < nums[k]
 * pre[i][v]: how many elements in [1:i] whose value < v
 * post[i][v]: how many elements in [j:n] whose value > v
 * All the integers of nums are unique. nums is a permutation.
 * x x x x x x i
 * if (nums[i] < v) dp[i][v] = dp[i-1][v] + 1
 * else dp[i][v] = dp[i-1][v]
 * x = dp1[j-1][nums[k]]
 * y = dp2[k+1][nums[j]]
 *
 * 1. 联想到 https://leetcode.cn/problems/maximum-score-of-a-node-sequence/
 * 2. 枚举中间的j和k
 * 3. 需要知道k右边有多少个比nums[j]大的数
 *          j左边有多少个比nums[k]小的数
 * 4. 预处理 great[k][x] 定义成在k右边的比x大的数的个数
 *          less[j][x] 定义成在j左边的比x小的数的个数
 *          less一边枚举j的时候，一边算
 */