package LC3601_3900;

public class LC3630_PartitionArrayforMaximumXORandAND {
    /**
     * You are given an integer array nums.
     *
     * Partition the array into three (possibly empty) subsequences A, B, and C such that every element of nums belongs
     * to exactly one subsequence.
     *
     * Your goal is to maximize the value of: XOR(A) + AND(B) + XOR(C)
     *
     * where:
     *
     * XOR(arr) denotes the bitwise XOR of all elements in arr. If arr is empty, its value is defined as 0.
     * AND(arr) denotes the bitwise AND of all elements in arr. If arr is empty, its value is defined as 0.
     * Return the maximum value achievable.
     *
     * Note: If multiple partitions result in the same maximum sum, you can consider any one of them.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Note: Please do not copy the description during the contest to maintain the integrity of your submissions.
     *
     * Input: nums = [2,3]
     * Output: 5
     *
     * Input: nums = [1,3,2]
     * Output: 6
     *
     * Input: nums = [2,3,6,7]
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= nums.length <= 19
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n * 2^n * logU, space = O(2^n + logU), U = max(nums)
    public long maximizeXorAndXor(int[] nums) {
        int n = nums.length;
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int sz = 32 - Integer.numberOfLeadingZeros(mx);

        int m = 1 << n;
        int[] subAnd = new int[m];
        int[] subXor = new int[m];
        int[] subOr = new int[m];
        subAnd[0] = -1;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int highBit = 1 << i;
            for (int mask = 0; mask < highBit; mask++) {
                subAnd[highBit | mask] = subAnd[mask] & x;
                subXor[highBit | mask] = subXor[mask] ^ x;
                subOr[highBit | mask] = subOr[mask] | x;
            }
        }
        subAnd[0] = 0;
        long res = 0;
        for (int i = 0; i < m; i++) {
            int j = (m - 1) ^ i;
            if (subAnd[i] + subOr[j] * 2L - subXor[j] > res) {
                res = Math.max(res, subAnd[i] + maxXor2(j, subXor[j], nums, sz));
            }
        }
        return res;
    }

    private long maxXor2(int sub, int xor, int[] nums, int sz) {
        XorBasis b = new XorBasis(sz);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((sub >> i & 1) > 0) b.insert(nums[i] & ~xor);
        }
        return xor + b.maxXor() * 2L;
    }

    class XorBasis {
        int[] b;
        public XorBasis(int n) {
            b = new int[n];
        }

        public void insert(int x) {
            while (x > 0) {
                int i = 31 - Integer.numberOfLeadingZeros(x);
                if (b[i] == 0) {
                    b[i] = x;
                    return;
                }
                x ^= b[i];
            }
        }

        public int maxXor() {
            int res = 0;
            for (int i = b.length - 1; i >= 0; i--) {
                res = Math.max(res, res ^ b[i]);
            }
            return res;
        }
    }
}