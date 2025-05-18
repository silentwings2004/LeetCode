package LC301_600;

public class LC483_SmallestGoodBase {
    /**
     * Given an integer n represented as a string, return the smallest good base of n.
     *
     * We call k >= 2 a good base of n, if all digits of n base k are 1's.
     *
     * Input: n = "13"
     * Output: "3"
     *
     * Constraints:
     *
     * n is an integer in the range [3, 10^18].
     * n does not contain any leading zeros.
     * @param n
     * @return
     */
    // S1: binary search
    // time = O((logn)^2), space = O(1)
    public String smallestGoodBase(String n) {
        long N = Long.parseLong(n);
        for (long m = (int)(Math.log(N + 1) / Math.log(2) + 1); m >= 2; m--) {
            long left = 2, right = (long)Math.pow(N, 1.0 / (m - 1)); // m == 1时，m - 1 = 0 => m >= 2
            while (left <= right) {  // 对k做二分
                long mid = left + (right - left) / 2;
                long k = mid;
                long sum = 1;
                for (int i = 1; i < m; i++) {
                    sum = sum * k + 1;
                }
                if (sum == N) return String.valueOf(k);
                else if (sum > N) right = mid - 1;
                else left = mid + 1;
            }
        }
        return String.valueOf(N - 1);
    }

    // S2: Math
    // time = O(1), space = O(1)
    public String smallestGoodBase2(String n) {
        long num = Long.parseLong(n);
        for (int i = 59; i >= 2; i--) {
            long k = (long) Math.pow(num, 1.0 / (i - 1));
            if (k <= 1) continue;
            long s = 0;
            for (int j = 0; j <= i - 1; j++) s = s * k + 1;
            if (s == num) return String.valueOf(k);
        }
        return String.valueOf(num - 1);
    }
}
/**
 * n - 1 进制 => 11
 * k => 1111111
 * n = k^0 + k^1 + k^2 + ... + k^(m-1)
 * n = (k^m - 1) / (k-1)   k尽量小
 * 二分，往小里压缩
 * 确定k之后，找m，但是如果找不到m怎么办？比如算出来是个小数，怎么办？
 * 要控制在10^6，这里时间复杂度太高
 * k = 2 => m_max = log(n) / log2 = 59.79  => 对m猜59次
 * n = (k^m - 1) / (k-1)  => n * (k - 1) + 1 = k^m
 * n * k > k^m  => n > k^(m - 1) => k < n^(1 / (m - 1))
 *
 * (n)10 = (11)n-1 => 一定有解
 * k >= 2 => 位数只有[log2n]位 最多60位
 * (n)10 = (1...1)k = k^(t-1) + k^(t-2) + ... + k^0
 * => 关于k的方程
 * 枚举完t之后，判断是否存在一个整数k >= 2 是满足这个方程的
 * 二分？ 10^18 可能爆long long，处理边界有点复杂
 * 能不能直接求出k?
 * k^(t-1) <= n => k <= n^(1/(t-1))
 * (k+1)^(t-1) = (k+1)^0 * C(t-1,0) + (k+1)*C(t-1,1) + ...+(k+1)^(t-1)*C(t-1,t-1)
 * t = 1 => (k+1)^(t-1) = n
 * t = 2 => (k+1)^(t-1) > n
 * t > 2 => (k+1)^(t-1) > n
 * => k + 1 > n^(1/(t-1))
 */
