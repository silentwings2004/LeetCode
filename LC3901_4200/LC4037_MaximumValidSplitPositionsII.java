package LC3901_4200;

public class LC4037_MaximumValidSplitPositionsII {
    /**
     * You are given an integer array nums.
     *
     * You may remove at most one element from nums. Let arr be the array of remaining elements in their original order,
     * and let m be its length.
     *
     * A split position i of arr is valid if:
     *
     * 0 <= i < m - 1, and
     * gcd(arr[0..i]) == gcd(arr[i + 1..m - 1]).
     * An array of length 1 has no valid split positions.
     *
     * The score of arr is the number of valid split positions in it.
     *
     * Return the maximum possible score of arr.
     *
     * Here, gcd(a) denotes the greatest common divisor of all elements in the array a.
     *
     * Input: nums = [10,30,15,10]
     * Output: 2
     *
     * Input: nums = [2,10,14]
     * Output: 1
     *
     * Input: nums = [2,4]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n + logU), space = O(n)  U: max{nums}
    public int maxValidSplits(int[] nums) {
        int res = helper(nums, -1);

        int n = nums.length, g = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (g > 0 && x % g == 0) continue;
            g = gcd(g, x);
            res = Math.max(res, helper(nums, i));
        }
        return res;
    }

    private int helper(int[] nums, int skip) {
        int n = nums.length;
        int[] suf = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (i != skip) suf[i] = gcd(suf[i + 1], nums[i]);
            else suf[i] = suf[i + 1];
        }

        int pre = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i != skip) {
                pre = gcd(pre, nums[i]);
                if (pre == suf[i + 1]) cnt++;
            }
        }
        return cnt;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * 性质一：对于合法分割，前缀 GCD = 后缀 GCD = 整个数组的 GCD
 * a = P + Q
 * gcd(P) = gcd(Q) = G
 * gcd(a) = gcd(P + Q) = gcd(gcd(P), gcd(Q)) = gcd(G, G) = G
 * gcd(P) = gcd(Q) = gcd(a)
 *
 * 性质二：
 * 定义 pre[i] = gcd(nums[0,i])
 * 定义 suf[i] = gcd(nums[i,n-1])
 * gcd(nums[0,i]) = gcd(gcd(nums[0,i-1]), nums[i])
 * pre[i] = gcd(pre[i-1], nums[i])
 * 如果发现 pre[i] = pre[i-1], 是否意味着不删除 nums[i] 更好呢？
 * 设 G = gcd(nums)，根据 GCD 的定义，nums[i] 就是 G 的倍数
 * gcd(nums) = gcd(pre[i], suf[i+1]) = gcd(pre[i-1], suf[i+1]) = 删除 nums[i] 后的剩余 n-1 个数的 GCD = G
 * 删除 nums[i] 后，合法分割是否变多
 * 如果删除 nums[i] 后，每个合法分割都能对应上原数组 nums 中的合法分割，那么不删更好
 * 由于删除的 nums[i] 是 G 的倍数，所以把 nums[i] 重新插入删除后的数组，合法分割的前缀 GCD = 后缀 GCD = G，不变，所以还是合法分割
 * 结论：如果 i > 0 且 pre[i] = pre[i-1], 那么删除后的数组的得分 (合法分割个数) <= nums 的得分(不删的得分)
 * 关键剪枝：如果 i = 0 或者 pre[i] != pre[i-1]，那么才考虑删除 nums[i]
 * 只需枚举 logU 次
 * O（n * (n + logU)) => O(logU * (n + logU))  U: max(nums)
 * 时间复杂度：枚举 O(logU)个下标，每次 O(n + logU) 时间
 */