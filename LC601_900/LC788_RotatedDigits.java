package LC601_900;

import java.util.Arrays;
import java.util.HashSet;

public class LC788_RotatedDigits {
    /**
     * An integer x is a good if after rotating each digit individually by 180 degrees, we get a valid number that is
     * different from x. Each digit must be rotated - we cannot choose to leave it alone.
     *
     * A number is valid if each digit remains a digit after rotation. For example:
     *
     * 0, 1, and 8 rotate to themselves,
     * 2 and 5 rotate to each other (in this case they are rotated in a different direction, in other words, 2 or 5 gets
     * mirrored),
     * 6 and 9 rotate to each other, and
     * the rest of the numbers do not rotate to any other number and become invalid.
     * Given an integer n, return the number of good integers in the range [1, n].
     *
     * Input: n = 10
     * Output: 4
     *
     * Input: n = 1
     * Output: 0
     *
     * Input: n = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int rotatedDigits(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            boolean flag = false;
            for (char c : s.toCharArray()) {
                if (c == '2' || c == '5' || c == '6' || c == '9') flag = true;
                else if (c == '0' || c == '1' || c == '8') continue;
                else {
                    flag = false;
                    break;
                }
            }
            if (flag) res++;
        }
        return res;
    }

    // S2:
    // time = O(nlogn), space = O(1)
    public int rotatedDigits2(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (check(i)) res++;
        }
        return res;
    }

    private boolean check(int x) {
        HashSet<Integer> s1 = new HashSet<>(Arrays.asList(0, 1, 8));
        HashSet<Integer> s2 = new HashSet<>(Arrays.asList(2, 5, 6, 9));
        boolean is_diff = false;
        while (x > 0) {
            int t = x % 10;
            x /= 10;
            if (!s1.contains(t) && !s2.contains(t)) return false;
            if (s2.contains(t)) is_diff = true;
        }
        return is_diff;
    }
}