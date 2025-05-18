package LC601_900;

public class LC810_ChalkboardXORGame {
    /**
     * You are given an array of integers nums represents the numbers written on a chalkboard.
     *
     * Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first. If erasing
     * a number causes the bitwise XOR of all the elements of the chalkboard to become 0, then that player loses. The
     * bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is 0.
     *
     * Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to 0,
     * then that player wins.
     *
     * Return true if and only if Alice wins the game, assuming both players play optimally.
     *
     * Input: nums = [1,1,2]
     * Output: false
     *
     * Input: nums = [0,1]
     * Output: true
     *
     * Input: nums = [1,2,3]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] < 2^16
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean xorGame(int[] nums) {
        int s = 0, n = nums.length;
        for (int x : nums) s ^= x;
        return s == 0 || n % 2 == 0;
    }
}
/**
 * 必胜态：至少存在一种方式，可以走到必败态  => s = 0 || n 为偶数
 * 必败态：只能走到必胜态 => 是！= 0 && n 为奇数
 * 证明：
 * n = 0 => 必胜，成立
 * 设 k < n 时成立
 * 证当k = n 时，也能力
 */
