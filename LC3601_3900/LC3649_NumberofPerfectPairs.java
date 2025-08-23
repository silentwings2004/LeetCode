package LC3601_3900;
import java.util.*;
public class LC3649_NumberofPerfectPairs {
    /**
     * You are given an integer array nums.
     *
     * A pair of indices (i, j) is called perfect if the following conditions are satisfied:
     *
     * i < j
     * Let a = nums[i], b = nums[j]. Then:
     * min(|a - b|, |a + b|) <= min(|a|, |b|)
     * max(|a - b|, |a + b|) >= max(|a|, |b|)
     * Return the number of distinct perfect pairs.
     *
     * Note: The absolute value |x| refers to the non-negative value of x.
     *
     * Input: nums = [0,1,2,3]
     * Output: 2
     *
     * Input: nums = [-3,2,-1,4]
     * Output: 4
     *
     * Input: nums = [1,10,100,1000]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long perfectPairs(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] = Math.abs(nums[i]);
        Arrays.sort(nums);

        long res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (nums[j] * 2 < nums[i]) j++;
            res += i - j;
        }
        return res;
    }
}
/**
 * |A-B| = |a-b|
 * |A+B| = |A-(-B)| = |AB'|
 * |OA| = |a|
 * |OB| = |b|
 * if a * b >= 0 => |AB'| = |OA| + |OB'| = |a| + |b|
 * max(|a - b|, |a + b|) = |a| + |b| >= max(|a|, |b|)  => 恒成立
 * min(|a - b|, |a + b|) = abs(|a| - |b|) <= min(|a|, |b|)
 * x = |a|
 * y = |b|
 * |x-y| <= min(x, y)
 * 0 <= x <= y <= 2x
 * y - x <= x
 * 这个式子和顺序无关 => 排序
 */