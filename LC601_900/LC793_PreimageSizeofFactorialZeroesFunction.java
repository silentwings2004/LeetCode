package LC601_900;

public class LC793_PreimageSizeofFactorialZeroesFunction {
    /**
     * Let f(x) be the number of zeroes at the end of x!. Recall that x! = 1 * 2 * 3 * ... * x and by convention, 0! = 1.
     *
     * For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has two
     * zeroes at the end.
     * Given an integer k, return the number of non-negative integers x have the property that f(x) = k.
     *
     * Input: k = 0
     * Output: 5
     *
     * Constraints:
     *
     * 0 <= k <= 10^9
     * @param k
     * @return
     */
    // S1
    // time = O((logk)^2), space = O(logk)
    public int preimageSizeFZF(int k) {
        long left = 1, right = 5 * (long)1e9; // left从1开始，因为即使是0! = 1
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (countTrailingZeros(mid) < k) left = mid + 1;
            else right = mid; // 猜得正好，可能可以继续往下猜，未必是最小的
        }
        // x之后的x+1,x+2,x+3,x+4必定都不含质因数5，而x+5必定会包含新的质因数5从而增加至少一个trailing zero。
        if (countTrailingZeros(left) == k) return 5;
        return 0;
    }

    private long countTrailingZeros(long x) {
        long count = 0;
        for (long i = 5; i <= x; i *= 5) {
            count += x / i;
        }
        return count;
    }

    // S2
    // time = O((logk)^2), space = O(1)
    public int preimageSizeFZF2(int k) {
        return (int)(calc(k) - calc(k - 1));
    }

    private long calc(int k) {
        long l = -1, r = (long) 1e18;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (helper(mid) <= k) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private long helper(long x) {
        long res = 0;
        while (x > 0) {
            res += x / 5;
            x /= 5;
        }
        return res;
    }
}
/**
 * 主要就是看x!里面有多少个质因数5
 * 1*2*3*4*5*6*7*8*9*10*...*24*25...125
 * 每5个一段，25比较特殊，有2个5 => 5 + 1
 * 5个一数的话，有一个5，每25一数有2个5，每125一数就有3个5
 * x! = x/5 + x/25 + x/125 + ... + 0
 * 猜x是多少，通过f(x) = x/5 + x/25 + x/125 + ... + 0
 * 这题不一定有解
 * f(26) = 6
 * f(25) = 6
 * f(24) = 4
 * 二分搜索的话，不一定能搜到f(x) = k
 * f(x1) = k1 > k
 * f(x2) = k2 < k
 * x上限是5*k
 * 此题需要注意，并不是一定存在x使得f(x)=K。比如f(24)=4，但是f(25)=6，因此不存在任何f(x)=5。
 * 我们可以用二分搜索的方法尝试找到最小的x，满足f(x)==K。
 * 如果能找到，那么就直接返回5，
 * 这是因为x之后的x+1,x+2,x+3,x+4必定都不含质因数5，而x+5必定会包含新的质因数5从而增加至少一个trailing zero。
 * 反之，如果二分搜索找不到，那么就返回0.
 *
 * 10的个数取决于5的个数
 * calc(k):有多少个非负整数f(n) <= k
 * calc(k) - calc(k - 1) => 二分  最后一个末尾0的个数 <= k的数
 * 末尾0的个数和n是有单调关系的 => n/5 + n/(5^2) + n/(5^3)
 */