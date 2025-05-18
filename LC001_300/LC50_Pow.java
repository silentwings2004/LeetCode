package LC001_300;
import java.util.*;
public class LC50_Pow {
    /**
     * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
     *
     * Input: x = 2.00000, n = 10
     * Output: 1024.00000
     * Constraints:
     *
     * -100.0 < x < 100.0
     * -2^31 <= n <= 2^31-1
     * -10^4 <= xn <= 10^4
     *
     * @param x
     * @param n
     * @return
     */
    // time = O(logn), space = O(logn)
    public double myPow(double x, int n) {
        long k = n;
        if (k < 0) {
            k = -k;
            x = 1 / x;
        }
        return qmi(x, k);
    }

    private double qmi(double x, long k) {
        double res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res *= x;
            x *= x;
            k >>= 1;
        }
        return res;
    }
}
/**
 * 比如我们要计算x的y次方。通常情况我们需要执行y次。但是如果我们将其转化为(x*x) ^ (y/2)就只需要执行一半的时间了，
 * 那么如果是()(x*x) * (x*x)) ^ (y/2/2)就只用四分之一的时间了，好家伙我直呼好家伙这不就是 O(logn) 的方式么。
 * 但这里有一个问题，你怎么知道y能一直被二整除呢？如果不能被整除怎么办？
 * 举个大家都会举的例子，计算3 ^ 10，第一次转化为了9 ^ 5 但下一次5/2不够除该怎么办？
 * 转化为（9^4）*（9^1）
 * 3^10=3*3*3*3*3*3*3*3*3*3
 * 3^10=(3*3)*(3*3)*(3*3)*(3*3)*(3*3)
 * 3^10=(3*3)^5
 * 3^10=9^5
 * 9^5=（9^4）*（9^1）
 * 9^5=（9^4）*（9^1）
 * 9^5=（6561^1）*(9^1)
 * public int quickPow(int x, int y) {
 *     if (y == 0) {
 *         return 1;
 *     }
 *     int ret = quickPow(x, y / 2);
 *     return y % 2 == 0 ? ret * ret : ret * ret * x;
 * }
 * 递归快速取模的算法思想没有问题，但是在递归的过程中，会使用很多额外的栈空间，是否存在一种办法，能通过迭代的循环方式完成，而非递归实现呢？
 * 答案当然是有的。思路很简单：当一个数 & 1若为1，即该数的最后一位为1，不能被2整除，否则代表可以被2整数。
 * public int quickPow(int x, int y) {
 *     int ret = 1;
 *     while(y != 0){
 *         if((y & 1) != 0) {
 *             ret = ret * x;
 *         }
 *         x = x * x;
 *         y >>= 1;
 *     }
 *     return ret;
 * }
 *
 */