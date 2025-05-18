package LC301_600;

public class LC440_KthSmallestinLexicographicalOrder {
    /**
     * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
     *
     * Input: n = 13, k = 2
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= k <= n <= 10^9
     * @param n
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int findKthNumber(int n, int k) {
        long cur = 1;
        k--;

        while (k > 0) {
            int nodes = countNodes(n, cur); // 看以cur为根的子树总共有多少个node
            if (k >= nodes) { // 整棵树的node结点总数都不及k，则要到右边的那棵子树上
                k -= nodes;
                cur++;
            } else { // 这棵树的node数大于k，则k一定在下面的子树里，所以k-1去掉根节点，向下面子树访问，cur到达下面子树：cur *= 10
                k--;
                cur *= 10;
            }
        }
        return (int) cur;
    }

    private int countNodes(long n, long cur) {
        long total = 0, next = cur + 1; // next代表cur为根的子树右边的那棵子树

        while (cur <= n) {  // 如果cur > n了，代表n已经出现在上一轮右移之前的total结果里了，不存在当前的cur与next之间的子树中了！
            total += Math.min(n - cur + 1, next - cur); // 正棵子树node数 = next - cur，如果k在其中，那么为 n - cur + 1
            cur *= 10; // cur, next分别向右移动一棵子树
            next *= 10;
        }
        return (int) total;
    }

    // S2
    // time = O(logn * logn), space = O(1)
    public int findKthNumber2(int n, int k) {
        int prefix = 1;
        while (k > 1) {
            int cnt = f(prefix, n);
            if (k > cnt) {
                k -= cnt;
                prefix++;
            } else {
                k--;
                prefix *= 10;
            }
        }
        return prefix;
    }

    private int f(int prefix, int n) {
        long p = 1;
        String A = String.valueOf(n), B = String.valueOf(prefix);
        int dt = A.length() - B.length(), res = 0;
        for (int i = 0; i < dt; i++) {
            res += p;
            p *= 10;
        }
        A = A.substring(0, B.length());
        if (A.equals(B)) res += n - prefix * p + 1;
        else if (A.compareTo(B) > 0) res += p;
        return res;
    }
}
/**
 * 按位去看答案的前缀是多少
 * 一位一位来看答案的前缀
 * 首位 1
 * f(prefix n): 1~n中有多少个数的前缀是prefix
 * cnt = f(1, n)
 * k = 1
 * k > cnt: prefix++; k-= cnt
 * 1 < k <= cnt => 则开始确定前缀的下一位， prefix * 10, k--
 */
