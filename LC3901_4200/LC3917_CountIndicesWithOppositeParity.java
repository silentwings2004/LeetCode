package LC3901_4200;

public class LC3917_CountIndicesWithOppositeParity {
    /**
     * You are given an integer array nums of length n.
     *
     * The score of an index i is defined as the number of indices j such that:
     *
     * i < j < n, and
     * nums[i] and nums[j] have different parity (one is even and the other is odd).
     * Return an integer array answer of length n, where answer[i] is the score of index i.
     *
     * Input: nums = [1,2,3,4]
     * Output: [2,1,1,0]
     *
     * Input: nums = [1]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] cnt = new int[2];
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i] % 2;
            res[i] = cnt[x ^ 1];
            cnt[x]++;
        }
        return res;
    }
}