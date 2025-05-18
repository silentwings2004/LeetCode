package LC2700_3000;

public class LC2786_VisitArrayPositionstoMaximizeScore {
    /**
     * You are given a 0-indexed integer array nums and a positive integer x.
     *
     * You are initially at position 0 in the array and you can visit other positions according to the following rules:
     *
     * If you are currently in position i, then you can move to any position j such that i < j.
     * For each position i that you visit, you get a score of nums[i].
     * If you move from a position i to a position j and the parities of nums[i] and nums[j] differ, then you lose a
     * score of x.
     * Return the maximum total score you can get.
     *
     * Note that initially you have nums[0] points.
     *
     * Input: nums = [2,3,6,1,9,2], x = 5
     * Output: 13
     *
     * Input: nums = [2,4,6,8], x = 3
     * Output: 20
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i], x <= 10^6
     * @param nums
     * @param x
     * @return
     */
    // time = O(n), space = O(n)
    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long[][] f = new long[n][2];
        if (nums[0] % 2 == 0) {
            f[0][0] = nums[0];
            f[0][1] = -x;
        } else {
            f[0][0] = -x;
            f[0][1] = nums[0];
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == 0) {
                f[i][0] = Math.max(f[i - 1][0] + nums[i], f[i - 1][1] + nums[i] - x);
                f[i][1] = f[i - 1][1];
            } else {
                f[i][0] = f[i - 1][0];
                f[i][1] = Math.max(f[i - 1][1] + nums[i], f[i - 1][0] + nums[i] - x);
            }
        }
        return Math.max(f[n - 1][0], f[n - 1][1]);
    }
}