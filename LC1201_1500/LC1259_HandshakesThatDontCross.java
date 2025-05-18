package LC1201_1500;

public class LC1259_HandshakesThatDontCross {
    /**
     * You are given an even number of people numPeople that stand around a circle and each person shakes hands with
     * someone else so that there are numPeople / 2 handshakes total.
     *
     * Return the number of ways these handshakes could occur such that none of the handshakes cross.
     *
     * Since the answer could be very large, return it modulo 10^9 + 7.
     *
     * Input: numPeople = 4
     * Output: 2
     *
     * Input: numPeople = 6
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= numPeople <= 1000
     * numPeople is even.
     * @param numPeople
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int numberOfWays(int numPeople) {
        int mod = (int)1e9 + 7, n = numPeople;
        long[] f = new long[n / 2 + 1];
        f[0] = 1;
        for (int i = 1; i <= n / 2; i++) {
            for (int j = 0; j < i; j++) {
                f[i] = (f[i] + f[j] * f[i - 1 - j]) % mod;
            }
        }
        return (int)f[n / 2];
    }

    // S2
    // time = O(n^2), space = O(n)
    public int numberOfWays2(int numPeople) {
        int n = numPeople, mod = (int)1e9 + 7;
        long[] f = new long[n + 1];
        f[0] = 1;
        f[2] = 1;
        for (int i = 4; i <= n; i += 2) {
            for (int j = 0; j <= i - 2; j++) {
                f[i] = (f[i] + f[j] * f[i - j - 2]) % mod;
            }
        }
        return (int)f[n];
    }
}
/**
 * 设计dp[i]表示i个人互相握手有多少种符合题意的方法。
 * 我们考虑最后一个人（第i个人）的握手方案。
 * 注意i必须是偶数，否则整体就无解。
 * 第i个人的选择可以是他左手第1个、第3个、第5个...直至右手第1个。
 * 考虑到第i个人的配成功，会将整个圈划分成了独立的左右两部分，
 * 因此上面这些方案其实对应了将这个圈细分的每种可能：(0,i-2),(2,i-4),(4,i-6)...(i-2,0)，其中每个括号内表示左右两部分的人数。
 * 因此我们可以得到递推关系式：dp[i] = sum(dp[j]+dp[i-2-j])， j=0,2,...i-2
 */