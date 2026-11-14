package LC3901_4200;

public class LC4107_CountValidSuffixSortsI {
    /**
     * You are given two integer arrays nums and target, both of length n.
     *
     * For each index i, consider sorting the suffix nums[i..n - 1] of the original array in non-increasing order.
     *
     * Return the number of indices i for which the resulting array is equal to target.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * A suffix of an array is a subarray that begins at any index and extends to the end of the array.
     *
     * Input: nums = [1,2,3], target = [3,2,1]
     * Output: 1
     *
     * Input: nums = [1,5,6,4,6], target = [1,5,6,6,4]
     * Output: 2
     *
     * Input: nums = [7,6,8], target = [8,6,7]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length == target.length <= 10^5
     * 1 <= nums[i], target[i] <= 10^5
     * @param nums
     * @param target
     * @return
     */
    // time = O(n + mx), space = O(n + mx)
    public int countSuffix(int[] nums, int[] target) {
        int n = nums.length;
        int[] cnt = new int[100001];
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
            cnt[target[i]]--;
        }
        for (int x : cnt) {
            if (x != 0) return 0;
        }

        boolean[] validPre = new boolean[n];
        validPre[0] = true;
        boolean suf = true;
        for (int i = 1; i < n; i++) {
            validPre[i] = validPre[i - 1] && nums[i - 1] == target[i - 1];
        }

        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && target[i] < target[i + 1]) {
                suf = false;
                break;
            }
            if (validPre[i]) res++;
        }
        return res;
    }
}