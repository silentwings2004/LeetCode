package LC1201_1500;
import java.util.*;
public class LC1291_SequentialDigits {
    /**
     * n integer has sequential digits if and only if each digit in the number is one more than the previous digit.
     *
     * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
     *
     * Input: low = 100, high = 300
     * Output: [123,234]
     *
     * Constraints:
     *
     * 10 <= low <= high <= 10^9
     * @param low
     * @param high
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        String s = String.valueOf(low), t = String.valueOf(high);
        int m = s.length(), n = t.length();
        for (int len = m; len <= n; len++) {
            for (int i = 1; i + len - 1 <= 9; i++) {
                int v = i, last = i;
                for (int j = 1; j < len; j++) {
                    v = v * 10 + (last + 1);
                    last++;
                }
                if (v >= low && v <= high) res.add(v);
            }
        }
        return res;
    }

    // S2:
    // time = O(1), space = O(1)
    public List<Integer> sequentialDigits2(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int x = 0;
            for (int j = i; j <= 9; j++) {
                x = x * 10 + j;
                if (x >= low && x <= high) res.add(x);
            }
        }
        Collections.sort(res);
        return res;
    }
}
