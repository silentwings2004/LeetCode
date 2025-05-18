package LC901_1200;
import java.util.*;
public class LC974_SubarraySumsDivisiblebyK {
    /**
     * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible
     * by k.
     *
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [4,5,0,-2,-3,1], k = 5
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 10^4
     * -10^4 <= nums[i] <= 10^4
     * 2 <= k <= 104
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0, s = 0;
        for (int x : nums) {
            s += x;
            int r = (s % k + k) % k;
            if (map.containsKey(r)) res += map.get(r);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return res;
    }
}
/**
 * 将区间和转化为前缀和来看，sum[i:j]能被K整除的充要条件就是：
 * presum[i]和presum[j-1]除以K的余数相同。
 * 我们用计数器记录i之前presum对K除的余数各自出现了多少次。
 * 如果presum[i]%K=r，那么count[r]就意味着有多少个位置j能配对成符合条件的区间。
 * 注意前缀和可能是负数，而C++中整除的余数允许是负数。
 * 所以我们在计算余数的时候需要转化为 (presum % k + k) % k。
 */
