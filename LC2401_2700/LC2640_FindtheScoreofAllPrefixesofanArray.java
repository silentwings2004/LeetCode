package LC2401_2700;

public class LC2640_FindtheScoreofAllPrefixesofanArray {
    /**
     * We define the conversion array conver of an array arr as follows:
     *
     * conver[i] = arr[i] + max(arr[0..i]) where max(arr[0..i]) is the maximum value of arr[j] over 0 <= j <= i.
     * We also define the score of an array arr as the sum of the values of the conversion array of arr.
     *
     * Given a 0-indexed integer array nums of length n, return an array ans of length n where ans[i] is the score of
     * the prefix nums[0..i].
     *
     * Input: nums = [2,3,7,5,10]
     * Output: [4,10,24,36,56]
     *
     * Input: nums = [1,1,2,4,8,16]
     * Output: [2,4,8,16,32,64]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        res[0] = nums[0];
        for (int i = 1; i < n; i++) res[i] = Math.max(res[i - 1], nums[i]);
        for (int i = 0; i < n; i++) res[i] += nums[i];
        for (int i = 1; i < n; i++) res[i] += res[i - 1];
        return res;
    }
}