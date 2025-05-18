package LC901_1200;

public class LC985_SumofEvenNumbersAfterQueries {
    /**
     * You are given an integer array nums and an array queries where queries[i] = [vali, indexi].
     *
     * For each query i, first, apply nums[indexi] = nums[indexi] + vali, then print the sum of the even values of nums.
     *
     * Return an integer array answer where answer[i] is the answer to the ith query.
     *
     * Input: nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
     * Output: [8,6,2,4]
     *
     * Input: nums = [1], queries = [[4,0]]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     * 1 <= queries.length <= 10^4
     * -10^4 <= vali <= 10^4
     * 0 <= indexi < nums.length
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n), space = O(1)
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int n = queries.length, sum = 0;
        int[] res = new int[n];
        for (int x : nums) sum += x % 2 == 0 ? x : 0;
        for (int i = 0; i < n; i++) {
            int val = queries[i][0], idx = queries[i][1];
            if (nums[idx] % 2 == 0) sum -= nums[idx];
            nums[idx] += val;
            if (nums[idx] % 2 == 0) sum += nums[idx];
            res[i] = sum;
        }
        return res;
    }
}