package LC2700_3000;
import java.util.*;
public class LC2859_SumofValuesatIndicesWithKSetBits {
    /**
     * You are given a 0-indexed integer array nums and an integer k.
     *
     * Return an integer that denotes the sum of elements in nums whose corresponding indices have exactly k set bits
     * in their binary representation.
     *
     * The set bits in an integer are the 1's present when it is written in binary.
     *
     * For example, the binary representation of 21 is 10101, which has 3 set bits.
     *
     * Input: nums = [5,10,1,5,2], k = 1
     * Output: 13
     *
     * Input: nums = [4,3,2,1], k = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10……5
     * 0 <= k <= 10
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int n = nums.size(), res = 0;
        for (int i = 0; i < n; i++) {
            if (Integer.bitCount(i) == k) res += nums.get(i);
        }
        return res;
    }
}