package LC2700_3000;
import java.util.*;
public class LC2708_MaximumStrengthofaGroup {
    /**
     * You are given a 0-indexed integer array nums representing the score of students in an exam. The teacher would
     * like to form one non-empty group of students with maximal strength, where the strength of a group of students of
     * indices i0, i1, i2, ... , ik is defined as nums[i0] * nums[i1] * nums[i2] * ... * nums[ik].
     *
     * Return the maximum strength of a group the teacher can create.
     *
     * Input: nums = [3,-1,-5,2,5,-9]
     * Output: 1350
     *
     * Input: nums = [-4,-5,-4]
     * Output: 20
     *
     * Constraints:
     *
     * 1 <= nums.length <= 13
     * -9 <= nums[i] <= 9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long maxStrength(int[] nums) {
        int pos = 0, neg = 0, zero = 0;
        long res = 1, t = -10;
        for (int x : nums) {
            if (x > 0) {
                pos++;
                res *= x;
            } else if (x < 0) {
                res *= x;
                neg++;
                t = Math.max(t, x);
            } else if (x == 0) zero++;
        }

        if (pos == 0 && neg == 0) return 0;
        if (pos == 0 && neg == 1) return zero > 0 ? 0 : res;
        if (neg % 2 == 1) res /= t;
        return res;
    }

    // S2: 状态压缩
    // time = O(2^n), space = O(1)
    public long maxStrength2(int[] nums) {
        int n = nums.length;
        long res = Long.MIN_VALUE;
        for (int i = 1; i < 1 << n; i++) {
            long t = 1;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) t *= nums[j];
            }
            res = Math.max(res, t);
        }
        return res;
    }

    // S3
    // time = O(n), space = O(1)
    public long maxStrength3(int[] nums) {
        int n = nums.length;
        long mn = nums[0], mx = nums[0];
        for (int i = 1; i < n; i++) {
            long x = nums[i], tmn = mn, tmx = mx;
            mn = Math.min(tmn, Math.min(x, Math.min(tmn * x, tmx * x)));
            mx = Math.max(tmx, Math.max(x, Math.max(tmn * x, tmx * x)));
        }
        return mx;
    }
}