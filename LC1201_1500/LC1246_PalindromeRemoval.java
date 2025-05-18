package LC1201_1500;

public class LC1246_PalindromeRemoval {
    /**
     * You are given an integer array arr.
     *
     * In one move, you can select a palindromic subarray arr[i], arr[i + 1], ..., arr[j] where i <= j, and remove that
     * subarray from the given array. Note that after removing a subarray, the elements on the left and on the right of
     * that subarray move to fill the gap left by the removal.
     *
     * Return the minimum number of moves needed to remove all numbers from the array.
     *
     * Input: arr = [1,2]
     * Output: 2
     *
     * Input: arr = [1,3,4,1,5]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= 20
     * @param arr
     * @return
     */
    // time = O(n^3), space = O(n^2)
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) f[i][j] = 1;
                else {
                    f[i][j] = f[i][j - 1] + 1;
                    for (int k = i; k < j; k++) {
                        if (arr[k] == arr[j]) {
                            f[i][j] = Math.min(f[i][j], (i <= k - 1 ? f[i][k - 1] : 0) + (k + 1 <= j - 1 ? f[k + 1][j - 1] : 1));
                        }
                    }
                }
            }
        }
        return f[0][n - 1];
    }
}
/**
 * 我们定义dp[i:j]表示对于s[i:j]最少需要删几次。
 * 如何将这个大区间转化为小区间呢？
 * 一个常见的处理方法就是看最后一个元素s[j]。
 * 无论怎么设计方案，s[j]都可以是最后一个被删除的。
 * 我们就想s[j]会和谁一起删除？必然是找相同的字符。
 * 我们在[i:j]中寻找一个s[k]==s[j]，那么原来的大区间就分化为了两部分：dp[i][k-1]+dp[k][j]。
 * 而对于后者，我们发现s[k]和s[j]并不占用操作，而是可以和删除dp[k+1][j-1]的时候一起消除（回文加了一层而已）。
 * 特别注意，删除s[k]和s[j]并不是永远都不占用操作数，
 * 如果当dp[k+1][j-1]本身是0的时候，我们还是需要将s[k]和s[j]当作一对紧挨着的回文数删去的。
 */