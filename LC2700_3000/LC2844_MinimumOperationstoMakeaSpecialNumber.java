package LC2700_3000;

public class LC2844_MinimumOperationstoMakeaSpecialNumber {
    /**
     * You are given a 0-indexed string num representing a non-negative integer.
     *
     * In one operation, you can pick any digit of num and delete it. Note that if you delete all the digits of num,
     * num becomes 0.
     *
     * Return the minimum number of operations required to make num special.
     *
     * An integer x is considered special if it is divisible by 25.
     *
     * Input: num = "2245047"
     * Output: 2
     *
     * Input: num = "2908305"
     * Output: 3
     *
     * Input: num = "10"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= num.length <= 100
     * num only consists of digits '0' through '9'.
     * num does not contain any leading zeros.
     * @param num
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int minimumOperations(String num) {
        int n = num.length(), res = n;
        for (int i = n - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (c == '5') {
                int j = i - 1;
                while (j >= 0 && num.charAt(j) != '2' && num.charAt(j) != '7') j--;
                if (j < 0) continue;
                res = Math.min(res, i - j - 1 + (n - 1 - i));
            } else if (c == '0') {
                int j = i - 1;
                while (j >= 0 && num.charAt(j) != '5' && num.charAt(j) != '0') j--;
                res = Math.min(res, i - j - 1 + (n - 1 - i));
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int minimumOperations2(String num) {
        int n = num.length();
        int res = num.indexOf('0') != -1 ? n - 1 : n;
        res = Math.min(res, helper(num, "25"));
        res = Math.min(res, helper(num, "50"));
        res = Math.min(res, helper(num, "75"));
        res = Math.min(res, helper(num, "00"));
        return res;
    }

    private int helper(String s, String t) {
        int n = s.length(), m = t.length();
        int i = n - 1, j = m - 1;
        while (i >= 0 && j >= 0) {
            if (s.charAt(i) == t.charAt(j)) j--;
            i--;
        }
        if (j >= 0) return n;
        if (t.equals("00")) {
            if (i >= 0) return n - m - (i + 1);
            return n;
        }
        return n - m - (i + 1);
    }
}
/**
 * 什么样的数可以被25整除
 * 0 25 50 75 100 125 150 175 200 ...
 *
 * 00
 * 25
 * 50
 * 75
 *
 * x = 100k
 * x = 100k + 25
 * x = 100k + 50
 * x = 100k + 75
 * 看末尾的2个数字，最多操作字符串的长度
 */
