package LC2401_2700;
import java.util.*;
public class LC2588_CounttheNumberofBeautifulSubarrays {
    /**
     * You are given a 0-indexed integer array nums. In one operation, you can:
     *
     * Choose two different indices i and j such that 0 <= i, j < nums.length.
     * Choose a non-negative integer k such that the kth bit (0-indexed) in the binary representation of nums[i] and
     * nums[j] is 1.
     * Subtract 2k from nums[i] and nums[j].
     * A subarray is beautiful if it is possible to make all of its elements equal to 0 after applying the above
     * operation any number of times.
     *
     * Return the number of beautiful subarrays in the array nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [4,3,1,2,4]
     * Output: 2
     *
     * Input: nums = [1,10,4]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public long beautifulSubarrays(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long res = 0;
        int state = 0;
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                if ((x >> i & 1) == 1) {
                    if ((state >> i & 1) == 1) state -= 1 << i;
                    else state |= 1 << i;
                }
            }
            res += map.getOrDefault(state, 0);
            map.put(state, map.getOrDefault(state, 0) + 1);
        }
        return res;
    }
}
/**
 * 找符合条件的subarray的数目，使得subarray里面的元素，在每个二进制bit上出现1的次数都是偶数.
 * 我们每次操作可以消灭一对在某个bit上的1，操作若干次之后会把所有bit上的1都消灭。
 * => 转化为前缀和之差的形式
 * 假设某个subarray [a:b]是符合要求的，
 * 那么意味着前缀[0:b]在每个bit上出现1的次数的奇偶性，必然与前缀[0:a-1]的奇偶性相同。
 */