package LC3601_3900;

public class LC3678_SmallestAbsentPositiveGreaterThanAverage {
    /**
     * You are given an integer array nums.
     *
     * Return the smallest absent positive integer in nums such that it is strictly greater than the average of all
     * elements in nums.
     *
     * The average of an array is defined as the sum of all its elements divided by the number of elements.
     *
     * Input: nums = [3,5]
     * Output: 6
     *
     * Input: nums = [-1,1,2]
     * Output: 3
     *
     * Input: nums = [4,-1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n + k), space = O(k)
    public int smallestAbsent(int[] nums) {
        int n = nums.length, s = 0;
        boolean[] st = new boolean[102];
        for (int x: nums) {
            s += x;
            if (x > 0) st[x] = true;
        }
        int x = Math.max(0, s / n) + 1;
        while (st[x]) x++;
        return x;
    }
}