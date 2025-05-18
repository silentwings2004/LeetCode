package LC1501_1800;
import java.util.*;
public class LC1590_MakeSumDivisiblebyP {
    /**
     * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the
     * remaining elements is divisible by p. It is not allowed to remove the whole array.
     *
     * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
     *
     * A subarray is defined as a contiguous block of elements in the array.
     *
     * Input: nums = [3,1,4,2], p = 6
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= p <= 10^9
     * @param nums
     * @param p
     * @return
     */
    // time = O(n), space = O(n)
    public int minSubarray(int[] nums, int p) {
        int n = nums.length, s = 0;
        for (int x : nums) s = (s + x) % p;
        if (s == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, res = n;
        for (int i = 0; i < n; i++) {
            sum = (sum + nums[i]) % p;
            int v = (sum - s + p) % p;
            if (map.containsKey(v)) res = Math.min(res, i - map.get(v));
            map.put(sum, i);
        }
        return res == n ? -1 : res;
    }
}
/**
 * x x x [x x x x] x x x
 * totalSum % p = r0
 * 本质上就是把r0求出来，找一个最小的窗口，被p除得到的余数是r0
 * 利用前缀和+hash+remainder as key
 * subarray只要求2个端点
 * x x x [i x x j] x x x
 * 固定j，来找到i
 * i越靠近j越好
 * 怎么去搜索呢？
 * 需要精准定位i
 * 一种套路方法：把区间和转化成前缀和的差值
 * sum[i:j] = presum[j] - presum[i - 1]
 *  r0           r          r - r0
 *  3            1           -2 + 5 -> 加上一个余数周期， 余3 + 余3 = 余6 -> 余1
 * map[r'] = i
 * 套路：prefix + hash + mod
 * 用余数来作为key
 * Hash[prefix[index] % mod] = index
 */