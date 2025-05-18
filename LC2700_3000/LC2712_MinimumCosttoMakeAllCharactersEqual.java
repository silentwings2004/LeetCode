package LC2700_3000;

public class LC2712_MinimumCosttoMakeAllCharactersEqual {
    /**
     * You are given a 0-indexed binary string s of length n on which you can apply two types of operations:
     *
     * Choose an index i and invert all characters from index 0 to index i (both inclusive), with a cost of i + 1
     * Choose an index i and invert all characters from index i to index n - 1 (both inclusive), with a cost of n - i
     * Return the minimum cost to make all characters of the string equal.
     *
     * Invert a character means if its value is '0' it becomes '1' and vice-versa.
     *
     * Input: s = "0011"
     * Output: 2
     *
     * Input: s = "010101"
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= s.length == n <= 10^5
     * s[i] is either '0' or '1'
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public long minimumCost(String s) {
        int n = s.length();
        String s1 = s.substring(0, n / 2), s2 = s.substring(n / 2);
        s2 = new StringBuilder(s2).reverse().toString();
        long op1 = cal(s1, 0) + cal(s2, 0);
        long op2 = cal(s1, 1) + cal(s2, 1);
        return Math.min(op1, op2);
    }

    private long cal(String s, int t) {
        int n = s.length();
        long res = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) - '0' == t) continue;
            res += i + 1;
            int j = i - 1;
            while (j >= 0 && s.charAt(j) - '0' == 1 - t) j--;
            res += j + 1;
            i = j;
        }
        return res;
    }
}