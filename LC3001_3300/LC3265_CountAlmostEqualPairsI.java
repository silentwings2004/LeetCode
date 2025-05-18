package LC3001_3300;

public class LC3265_CountAlmostEqualPairsI {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * We call two integers x and y in this problem almost equal if both integers can become equal after performing the
     * following operation at most once:
     *
     * Choose either x or y and swap any two digits within the chosen number.
     * Return the number of indices i and j in nums where i < j such that nums[i] and nums[j] are almost equal.
     *
     * Note that it is allowed for an integer to have leading zeros after performing an operation.
     *
     * Input: nums = [3,12,30,17,21]
     *
     * Output: 2
     *
     * Input: nums = [1,1,1,1,1]
     *
     * Output: 10
     *
     * Input: nums = [123,231]
     *
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n^2 * (logn)^2), space = O(logn)
    public int countPairs(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(nums[i], nums[j])) res++;
            }
        }
        return res;
    }

    private boolean check(int x, int y) {
        if (x == y) return true;
        if (x < y) {
            int t = x;
            x = y;
            y = t;
        }
        char[] s = String.valueOf(x).toCharArray();
        int n = s.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(s, i, j);
                if (Integer.parseInt(String.valueOf(s)) == y) return true;
                swap(s, i, j);
            }
        }
        return false;
    }

    private void swap(char[] s, int i, int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }
}