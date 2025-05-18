package LC301_600;
import java.util.*;
public class LC466_CountTheRepetitions {
    /**
     * We define str = [s, n] as the string str which consists of the string s concatenated n times.
     *
     * For example, str == ["abc", 3] =="abcabcabc".
     * We define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it
     * becomes s1.
     *
     * For example, s1 = "abc" can be obtained from s2 = "abdbec" based on our definition by removing the bolded
     * underlined characters.
     * You are given two strings s1 and s2 and two integers n1 and n2. You have the two strings str1 = [s1, n1] and
     * str2 = [s2, n2].
     *
     * Return the maximum integer m such that str = [str2, m] can be obtained from str1.
     *
     * Input: s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
     * Output: 2
     *
     * Input: s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s1.length, s2.length <= 100
     * s1 and s2 consist of lowercase English letters.
     * 1 <= n1, n2 <= 10^6
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    // time = O(n1 * m), space = O(n1)
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        List<Integer> cnt = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int m = s1.length(), n = s2.length();
        int k = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m; j++) {
                if (s1.charAt(j) == s2.charAt(k % n)) k++;
            }
            cnt.add(k); // 每次匹配玩一个s1，就把s2的指针位置k存下来
            if (map.containsKey(k % n)) { // 计算一下循环节里一共有多少个s1
                int a = i - map.get(k % n); // 计算一下循环节里能匹配多少个s2中的字符
                int b = k - cnt.get(map.get(k % n)); // 循环节中匹配了多少个字符
                int res = (n1 - i - 1) / a * b;
                // 先遍历不完整循环节的s1个数
                for (int u = 0; u < (n1 - i - 1) % a; u++) {
                    for (int j = 0; j < m; j++) {
                        if (s1.charAt(j) == s2.charAt(k % n)) k++;
                    }
                }
                res += k;  // 这里k既包含循环节出现之前匹配的字符，也包含最后的不完整的循环节
                return res / n / n2;
            }
            map.put(k % n, i);
        }
        return k / n / n2;
    }
}
/**
 * 找规律：
 * 1. 倍增
 * 2. 循环节
 * 遍历完第一个s1之后，看下能匹配s2多少个字符
 * (1) 用了多少个s1
 * (2) 匹配了多少个s2的字符
 */