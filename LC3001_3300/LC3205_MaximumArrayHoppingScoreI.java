package LC3001_3300;

public class LC3205_MaximumArrayHoppingScoreI {
    /**
     * Given an array nums, you have to get the maximum score starting from index 0 and hopping until you reach the
     * last element of the array.
     *
     * In each hop, you can jump from index i to an index j > i, and you get a score of (j - i) * nums[j].
     *
     * Return the maximum score you can get.
     *
     * Input: nums = [1,5,8]
     * Output: 16
     *
     * Input: nums = [4,5,2,8,9,1,3]
     * Output: 42
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^3
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int maxScore(int[] nums) {
        int n = nums.length, res = 0;
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                f[i] = Math.max(f[i], f[j] + (i - j) * nums[i]);
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maxScore2(int[] nums) {
        int n = nums.length, mx = nums[n - 1], res = 0;
        for (int i = n - 1; i > 0; i--) {
            mx = Math.max(mx, nums[i]);
            res += mx;
        }
        return res;
    }
}
/**
 * Hop from i to k, get (k-i)*nums[k] == (j-i)*nums[k] + (k-j)*nums[k] points.
 * Hop from i to j then from j to k, get (j-i)*nums[j] + (k-j)*nums[k] points.
 * nums[k] > nums[j] <=> score1 > score2.
 * By induction, we always choose the maximum element on the right to hop from the current i.
 */