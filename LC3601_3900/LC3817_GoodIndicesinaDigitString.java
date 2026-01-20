package LC3601_3900;
import java.util.*;
public class LC3817_GoodIndicesinaDigitString {
    /**
     * You are given a string s consisting of digits.
     *
     * An index i is called good if there exists a substring of s that ends at index i and is equal to the decimal
     * representation of i.
     *
     * Return an integer array of all good indices in increasing order.
     *
     * Input: s = "0234567890112"
     * Output: [0,11,12]
     *
     * Input: s = "01234"
     * Output: [0,1,2,3,4]
     *
     * Input: s = "12345"
     * Output: []
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s only consists of digits from '0' to '9'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> goodIndices(String s) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        for (int i = 0, j = 0, t = 0, m = 1, x = 10; i < n; i++) {
            if (i == x) {
                m++;
                x *= 10;
            }
            if (i - j + 1 > m) t -= (s.charAt(j++) - '0') * (int)Math.pow(10, i - j);
            t = t * 10 + (s.charAt(i) - '0');
            if (t == i) res.add(i);
        }
        return res;
    }
}