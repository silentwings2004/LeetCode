package LC2401_2700;
import java.util.*;
public class LC2488_CountSubarraysWithMedianK {
    /**
     * You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.
     *
     * Return the number of non-empty subarrays in nums that have a median equal to k.
     *
     * Note:
     *
     * The median of an array is the middle element after sorting the array in ascending order. If the array is of even
     * length, the median is the left middle element.
     * For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [3,2,1,4,5], k = 4
     * Output: 3
     *
     * Input: nums = [2,3,1], k = 3
     * Output: 1
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * 1 <= nums[i], k <= n
     * The integers in nums are distinct.
     * @param nums
     * @param k
     * @return
     */
    // S1:
    // time = O(n), space = O(n)
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[2 * n + 1];
        cnt[n]++;

        int res = 0, s = n;
        boolean flag = false;
        for (int x : nums) {
            if (x > k) s++;
            else if (x < k) s--;
            if (x == k) flag = true;
            if (!flag) cnt[s]++; // 遇到中位数之后，不能再累加，因为两点之间必须包括中位数k
            else res += cnt[s] + cnt[s - 1];
        }
        return res;
    }

    // S2:
    // time = O(n), space = O(n)
    public int countSubarrays2(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] < k) nums[i] = -1;
            else if (nums[i] == k) nums[i] = 0;
            else nums[i] = 1;
        }

        HashMap<Integer, Integer> odd = new HashMap<>();
        HashMap<Integer, Integer> even = new HashMap<>();
        even.put(0, 1);

        int s = 0, res = 0;
        for (int i = 0; i < n; i++) {
            s += nums[i];
            if (i % 2 == 0) { // 长度为奇数个,注意idx = 0, 2...分别代表元素有1个，3个...
                res += even.getOrDefault(s, 0);
                res += odd.getOrDefault(s - 1, 0);
                odd.put(s, odd.getOrDefault(s, 0) + 1);

            } else {
                res += odd.getOrDefault(s, 0);
                res += even.getOrDefault(s - 1, 0);
                even.put(s, even.getOrDefault(s, 0) + 1);
            }
        }
        return res;
    }
}
/**
 * 与nums的具体数值无关，只关心大小比较
 * 比k小的-1，和k一样的0，比k大的就是1
 * nums => -1 0 1
 * [-1 -1 0 1 1] => subarray sum = 0, odd length
 * [-1,-1,0,1,1,1] => subarray sum = 1, even length
 * subarray[i+1:j] sum => presum[j] - presum[i]
 * x x x [x x x x]  找左边界
 *     j  ?     i
 * presum[i] - 0
 * prsum_even[s]: how many prefixes whose sum equals s && length is even
 * prsum_odd[s]: how many prefixes whose sum equals s && length is odd
 * if [0:i] length is odd:
 *    res += presum_even[s-0]
 *    res += presum_odd[s-1]
 * if [0:i] length is even:
 *    res += presum_odd[s-0]
 *    res += presum_even[s-1]
 * [x x x x x x] => presum[i] - presum[?]
 *            i
 * 展开成一个数学式子
 * 中位数 = (奇数长度) 小于k的个数 = 大于k的个数
 * 偶数长度 小于k的个数 + 1 = 大于k的个数
 * 左侧小于k的个数 + 右侧小于k的个数 = 左侧大于k的个数 + 右侧大于k的个数
 * => +左侧小于k的个数 - 左侧大于k的个数 = +右侧大于k的个数 - 右侧小于k的个数
 */
