package LC2700_3000;
import java.util.*;
public class LC2845_CountofInterestingSubarrays {
    /**
     * You are given a 0-indexed integer array nums, an integer modulo, and an integer k.
     *
     * Your task is to find the count of subarrays that are interesting.
     *
     * A subarray nums[l..r] is interesting if the following condition holds:
     *
     * Let cnt be the number of indices i in the range [l, r] such that nums[i] % modulo == k. Then, cnt % modulo == k.
     * Return an integer denoting the count of interesting subarrays.
     *
     * Note: A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [3,2,4], modulo = 2, k = 1
     * Output: 3
     *
     * Input: nums = [3,1,9,6], modulo = 3, k = 0
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= modulo <= 10^9
     * 0 <= k < modulo
     * @param nums
     * @param modulo
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, 1L);
        long res = 0;
        for (int i = 0, s = 0; i < n; i++) {
            int t = nums.get(i) % modulo;
            s += t == k ? 1 : 0;
            int r = s % modulo;
            res += map.getOrDefault((r - k + modulo) % modulo, 0L);
            map.put(r, map.getOrDefault(r, 0L) + 1);
        }
        return res;
    }
}
/**
 * 1. 转换
 * 如果 nums[i] % m = k => nums[i] = 1, nums[i] = 0
 * cnt = 子数组的元素和
 * 2. 前缀和
 * nums[L] + ... + nums[R]
 * 3. 取模
 * (s[R] - s[L]) % m = k
 * 4. 式子变形
 */
