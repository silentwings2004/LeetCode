package LC001_300;
import java.util.*;
public class LC6_ZigZagConversion {
    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
     * display this pattern in a fixed font for better legibility)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     *
     * Input: s = "PAYPALISHIRING", numRows = 3
     * Output: "PAHNAPLSIIGYIR"
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of English letters (lower-case and upper-case), ',' and '.'.
     * 1 <= numRows <= 1000
     * @param s
     * @param numRows
     * @return
     */
    // time = O(n), space = O(n)
    public String convert(String s, int numRows) {
        int m = numRows;
        if (m == 1) return s; // 必须特判，否则m = 1会导致下面死循环！

        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (i == 0 || i == m - 1) {
                for (int j = i; j < n; j += 2 * m - 2) {
                    sb.append(s.charAt(j));
                }
            } else {
                for (int j = i, k = 2 * m - 2 - i; j < n || k < n; j += 2 * m - 2, k += 2 * m - 2) {
                    if (j < n) sb.append(s.charAt(j));
                    if (k < n) sb.append(s.charAt(k));
                }
            }
        }
        return sb.toString();
    }
}
/**
 * 观察这个数列的周期性。一个周期的元素数目M=numRows*2-2，因此这个数列的周期数目是N=(len(s)-1)/M+1。
 * 注意这种根据M计算Ｎ的的技巧。
 * 在CUDA编程中，确定block数目的计算和此很相似。
 * 在每个周期中，第一行是第０个元素，第二行是第１和M-1个元素，第三行是第２和Ｍ-2个元素，直至最后一行是第numRows-1个元素。
 * 所以我们只要按行遍历，在每一行中将Ｎ个周期里对应该行的数字都找出来。
 */