package LC2401_2700;

public class LC2574_LeftandRightSumDifferences {
    /**
     * Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:
     *
     * answer.length == nums.length.
     * answer[i] = |leftSum[i] - rightSum[i]|.
     * Where:
     *
     * leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element,
     * leftSum[i] = 0.
     * rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element,
     * rightSum[i] = 0.
     * Return the array answer.
     *
     * Input: nums = [10,4,8,3]
     * Output: [15,1,11,22]
     *
     * Input: nums = [1]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length;
        int[] l = new int[n], r = new int[n];
        for (int i = 1; i < n; i++) l[i] = l[i - 1] + nums[i - 1];
        for (int i = n - 2; i >= 0; i--) r[i] = r[i + 1] + nums[i + 1];
        for (int i = 0; i < n; i++) l[i] = Math.abs(l[i] - r[i]);
        return l;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] leftRigthDifference2(int[] nums) {
        int n = nums.length, l = 0, r = 0;
        int[] res = new int[n];
        for (int x : nums) r += x;
        for (int i = 0; i < n; i++) {
            r -= nums[i];
            res[i] = Math.abs(r - l);
            l += nums[i];
        }
        return res;
    }
}