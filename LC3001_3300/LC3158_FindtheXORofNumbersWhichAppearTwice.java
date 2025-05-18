package LC3001_3300;

public class LC3158_FindtheXORofNumbersWhichAppearTwice {
    /**
     * You are given an array nums, where each number in the array appears either once or twice.
     *
     * Return the bitwise XOR of all the numbers that appear twice in the array, or 0 if no number appears twice.
     *
     * Input: nums = [1,2,1,3]
     * Output: 1
     *
     * Input: nums = [1,2,3]
     * Output: 0
     *
     * Input: nums = [1,2,2,1]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 50
     * Each number in nums appears either once or twice.
     * @param nums
     * @return
     */
    // S1
    // time = O(1), space = O(n)
    public int duplicateNumbersXOR(int[] nums) {
        int[] cnt = new int[60];
        for (int x : nums) cnt[x]++;
        int res = 0;
        for (int i = 1; i <= 50; i++) {
            if (cnt[i] == 2) res ^= i;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int duplicateNumbersXOR2(int[] nums) {
        int res = 0;
        long state = 0;
        for (int x : nums) {
            if ((state >> x & 1) == 1) res ^= x;
            else state |= 1L << x;
        }
        return res;
    }
}