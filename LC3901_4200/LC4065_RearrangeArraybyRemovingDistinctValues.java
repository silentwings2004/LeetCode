package LC3901_4200;

public class LC4065_RearrangeArraybyRemovingDistinctValues {
    /**
     * You are given an integer array nums.
     *
     * You start with an empty array ans. Repeat the following operation until nums is empty:
     *
     * Identify all distinct values currently present in nums.
     * Remove one occurrence of every distinct value currently in nums, and append those values to ans in ascending order.
     * Return the array ans.
     *
     * Input: nums = [3,1,3,2,1,3]
     * Output: [1,2,3,1,3,3]
     *
     * Input: nums = [7,7,4,4,4]
     * Output: [4,7,4,7,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n * U), space = O(U)  U: max{nums}
    public int[] rearrangeArray(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) cnt[x]++;
        int n = nums.length, idx = 0;
        int[] res = new int[n];
        while (idx < n) {
            for (int i = 1; i <= 100; i++) {
                if (cnt[i] > 0) {
                    res[idx++] = i;
                    cnt[i]--;
                }
            }
        }
        return res;
    }
}