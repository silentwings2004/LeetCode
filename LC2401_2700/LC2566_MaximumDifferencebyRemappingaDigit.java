package LC2401_2700;

public class LC2566_MaximumDifferencebyRemappingaDigit {
    /**
     * You are given an integer num. You know that Danny Mittal will sneakily remap one of the 10 possible digits
     * (0 to 9) to another digit.
     *
     * Return the difference between the maximum and minimum values Danny can make by remapping exactly one digit in num.
     *
     * Notes:
     *
     * When Danny remaps a digit d1 to another digit d2, Danny replaces all occurrences of d1 in num with d2.
     * Danny can remap a digit to itself, in which case num does not change.
     * Danny can remap different digits for obtaining minimum and maximum values respectively.
     * The resulting number after remapping can contain leading zeroes.
     * We mentioned "Danny Mittal" to congratulate him on being in the top 10 in Weekly Contest 326.
     *
     * Input: num = 11891
     * Output: 99009
     *
     * Input: num = 90
     * Output: 99
     *
     * Constraints:
     *
     * 1 <= num <= 10^8
     * @param num
     * @return
     */
    // time = O(n), space = O(n)
    public int minMaxDifference(int num) {
        return get(num, 9) - get(num, 0);
    }

    private int get(int x, int v) {
        char[] s = String.valueOf(x).toCharArray();
        int n = s.length, t = -1;
        for (int i = 0; i < n; i++) {
            int u = s[i] - '0';
            if (u != v) {
                if (t == -1) t = u;
                if (u == t) s[i] = (char)(v + '0');
            }
        }
        return Integer.parseInt(String.valueOf(s));
    }
}