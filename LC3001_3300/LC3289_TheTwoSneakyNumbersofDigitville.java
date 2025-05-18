package LC3001_3300;

public class LC3289_TheTwoSneakyNumbersofDigitville {
    /**
     * In the town of Digitville, there was a list of numbers called nums containing integers from 0 to n - 1. Each
     * number was supposed to appear exactly once in the list, however, two mischievous numbers sneaked in an additional
     * time, making the list longer than usual.
     *
     * As the town detective, your task is to find these two sneaky numbers. Return an array of size two containing the
     * two numbers (in any order), so peace can return to Digitville.
     *
     * Input: nums = [0,1,1,0]
     * Output: [0,1]
     *
     * Input: nums = [0,3,2,1,3,2]
     * Output: [2,3]
     *
     * Input: nums = [7,1,5,4,3,4,6,0,9,5,8,2]
     * Output: [4,5]
     *
     * Constraints:
     *
     * 2 <= n <= 100
     * nums.length == n + 2
     * 0 <= nums[i] < n
     * The input is generated such that nums contains exactly two repeated elements.
     * @param nums
     * @return
     */
    // time = O(n), space = o(1)
    public int[] getSneakyNumbers(int[] nums) {
        int[] cnt = new int[110];
        int[] res = new int[2];
        int idx = 0;
        for (int x : nums) {
            cnt[x]++;
            if (cnt[x] > 1) res[idx++] = x;
        }
        return res;
    }
}