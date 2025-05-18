package LC2700_3000;

public class LC2936_NumberofEqualNumbersBlocks {
    /**
     * You are given a 0-indexed array of integers, nums. The following property holds for nums:
     *
     * All occurrences of a value are adjacent. In other words, if there are two indices i < j such that
     * nums[i] == nums[j], then for every index k that i < k < j, nums[k] == nums[i].
     * Since nums is a very large array, you are given an instance of the class BigArray which has the following functions:
     *
     * int at(long long index): Returns the value of nums[i].
     * void size(): Returns nums.length.
     * Let's partition the array into maximal blocks such that each block contains equal values. Return the number of
     * these blocks.
     *
     * Note that if you want to test your solution using a custom test, behavior for tests with nums.length > 10 is
     * undefined.
     *
     * Input: nums = [3,3,3,3,3]
     * Output: 1
     *
     * Input: nums = [1,1,1,3,9,9,9,2,10,10]
     * Output: 5
     *
     * Input: nums = [1,2,3,4,5,6,7]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^15
     * 1 <= nums[i] <= 10^9
     * The input is generated such that all equal values are adjacent.
     * The sum of the elements of nums is at most 10^15.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int countBlocks(BigArray nums) {
        long n = nums.size();
        return (int)dfs(nums, 0, n - 1);
    }

    private long dfs(BigArray nums, long l, long r) {
        if (nums.at(l) == nums.at(r)) return 1;

        long mid = l + r >> 1;
        long ls = dfs(nums, l, mid);
        long rs = dfs(nums, mid + 1, r);

        return ls + rs - (nums.at(mid) == nums.at(mid + 1) ? 1 : 0);
    }

    class BigArray {
        public BigArray(int[] elements);
        public int at(long index);
        public long size();
    }
}