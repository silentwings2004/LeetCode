package LC901_1200;
import java.util.*;
public class LC1018_BinaryPrefixDivisibleBy5 {
    /**
     * You are given a binary array nums (0-indexed).
     *
     * We define xi as the number whose binary representation is the subarray nums[0..i] (from most-significant-bit to
     * least-significant-bit).
     *
     * For example, if nums = [1,0,1], then x0 = 1, x1 = 2, and x2 = 5.
     * Return an array of booleans answer where answer[i] is true if xi is divisible by 5.
     *
     * Input: nums = [0,1,1]
     * Output: [true,false,false]
     *
     * Input: nums = [1,1,1]
     * Output: [false,false,false]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * nums[i] is either 0 or 1.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0, r = 0; i < n; i++) {
            r = r * 2 + nums[i];
            r %= 5;
            if (r == 0) res.add(true);
            else res.add(false);
        }
        return res;
    }
}