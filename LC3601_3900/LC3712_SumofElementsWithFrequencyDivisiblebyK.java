package LC3601_3900;

public class LC3712_SumofElementsWithFrequencyDivisiblebyK {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Return an integer denoting the sum of all elements in nums whose frequency is divisible by k, or 0 if there are
     * no such elements.
     *
     * Note: An element is included in the sum exactly as many times as it appears in the array if its total frequency
     * is divisible by k.
     *
     * The frequency of an element x is the number of times it occurs in the array.
     *
     * Input: nums = [1,2,2,3,3,3,3,4], k = 2
     * Output: 16
     *
     * Input: nums = [1,2,3,4,5], k = 2
     * Output: 0
     *
     * Input: nums = [4,4,4,1,2,3], k = 3
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int sumDivisibleByK(int[] nums, int k) {
        int[] cnt = new int[101];
        for (int x : nums) cnt[x]++;
        int res = 0;
        for (int i = 1; i <= 100; i++) {
            if (cnt[i] == 0) continue;
            if (cnt[i] % k == 0) res += i * cnt[i];
        }
        return res;
    }
}