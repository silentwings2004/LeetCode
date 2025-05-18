package LC001_300;
import java.util.*;
public class LC38_CountandSay {
    /**
     * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
     *
     * countAndSay(1) = "1"
     * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a
     * different digit string.
     * To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a
     * contiguous section all of the same character. Then for each group, say the number of characters, then say the
     * character. To convert the saying into a digit string, replace the counts with a number and concatenate every
     * saying.
     *
     * Input: n = 4
     * Output: "1211"
     *
     * Constraints:
     *
     * 1 <= n <= 30
     * @param n
     * @return
     */
    // time = O(n * m), space = O(m)  n: 给定的正整数， m: 生成的字符串中的最大长度
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 0; i < n - 1; i++) {
            StringBuilder sb = new StringBuilder();
            int j = 0, m = s.length();
            while (j < m) {
                int k = j + 1;
                while (k < m && s.charAt(k) == s.charAt(j)) k++;
                sb.append(k - j).append(s.charAt(j));
                j = k;
            }
            s = sb.toString();
        }
        return s;
    }
}