package LC3601_3900;

public class LC3866_FirstUniqueEvenElement {
    /**
     * You are given an integer array nums.
     *
     * Return an integer denoting the first even integer (earliest by array index) that appears exactly once in nums.
     * If no such integer exists, return -1.
     *
     * An integer x is considered even if it is divisible by 2.
     *
     * Input: nums = [3,4,2,5,4,6]
     * Output: 2
     *
     * Input: nums = [4,4]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int firstUniqueEven(int[] nums) {
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int[] cnt = new int[mx + 1];
        for (int x : nums) cnt[x]++;
        for (int x : nums) {
            if (x % 2 == 0 && cnt[x] == 1) return x;
        }
        return -1;
    }
}