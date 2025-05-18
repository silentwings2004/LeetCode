package LC1201_1500;

public class LC1404_NumberofStepstoReduceaNumberinBinaryRepresentationtoOne {
    /**
     * Given the binary representation of an integer as a string s, return the number of steps to reduce it to 1 under
     * the following rules:
     *
     * If the current number is even, you have to divide it by 2.
     *
     * If the current number is odd, you have to add 1 to it.
     *
     * It is guaranteed that you can always reach one for all test cases.
     *
     * Input: s = "1101"
     * Output: 6
     *
     * Input: s = "10"
     * Output: 1
     *
     * Input: s = "1"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * s consists of characters '0' or '1'
     * s[0] == '1'
     * @param s
     * @return
     */
    // S1: simulation
    // time = O(n), space = O(n)
    public int numSteps(String s) {
        if (s.equals("1")) return 0;
        int res = 0;
        while (!s.equals("1")) {
            if (s.charAt(s.length() - 1) == '0') s = s.substring(0, s.length() - 1);
            else {
                char[] chars = s.toCharArray();
                int i = chars.length - 1;
                while (i >= 0 && chars[i] == '1') chars[i--] = '0';
                if (i >= 0) chars[i] = '1';
                s = String.valueOf(chars);
                if (i < 0) s = "1" + s;
            }
            res++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int numSteps2(String s) {
        int n = s.length(), res = 0, carry = 0;
        for (int i = n - 1; i >= 0; i--) {
            int t = s.charAt(i) - '0' + carry;
            if (t == 1 && i == 0) break;
            carry = t / 2;
            t %= 2;
            if (t == 0) res++;
            else {
                res += 2;
                carry++;
            }
        }
        return res;
    }

    // S3
    // time = O(n), space = O(1)
    public int numSteps3(String s) {
        int n = s.length(), res = 0;
        boolean flag = false;
        for (int i = n - 1; i >= 0; i--) {
            int u = s.charAt(i) - '0';
            if (u == 0) res += flag ? 2 : 1;
            else {
                if (!flag) {
                    if (i != 0) res += 2;
                    flag = true;
                } else res++;
            }
        }
        return res;
    }
}