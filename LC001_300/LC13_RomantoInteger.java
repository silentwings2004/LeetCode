package LC001_300;
import java.util.*;
public class LC13_RomantoInteger {
    /**
     * Given a roman numeral, convert it to an integer.
     *
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * Input: s = "MCMXCIV"
     * Output: 1994
     *
     * Constraints:
     *
     * 1 <= s.length <= 15
     * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
     * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) res -= map.get(s.charAt(i));
            else res += map.get(s.charAt(i));
        }
        return res;
    }
}
/**
 * 特判下前面的字母比后面的要小 => 减去
 * 如果>= => 直接加上去即可
 */