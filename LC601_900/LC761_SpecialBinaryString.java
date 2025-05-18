package LC601_900;
import java.util.*;
public class LC761_SpecialBinaryString {
    /**
     * Special binary strings are binary strings with the following two properties:
     *
     * The number of 0's is equal to the number of 1's.
     * Every prefix of the binary string has at least as many 1's as 0's.
     * You are given a special binary string s.
     *
     * A move consists of choosing two consecutive, non-empty, special substrings of s, and swapping them. Two strings
     * are consecutive if the last character of the first string is exactly one index before the first character of the
     * second string.
     *
     * Return the lexicographically largest resulting string possible after applying the mentioned operations on the
     * string.
     *
     * Input: s = "11011000"
     * Output: "11100100"
     *
     * Constraints:
     *
     * 1 <= s.length <= 50
     * s[i] is either '0' or '1'.
     * s is a special binary string.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(n)
    public String makeLargestSpecial(String s) {
        int n = s.length();
        if (n <= 2) return s;

        List<String> q = new ArrayList<>();
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);
            if (c == '1') cnt++;
            else {
                cnt--;
                if (cnt == 0) {
                    q.add('1' + makeLargestSpecial(sb.substring(1, sb.length() - 1)) + '0');
                    sb = new StringBuilder();
                }
            }
        }
        Collections.sort(q, (o1, o2) -> o2.compareTo(o1));
        sb = new StringBuilder();
        for (String x : q) sb.append(x);
        return sb.toString();
    }
}
/**
 * 首先，应该容易分析出：对于一个special string S，它整体可以拆分为一个或若干个不可再拆分的、连续的sub special string。
 * 对于每个不可再连续拆分的sub special string S'，它的首位一定是1，末位一定是0，中间一定还是一个special string，于是可能还可以继续拆分下去。
 * 写成式子就是： 任何 S = （1）ABCDEF（0）,首位的1和末位的0可能存在，而中间的ABCDEF都还是不可连续拆分的speical string，
 * 因为题目规定的swap的规则必须是在相邻的special string之间进行，
 * 所以对于任何一个S,只能通过内部的ABCDEF这些S'之间的位置调整,使得S自身调整至字典序最大（暂时不考虑ABCDEF内部的调整，假设它们已经各自字典序最优）。
 * 那么如何调整ABCDEF使得S的字典序最大呢？显然，只要让ABCDEF按照字典序从大到小排列即可。
 * 这就有了递归的思路。把S拆成ABCDEF，让它们各自递归成字典序最大，然后优化后的ABCDF按字典序重排，就能得到字典序最大的S。
 *
 * 题目等价于有效的括号系列
 * 卡特兰数
 * 每次将相邻的2个合法括号子串进行交换
 * 每次只能交换同级括号
 * 目标：括号序列的字典序最大
 * (()) () (())
 * 同级括号内部是独立的，每个括号内部是个子问题，递归分治
 * 最优解内部一定是字典序最大
 * 同级之间字典序最大 => 剑指offer AC.58
 */