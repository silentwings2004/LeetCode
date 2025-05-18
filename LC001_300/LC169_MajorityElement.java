package LC001_300;
import java.util.*;
public class LC169_MajorityElement {
    /**
     * Given an array nums of size n, return the majority element.
     *
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority
     * element always exists in the array.
     *
     * Input: nums = [3,2,3]
     * Output: 3
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 5 * 10^4
     * -2^31 <= nums[i] <= 2^31 - 1
     *
     *
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     * @param nums
     * @return
     */
    // Moore voting algorithm
    // time = O(n), space = O(1)
    public int majorityElement(int[] nums) {
        int r = 0, c = 0;
        for (int x : nums) {
            if (c == 0) {
                r = x;
                c = 1;
            } else if (r == x) c++;
            else c--;
        }
        return r;
    }
}
/**
 * r: 当前存的数
 * c: 当前数的数量
 */