package LC2101_2400;
import java.util.*;
public class LC2197_ReplaceNonCoprimeNumbersinArray {
    /**
     * You are given an array of integers nums. Perform the following steps:
     *
     * Find any two adjacent numbers in nums that are non-coprime.
     * If no such numbers are found, stop the process.
     * Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
     * Repeat this process as long as you keep finding two adjacent non-coprime numbers.
     * Return the final modified array. It can be shown that replacing adjacent non-coprime numbers in any arbitrary
     * order will lead to the same result.
     *
     * The test cases are generated such that the values in the final array are less than or equal to 108.
     *
     * Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.
     *
     * Input: nums = [6,4,3,2,7,6,2]
     * Output: [12,7,6]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * The test cases are generated such that the values in the final array are less than or equal to 10^8.
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(1)
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stk = new ArrayList<>();
        for (int x : nums) {
            while (!stk.isEmpty() && gcd(stk.get(stk.size() - 1), x) != 1) {
                int y = stk.get(stk.size() - 1);
                stk.remove(stk.size() - 1);
                x = lcm(x, y);
            }
            stk.add(x);
        }
        return stk;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        int g = gcd(a, b);
        return a / g * b;
    }
}
/**
 * 合并相邻元素 -> 栈
 */
