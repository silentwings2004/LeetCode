package LC601_900;
import java.util.*;
public class LC823_BinaryTreesWithFactors {
    /**
     * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
     *
     * We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf
     * node's value should be equal to the product of the values of its children.
     *
     * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
     *
     * Input: arr = [2,4]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= arr.length <= 1000
     * 2 <= arr[i] <= 10^9
     *
     * @param arr
     * @return
     */
    // time = O(n^2), space = O(n)
    public int numFactoredBinaryTrees(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(arr[i], i);

        long[] f = new long[n];
        long M = (long)(1e9 + 7), res = 0;
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int d = arr[i] / arr[j];
                    if (map.containsKey(d)) {
                        int k = map.get(d);
                        f[i] = (f[i] + f[j] * f[k]) % M;
                    }
                }
            }
            res = (res + f[i]) % M;
        }
        return (int) res;
    }
}
/**
 * DP equation:
 * j: 0 ~ i - 1
 * dp[i] = sum(dp[j] * dp[i / j])
 * res = sum(dp[i])
 * with i, j, i / j in the list L
 *
 * 父节点的值一定大于子节点的值 -> 排序
 * 完全二叉树，要么2个儿子，要么一个儿子都没有
 * 先求所有子节点的值
 * f(i): 以第i个数为根的二叉树有多少种
 * 1. 无儿子：1
 * 2. 3*8
 * 3. 4*6
 * 4. 6*4
 * 5. 8*3
 * => f(4) * f(6)
 */
