package LC3001_3300;
import java.util.*;
public class LC3224_MinimumArrayChangestoMakeDifferencesEqual {
    /**
     * You are given an integer array nums of size n where n is even, and an integer k.
     *
     * You can perform some changes on the array, where in one change you can replace any element in the array with any
     * integer in the range from 0 to k.
     *
     * You need to perform some changes (possibly none) such that the final array satisfies the following condition:
     *
     * There exists an integer X such that abs(a[i] - a[n - i - 1]) = X for all (0 <= i < n).
     * Return the minimum number of changes required to satisfy the above condition.
     *
     * Input: nums = [1,0,1,2,4,3], k = 4
     * Output: 2
     *
     * Input: nums = [0,1,2,3,3,6,5,4], k = 6
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 10^5
     * n is even.
     * 0 <= nums[i] <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minChanges(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] b = new int[100010];
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int v = Math.abs(nums[i] - nums[j]);
            map.put(v, map.getOrDefault(v, 0) + 1);
            int[] x = new int[]{nums[i], nums[j], Math.abs(nums[i] - k), Math.abs(nums[j] - k)};
            Arrays.sort(x);
            int l = x[0], r = x[3];
            if (Math.min(nums[i], nums[j]) <= k) l = 0;
            b[l]++;
            b[r + 1]--;
            if (l > 0) {
                b[0] += 2;
                b[l] -= 2;
            }
            if (r < k) {
                b[r + 1] += 2;
                b[k + 1] -= 2;
            }
        }

        int res = n;
        for (int i = 0, s = 0; i <= k; i++) {
            s += b[i];
            res = Math.min(res, s - map.getOrDefault(i, 0));
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int minChanges2(int[] nums, int k) {
        int n = nums.length;
        int[] b = new int[k + 2];
        for (int i = 0; i < n / 2; i++) {
            int p = nums[i], q = nums[n - i - 1];
            if (p > q) {
                int t = p;
                p = q;
                q = t;
            }
            int x = q - p;
            int mx = Math.max(q, k - p);
            b[0]++;
            b[x]--;
            b[x + 1]++;
            b[mx + 1]--;
            b[mx + 1] += 2;
        }
        int res = 0x3f3f3f3f;
        for (int i = 0, s = 0; i <= k; i++) {
            s += b[i];
            res = Math.min(res, s);
        }
        return res;
    }
}
/**
 * 1. 枚举 x
 * 从特殊到一般
 * 如果答案是 0， 那么 nums 得是什么样的？
 * 我把 nums[i] 与 nums[n - i - 1] 看成一对
 * 如果所有 n / 2 个数的绝对差都是一样的，那么无需操作，答案是 0
 * (p, q) = (nums[i], nums[n - 1 - i])
 * 不妨设 p <= q (如果 p > q 就交换)
 * (0) q - p == x => 无需修改
 * (1) q - p > x => 修改一个数即可
 * (2) q - p < x and max(q, k - p) >= x => 修改一个数即可
 * (3) q - p < x and max(q, k - p) < x => 修改2个数 (比如 p = 0, q = x)
 *
 * 枚举 x = 0, 1, 2, 3, ... k
 * x == 0: 只需要知道有多少对 (p,q) q - p != 0 的即可
 *         如果我知道有多少对 (p,q) q - p == 0
 *         修改次数 = n / 2 - cnt[0]
 *
 *         统计 max(q, k - p) 的出现次数
 *
 * x == 1: 只需要知道有多少对 (p,q) q - p != x 的即可
 *         如果我知道有多少对 (p,q) q - p == x
 *         修改次数 = n / 2 - cnt[1] + (max(q, k - p) == 0 的 (p,q) 的对数)
 * 一般情况：
 * 最少要修改 n / 2 - cnt[x] + cnt2[0] + cnt2[1] + ... + cnt2[x - 1] 次
 *          n / 2 - cnt[x] + sum2
 *
 * 2. 差分数组
 * 枚举 (p,q) 如果要把 (p,q) 改成别的，它要操作几次
 * [0,x-1] 都 +1
 * [x+1,k] 都 -1
 */
