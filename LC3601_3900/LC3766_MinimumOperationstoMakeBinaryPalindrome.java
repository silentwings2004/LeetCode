package LC3601_3900;

public class LC3766_MinimumOperationstoMakeBinaryPalindrome {
    /**
     * You are given an integer array nums.
     *
     * For each element nums[i], you may perform the following operations any number of times (including zero):
     *
     * Increase nums[i] by 1, or
     * Decrease nums[i] by 1.
     * A number is called a binary palindrome if its binary representation without leading zeros reads the same forward
     * and backward.
     *
     * Your task is to return an integer array ans, where ans[i] represents the minimum number of operations required to
     * convert nums[i] into a binary palindrome.
     *
     * Input: nums = [1,2,4]
     * Output: [0,1,1]
     *
     * Input: nums = [6,7,12]
     * Output: [1,0,3]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5000
     * 1 <= nums[i] <= 5000
     * @param nums
     * @return
     */
    // time = O(n * k), space = O(1)
    public int[] minOperations(int[] nums) {
        int n = nums.length, mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= mx; j++) {
                if (check(nums[i] + j) || check(nums[i] - j)) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    private boolean check(int x) {
        String s = Integer.toBinaryString(x);
        int n = s.length();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}