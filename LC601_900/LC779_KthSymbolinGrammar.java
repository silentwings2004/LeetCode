package LC601_900;

public class LC779_KthSymbolinGrammar {
    /**
     * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we
     * look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
     *
     * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
     * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
     *
     * Input: n = 1, k = 1
     * Output: 0
     *
     * Input: n = 2, k = 1
     * Output: 0
     *
     * Input: n = 2, k = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 30
     * 1 <= k <= 2^(n - 1)
     * @param n
     * @param k
     * @return
     */
    // S1
    // time = O(logk), space = O(1)
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k - 1) & 1;
    }

    // S2
    // time = O(logk), space = O(1)
    public int kthGrammar2(int n, int k) {
        int ans = 0;
        for (;n > 1; n--) {
            if (k > (1 << n - 2)) {
                ans ^= 1;
                k -= 1 << n - 2;
            }
        }
        return ans;
    }
}
/**
 * 分成左右两半
 * 左边一半跟上一行一样，右边一半把左边一半取反
 * n行长度 = 2 ^ (n - 1)
 * k属于前一段还是后一段
 * f(n,k) = f(n-1, k)   k在前一半
 *          f(n-1, k - 2^(n-2)) ^ 1
 * 奇数位的数字永远和上一行对应的数字不同
 * 需要找到翻转了多少次，翻转奇数次为1，偶数次为0
 * 对于某一层n的第i个字符来说，若该字符为0，那么(2i,2i+1) = (0,1), 若该字符为1，那么(2i, 2i + 1) = (1, 0)。
 * 可以发现偶数位2i与之前的i字符相同，而2i+1则相反，
 * 因此我们只需要判断k每次除以2后有多少次位于奇数位置即可。
 * 若位于依次奇数位，那么代表会发生一次翻转。
 * 对于奇数位的统计可以直接统计其二进制上1的个数，使用Java的Integer.bitCount(k)可以直接求出。
 */