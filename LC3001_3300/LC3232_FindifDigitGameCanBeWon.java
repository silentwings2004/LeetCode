package LC3001_3300;

public class LC3232_FindifDigitGameCanBeWon {
    /**
     * You are given an array of positive integers nums.
     *
     * Alice and Bob are playing a game. In the game, Alice can choose either all single-digit numbers or all
     * double-digit numbers from nums, and the rest of the numbers are given to Bob. Alice wins if the sum of her
     * numbers is strictly greater than the sum of Bob's numbers.
     *
     * Return true if Alice can win this game, otherwise, return false.
     *
     * Input: nums = [1,2,3,4,10]
     * Output: false
     *
     * Input: nums = [1,2,3,4,5,14]
     * Output: true
     *
     * Input: nums = [5,5,5,25]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 99
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canAliceWin(int[] nums) {
        int s1 = 0, s2 = 0;
        for (int x : nums) {
            if (x < 10) s1 += x;
            else s2 += x;
        }
        return s1 != s2;
    }
}