package LC3301_3600;

public class LC3513_NumberofUniqueXORTripletsI {
    /**
     * You are given an integer array nums of length n, where nums is a permutation of the numbers in the range [1, n].
     *
     * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
     *
     * Return the number of unique XOR triplet values from all possible triplets (i, j, k).
     *
     * A permutation is a rearrangement of all the elements of a set.
     *
     * Input: nums = [1,2]
     * Output: 2
     *
     * Input: nums = [3,1,2]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= n
     * nums is a permutation of integers from 1 to n.
     * @param nums
     * @return
     */
    // time = O(1), space = O(1)
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n < 3) return n;
        int m = 32 - Integer.numberOfLeadingZeros(n);
        return 1 << m;
    }
}
/**
 * 构造方案：
 * 1. 先用一个数 x 把最高位去掉
 * 2. 再异或一个 1 得到第二个数 y
 * 3. 最后得到三个数 x, y, 1
 * e.g. 1101 -> x = 1000 得到 101 -> 101 ^ 1 = 100, y = 100
 * => x = 1000, y = 100, z = 1
 * 但有种情况得不到，比如 1001
 * 1001 -> x = 1000, get 1 -> 1 ^ 1 = 0, but 0 not exist inside the array
 * solution: 特殊处理, x = 1000, get 0001, then use 0010 ^ 0011 = 0001
 * => x = 1000, y = 0011, z = 0010
 * 确实可以得到长度与 n 的二进制长度一样的所有数字 => [0, 2^L - 1]
 */