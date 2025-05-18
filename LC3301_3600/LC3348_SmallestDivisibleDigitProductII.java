package LC3301_3600;
import java.util.*;
public class LC3348_SmallestDivisibleDigitProductII {
    /**
     * You are given a string num which represents a positive integer, and an integer t.
     *
     * A number is called zero-free if none of its digits are 0.
     *
     * Return a string representing the smallest zero-free number greater than or equal to num such that the product of
     * its digits is divisible by t. If no such number exists, return "-1".
     *
     * Input: num = "1234", t = 256
     * Output: "1488"
     *
     * Input: num = "12355", t = 50
     * Output: "12355"
     *
     * Input: num = "11111", t = 26
     * Output: "-1"
     *
     * Constraints:
     *
     * 2 <= num.length <= 2 * 10^5
     * num consists only of digits in the range ['0', '9'].
     * num does not contain leading zeros.
     * 1 <= t <= 10^14
     * @param num
     * @param t
     * @return
     */
    // time = O(n + D(logt)^5), space = O(n + (logt)^5)
    String s;
    int cnt;
    int[] res;
    HashSet<String> vis;
    public String smallestNumber(String num, long t) {
        long v = t;
        cnt = 0;
        for (int x : new int[]{2, 3, 5, 7}) {
            while (v % x == 0) {
                v /= x;
                cnt++;
            }
        }
        if (v > 1) return "-1";
        cnt = Math.max(cnt - num.length() + 1, 1);
        s = "0".repeat(cnt) + num;
        int n = s.length();
        res = new int[n];
        vis = new HashSet<>();

        dfs(0, t, true);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    private boolean dfs(int u, long t, boolean isLimit) {
        if (u == s.length()) return t == 1;
        if (!isLimit && !vis.add(u + "#" + t)) return false;

        int x = s.charAt(u) - '0';
        int low = 0;
        if (isLimit) {
            if (x > 0 || u < cnt) low = x;
            else low = 1;
        } else low = 1;

        for (int d = low; d < 10; d++) {
            res[u] = d;
            long new_t = d > 1 ? t / gcd(t, d) : t;
            if (dfs(u + 1, new_t, isLimit && d == x)) return true;
        }
        return false;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * 第一印象：大量分类讨论
 * 暴力枚举
 * 由于数位乘积中的质因子只有 2,3,5,7，如果 t 包含其他质因子，那么直接返回 −1
 * 如果 t 只包含质因子 2,3,5,7，那么答案一定存在
 * 仿照 数位 DP 的思路，写一个爆搜。
 * 在递归的过程中，用一个参数 isLimit 表示「是否受到 s 的约束」
 * 一般地，如果填的数字是 d，那么余下的数位，需要满足乘积是 t / GCD(t,d) 的倍数。
 * i：表示当前填到 s 的第 i 个数位了
 * t：表示 [i,n−1] 所填数位，需要满足乘积是 t 的倍数。
 * isLimit：表示是否受到 s 的约束。
 * 如果为 false，那么当前数位可以填 [1,9] 中的数；
 * 如果为 true，那么当前数位只能填 [max(s[i],1),9] 中的数。
 * 递归边界：当 i=n 时，如果 t=1，说明当前填法是符合要求的，返回 true，否则返回 false。
 * 递归入口：dfs(0,t,true)。
 * 注意:
 * 如果 s 很短但 t 很大，答案是会比 s 还长的。
 * 为了兼顾这种情况，我们可以往 s 的前面添加 max(cnt−n+1,1) 个前导 0。其中 cnt 是 t 的质因子个数。
 * 注意至少要添加 1 个前导零，因为可能有 s=999 这种情况，即使 t=2，答案（1112）长度也比 s 要长。
 * 注意添加前导零会影响可以填入的数字，当 isLimit=true 且 i < cnt 时，我们可以填入 0。
 */