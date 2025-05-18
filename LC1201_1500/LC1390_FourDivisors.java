package LC1201_1500;

import java.util.HashSet;

public class LC1390_FourDivisors {
    /**
     * Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four
     * divisors. If there is no such integer in the array, return 0.
     *
     * Input: nums = [21,4,7]
     * Output: 32
     *
     * Input: nums = [21,21]
     * Output: 64
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n * sqrt(k)), space = O(1)
    public int sumFourDivisors(int[] nums) {
        int res = 0;
        for (int x : nums) res += check(x);
        return res;
    }

    private int check(int x) {
        int cnt = 2, s = x + 1, t = x;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                cnt++;
                s += i;
                if (x / i != i) {
                    cnt++;
                    s += x / i;
                }
            }
        }
        if (x > 1 && x != t) {
            cnt++;
            s += x;
        }
        return cnt == 4 ? s : 0;
    }
}