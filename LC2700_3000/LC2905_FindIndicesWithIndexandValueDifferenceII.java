package LC2700_3000;

public class LC2905_FindIndicesWithIndexandValueDifferenceII {
    /**
     * You are given a 0-indexed integer array nums having length n, an integer indexDifference, and an integer
     * valueDifference.
     *
     * Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:
     *
     * abs(i - j) >= indexDifference, and
     * abs(nums[i] - nums[j]) >= valueDifference
     * Return an integer array answer, where answer = [i, j] if there are two such indices, and answer = [-1, -1]
     * otherwise. If there are multiple choices for the two indices, return any of them.
     *
     * Note: i and j may be equal.
     *
     * Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
     * Output: [0,3]
     *
     * Input: nums = [2,1], indexDifference = 0, valueDifference = 0
     * Output: [0,0]
     *
     * Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 0 <= indexDifference <= 10^5
     * 0 <= valueDifference <= 10^9
     * @param nums
     * @param indexDifference
     * @param valueDifference
     * @return
     */
    // time = O(n), space = O(n)
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int[][] l = new int[n][2], r = new int[n][2];
        l[0] = new int[]{nums[0], nums[0]};
        for (int i = 1; i < n; i++) {
            l[i][0] = Math.max(l[i - 1][0], nums[i]);
            l[i][1] = Math.min(l[i - 1][1], nums[i]);
        }
        r[n - 1] = new int[]{nums[n - 1], nums[n - 1]};
        for (int i = n - 2; i >= 0; i--) {
            r[i][0] = Math.max(r[i + 1][0], nums[i]);
            r[i][1] = Math.min(r[i + 1][1], nums[i]);
        }

        for (int i = 0; i < n; i++) {
            int a = i - indexDifference, b = i + indexDifference;
            int x = nums[i];
            if (a >= 0 && (Math.abs(x - l[a][0]) >= valueDifference || Math.abs(x - l[a][1]) >= valueDifference)) {
                for (int j = 0; j <= a; j++) {
                    if (Math.abs(nums[j] - x) >= valueDifference) return new int[]{j, i};
                }
            }
            if (b < n && (Math.abs(x - r[b][0]) >= valueDifference || Math.abs(x - r[b][1]) >= valueDifference)) {
                for (int j = b; j < n; j++) {
                    if (Math.abs(x - nums[j]) >= valueDifference) return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
