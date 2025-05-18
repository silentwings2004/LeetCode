package LC3301_3600;

public class LC3514_NumberofUniqueXORTripletsII {
    /**
     * You are given an integer array nums.
     *
     * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
     *
     * Return the number of unique XOR triplet values from all possible triplets (i, j, k).
     *
     * Input: nums = [1,3]
     * Output: 2
     *
     * Input: nums = [6,7,8,9]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1500
     * 1 <= nums[i] <= 1500
     * @param nums
     * @return
     */
    // time = O(n * 2^m), space = O(2^m)
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        boolean[] st = new boolean[1 << m];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int x = nums[i] ^ nums[j];
                st[x] = true;
            }
        }

        int res = 0;
        for (int i = 0; i < 1 << m; i++) {
            for (int x : nums) {
                if (st[i ^ x]) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}