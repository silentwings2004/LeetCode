package LC3901_4200;
import java.util.*;
public class LC4064_LongestSubarrayDivisiblebyKwithAtMostOneNegationII {
    /**
     * You are given an integer array nums and an integer k.
     *
     * A subarray is valid if its sum is divisible by k, or can become divisible by k by negating one element within
     * that subarray.
     *
     * Negating an element means replacing its value x with -x.
     *
     * Return the length of the longest valid subarray. If no valid subarray exists, return 0.
     *
     * A subarray is a contiguous, non-empty sequence of elements within an array.
     *
     * Input: nums = [4,1,2], k = 3
     * Output: 3
     *
     * Input: nums = [5,3,4], k = 7
     * Output: 2
     *
     * Input: nums = [2,2,5], k = 6
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * 1 <= k <= 3000
     * @param nums
     * @param k
     * @return
     */
    // time = O(n + k^2logn), space = O(n + k)
    public int longestSubarray(int[] nums, int k) {
        List<Integer>[] p = new List[k]; // 2*nums[i] % k 出现的所有位置
        for (int i = 0; i < k; i++) p[i] = new ArrayList<>();
        int[] fp = new int[k], lp = new int[k]; // 前缀和 % k 首次出现的位置 以及 前缀和 % k 最后一次出现的位置
        Arrays.fill(fp, -1);
        fp[0] = 0;

        int n = nums.length, sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i] % k + k; // 保证 x 非负
            p[x * 2 % k].add(i);
            sum = (sum + x) % k;
            int r = i + 1, l = fp[sum];
            if (l < 0) fp[sum] = r;
            else res = Math.max(res, r - l); // 不取反时的最大长度
            lp[sum] = r;
        }

        // 枚举 s[l] % k 和 s[r] % k，判断是否存在满足要求的 i
        for (int i = 0; i < k; i++) {
            int l = fp[i];
            if (l < 0) continue;
            for (int j = 0; j < k; j++) {
                int r = lp[j];
                if (r - l <= res) continue; // 最优性优化：res 无法增大

                // 2 * nums[i] % k = (sr - sl) % k
                List<Integer> q = p[(j - i + k) % k];  // +k 保证结果非负
                int idx = Collections.binarySearch(q, l);
                if (idx < 0) idx = ~idx; // 见 Collections.binarySearch 源码
                if (idx < q.size() && q.get(idx) < r) res = r - l;
            }
        }
        return res;
    }
}
/**
 * 取反 [L, R)
 * (SR - SL - 2 * ai) % k == 0
 * 0 <= L <= i < R <= n
 * s.t. max{R - L}
 * 本题 k ≤ 3000，我们可以枚举前缀和的值（而不是下标）。
 * 枚举 s[ℓ] mod k 和 s[r] mod k，那么贪心地：
 * 对于多个相同的 s[ℓ] mod k，选择 ℓ 最小的，这样不仅子数组更长，也更能包含满足要求的 i。
 * 对于多个相同的 s[r] mod k，选择 r 最大的，这样不仅子数组更长，也更能包含满足要求的 i。
 * 现在问题变成：判断在 [ℓ,r) 中是否存在 i，满足 ℓ≤i<r 且 s[r]−s[ℓ]−2⋅nums[i] 是 k 的倍数。
 * 我们可以把 2⋅nums[i] mod k 的所有出现位置保存到数组中，然后在这个数组中 二分查找 第一个 ≥ ℓ 的下标 i，
 * 如果发现 i < r，那么存在满足要求的 i，用子数组长度 r−ℓ 更新答案的最大值。
 * 最优性优化：在二分之前，如果发现 r−ℓ ≤ ans，那么 ans 不会变大，无需二分。
 *
 * 本质是一个三变量问题 (L, i, R) L <= i < R
 * 方法一：枚举 L 和 R (S[L] 只看最小的 L
 * 方法二：枚举 i 和 L ？ S[L] 只看最小的 L
 *       如果 nums[i] * 2 % k = nums[i + 1] * 2 % k
 *       且没有遇到新的前缀和 % k
 *       那么无需枚举 nums[i] 及其左边的前缀和
 *       O(k^2) 次枚举
 *       1. 所有东西都用集合保存：O(min(n, k)^2) 次枚举
 *       2. 这里用集合，是用到了
 *       a) 判断元素之前是否遇到过，布尔数组 判断 nums[i] * 2 % k 是否遇到过 O(k)
 *       b) 遍历这些去重后的元素   数组  雨大哦一个新的就加到数组中 min(n,k)
 *       在遍历 nums 的过程中，至多遇到 k 个 % k 结果与之前的前缀和 % k 不同的前缀和
 */