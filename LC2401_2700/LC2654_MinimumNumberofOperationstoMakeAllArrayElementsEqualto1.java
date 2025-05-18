package LC2401_2700;

public class LC2654_MinimumNumberofOperationstoMakeAllArrayElementsEqualto1 {
    /**
     * You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the
     * array any number of times:
     *
     * Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
     * Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.
     *
     * The gcd of two integers is the greatest common divisor of the two integers.
     *
     * Input: nums = [2,6,3,4]
     * Output: 4
     *
     * Input: nums = [2,10,6,14]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 50
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n^2*logn), space = O(1)
    final int INF = (int) 1e9;
    public int minOperations(int[] nums) {
        int n = nums.length, one = 0;
        for (int x : nums) {
            if (x == 1) one++;
        }
        if (one > 0) return n - one;

        int res = INF;
        for (int i = 0; i < n; i++) {
            int a = nums[i], cnt = 0;
            for (int j = i; j + 1 < n; j++) {
                if (a == 1) res = Math.min(res, cnt + n - 1);
                int b = nums[j + 1];
                a = gcd(a, b);
                cnt++;
            }
            if (a == 1) res = Math.min(res, cnt + n - 1);
        }
        return res == INF ? -1 : res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}