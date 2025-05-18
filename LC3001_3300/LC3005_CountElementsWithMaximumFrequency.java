package LC3001_3300;

public class LC3005_CountElementsWithMaximumFrequency {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
     *
     * The frequency of an element is the number of occurrences of that element in the array.
     *
     * Input: nums = [1,2,2,3,1,4]
     * Output: 4
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxFrequencyElements(int[] nums) {
        int[] cnt = new int[110];
        int maxf = 0;
        for (int x : nums) {
            cnt[x]++;
            maxf = Math.max(maxf, cnt[x]);
        }
        int res = 0;
        for (int x : cnt) {
            if (x == maxf) res += maxf;
        }
        return res;
    }
}