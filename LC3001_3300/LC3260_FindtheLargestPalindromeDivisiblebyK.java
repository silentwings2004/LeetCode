package LC3001_3300;

public class LC3260_FindtheLargestPalindromeDivisiblebyK {
    /**
     * You are given two positive integers n and k.
     *
     * An integer x is called k-palindromic if:
     *
     * x is a palindrome.
     * x is divisible by k.
     * Return the largest integer having n digits (as a string) that is k-palindromic.
     *
     * Note that the integer must not have leading zeros.
     *
     * Input: n = 3, k = 5
     *
     * Output: "595"
     *
     * Input: n = 1, k = 4
     *
     * Output: "8"
     *
     * Input: n = 5, k = 6
     *
     * Output: "89898"
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= k <= 9
     * @param n
     * @param k
     * @return
     */
    // time = O(n * D), space = O(n * k)
    int n, k;
    String res;
    boolean[][] f;
    int[] q;
    public String largestPalindrome(int n, int k) {
        this.n = n;
        this.k = k;
        res = "";
        f = new boolean[n][10];
        q = new int[n + 1];
        for (int i = 0; i <= n; i++) q[i] = qmi(10, i);

        dfs(0, 0, new StringBuilder());
        if (n % 2 == 1) res += reverse(res.substring(0, res.length() - 1));
        else res += reverse(res);
        return res;
    }

    private boolean dfs(int u, int sum, StringBuilder sb) {
        if (u == (n + 1) / 2) {
            if (sum % k == 0) {
                res = sb.toString();
                return true;
            }
            return false;
        }
        if (f[u][sum]) return false;

        for (int i = 9; i >= (u == 0 ? 1 : 0); i--) {
            sb.append(i);
            if (n - 1 - u == u) {
                if (dfs(u + 1, (sum + i * q[n - 1 - u]) % k, sb)) return true;
            } else if (dfs(u + 1, (sum + i * q[n - 1 - u] + i * q[u]) % k, sb)) return true;
            sb.setLength(sb.length() - 1);
        }
        f[u][sum] = true;
        return false;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    private int qmi(int a, int x) {
        int res = 1;
        while (x > 0) {
            if ((x & 1) == 1) res = res * a % k;
            a = a * a % k;
            x >>= 1;
        }
        return res;
    }
}
/**
 * 1. 在 i 位置填数字 d，也同时在位置 n - 1 - i 填了数字 d
 * 2. 在生成答案之前，只需要考虑回文数 % k 的结果
 */