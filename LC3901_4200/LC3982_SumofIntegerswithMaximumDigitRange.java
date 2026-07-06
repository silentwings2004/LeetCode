package LC3901_4200;

public class LC3982_SumofIntegerswithMaximumDigitRange {
    /**
     * You are given an integer array nums.
     *
     * The digit range of an integer is defined as the difference between its largest digit and smallest digit.
     *
     * For example, the digit range of 5724 is 7 - 2 = 5.
     *
     * Return the sum of all integers in nums whose digit range is equal to the maximum digit range among all integers
     * in the array.
     *
     * Input: nums = [5724,111,350]
     * Output: 6074
     *
     * Input: nums = [90,900]
     * Output: 990
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 10 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogU), space = O(1) U: max(nums)
    public int maxDigitRange(int[] nums) {
        int[] w = new int[10];
        int mx = 0;
        for (int x : nums) {
            int v = get(x);
            w[v] += x;
            mx = Math.max(mx, v);
        }
        return w[mx];
    }

    private int get(int x) {
        int a = 9, b = 0;
        while (x > 0) {
            int v = x % 10;
            x /= 10;
            a = Math.min(a, v);
            b = Math.max(b, v);
        }
        return b - a;
    }
}