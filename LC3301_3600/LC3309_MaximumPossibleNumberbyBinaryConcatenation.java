package LC3301_3600;
import java.util.*;
public class LC3309_MaximumPossibleNumberbyBinaryConcatenation {
    /**
     * You are given an array of integers nums of size 3.
     *
     * Return the maximum possible number whose binary representation can be formed by concatenating the binary
     * representation of all elements in nums in some order.
     *
     * Note that the binary representation of any number does not contain leading zeros.
     *
     * Input: nums = [1,2,3]
     * Output: 30
     *
     * Input: nums = [2,8,16]
     * Output: 1296
     *
     * Constraints:
     *
     * nums.length == 3
     * 1 <= nums[i] <= 127
     * @param nums
     * @return
     */
    // S1: brute force
    // time = O(1), space = O(1)
    public int maxGoodNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == i) continue;
                for (int k = 0; k < 3; k++) {
                    if (k == i || k == j) continue;
                    res = Math.max(res, cal(convert(nums[i]) + convert(nums[j]) + convert(nums[k])));
                }
            }
        }
        return res;
    }

    private int cal(String s) {
        int t = 0;
        for (int i = 0; i < s.length(); i++) t = t * 2 + s.charAt(i) - '0';
        return t;
    }

    private String convert(int x) {
        return Integer.toBinaryString(x);
    }

    // S2
    // time = O(nlogn), space = O(1)
    public int maxGoodNumber2(int[] nums) {
        Integer[] arr = new Integer[]{nums[0], nums[1], nums[2]};
        Arrays.sort(arr, (o1, o2) -> {
            int len1 = 32 - Integer.numberOfLeadingZeros(o1);
            int len2 = 32 - Integer.numberOfLeadingZeros(o2);
            int ba = o2 << len1 | o1;
            int ab = o1 << len2 | o2;
            return ba - ab;
        });

        int res = 0;
        for (int x : arr) {
            int len = 32 - Integer.numberOfLeadingZeros(x);
            res = res << len | x;
        }
        return res;
    }
}