package LC901_1200;

public class LC1031_MaximumSumofTwoNonOverlappingSubarrays {
    /**
     * Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of elements in two
     * non-overlapping subarrays with lengths firstLen and secondLen.
     *
     * The array with length firstLen could occur before or after the array with length secondLen, but they have to be
     * non-overlapping.
     *
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
     * Output: 20
     *
     * Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
     * Output: 29
     *
     * Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
     * Output: 31
     *
     * Constraints:
     *
     * 1 <= firstLen, secondLen <= 1000
     * 2 <= firstLen + secondLen <= 1000
     * firstLen + secondLen <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * @param nums
     * @param firstLen
     * @param secondLen
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length, res = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        for (int i = 1; i + firstLen - 1 <= n; i++) {
            int r1 = i + firstLen - 1;
            int a = s[r1] - s[i - 1];
            for (int j = 1; j + secondLen - 1 < i; j++) {
                int r2 = j + secondLen - 1;
                int b = s[r2] - s[j - 1];
                res = Math.max(res, a + b);
            }
            for (int j = i + firstLen; j + secondLen - 1 <= n; j++) {
                int r2 = j + secondLen - 1;
                int b = s[r2] - s[j - 1];
                res = Math.max(res, a + b);
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int maxSumTwoNoOverlap2(int[] nums, int firstLen, int secondLen) {
        return Math.max(work(nums, firstLen, secondLen), work(nums, secondLen, firstLen));
    }

    private int work(int[] nums, int a, int b) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        int res = 0;
        for (int i = a + b, maxa = 0; i <= n; i++) {
            maxa = Math.max(maxa, s[i - b] - s[i - b - a]);
            res = Math.max(res, s[i] - s[i - b] + maxa);
        }
        return res;
    }
}