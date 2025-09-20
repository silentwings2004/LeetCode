package LC3601_3900;

public class LC3681_MaximumXORofSubsequences {
    /**
     * You are given an integer array nums of length n where each element is a non-negative integer.
     *
     * Select two subsequences of nums (they may be empty and are allowed to overlap), each preserving the original
     * order of elements, and let:
     *
     * X be the bitwise XOR of all elements in the first subsequence.
     * Y be the bitwise XOR of all elements in the second subsequence.
     * Return the maximum possible value of X XOR Y.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Note: The XOR of an empty subsequence is 0.
     *
     * Input: nums = [1,2,3]
     * Output: 3
     *
     * Input: nums = [5,2]
     * Output: 7
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxXorSubsequences(int[] nums) {
        int[] b = new int[32];
        for (int x : nums) {
            for (int i = 31; i >= 0; i--) {
                if ((x >> i & 1) == 0) continue;
                if (b[i] == 0) {
                    b[i] = x;
                    break;
                }
                x ^= b[i];
            }
        }
        int res = 0;
        for (int i = 31; i >= 0; i--) res = Math.max(res, res ^ b[i]);
        return res;
    }
}
/**
 * “异或空间线性基”的模版题
 * 对于一组数字集合nums，任意选取若干进行异或操作所能构成的结果记作空间S。
 * 如果另一组数字Basis同样也能构成S，并且Basis所需的数字最少，那么Basis就成为原数字集合的一组“异或空间线性基”。
 * 所构造的线性基有一个特点，每个元素的leading one的位置都不相同。
 * 比如{12,5,2}，其二进制表达就是{1100,0101,0010}，可以观察到最高位的位置依次降低。
 * 假设我们能找到nums的一组线性基B，那么nums能组成的最大异或值，等价于其线性基B能组成的最大异或值。
 * 我们可以用贪心法来从大到小进行选择：第i个元素的选择取决于答案的第i个bit位是否会退化。
 *    3 2 1 0
 * a  0 0 1 0
 * b  0 1 0 1
 * c  1 0 1 1
 * d  0 1 1 0
 */