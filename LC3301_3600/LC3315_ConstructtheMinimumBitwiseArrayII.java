package LC3301_3600;
import java.util.*;
public class LC3315_ConstructtheMinimumBitwiseArrayII {
    /**
     * You are given an array nums consisting of n prime integers.
     *
     * You need to construct an array ans of length n, such that, for each index i, the bitwise OR of ans[i] and ans[i]
     * + 1 is equal to nums[i], i.e. ans[i] OR (ans[i] + 1) == nums[i].
     *
     * Additionally, you must minimize each value of ans[i] in the resulting array.
     *
     * If it is not possible to find such a value for ans[i] that satisfies the condition, then set ans[i] = -1.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Input: nums = [2,3,5,7]
     * Output: [-1,1,4,3]
     *
     * Input: nums = [11,13,31]
     * Output: [9,12,15]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 2 <= nums[i] <= 10^9
     * nums[i] is a prime number.
     * @param nums
     * @return
     */
    // S1
    // time = O(32 * n), space = O(1)
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            int x = nums.get(i), j = 0;
            if ((x & 1) == 0) continue;
            while ((x >> j & 1) == 1) j++;
            j--;
            res[i] = x - (1 << j);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] minBitwiseArray2(List<Integer> nums) {
        int n = nums.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if ((nums.get(i) & 1) == 0) res[i] = -1;
            else {
                int x = ~nums.get(i);
                res[i] = nums.get(i) ^ ((x & -x) >> 1);
            }
        }
        return res;
    }
}