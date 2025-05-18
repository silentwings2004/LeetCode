package LC3301_3600;

public class LC3361_ShiftDistanceBetweenTwoStrings {
    /**
     * You are given two strings s and t of the same length, and two integer arrays nextCost and previousCost.
     *
     * In one operation, you can pick any index i of s, and perform either one of the following actions:
     *
     * Shift s[i] to the next letter in the alphabet. If s[i] == 'z', you should replace it with 'a'. This operation
     * costs nextCost[j] where j is the index of s[i] in the alphabet.
     * Shift s[i] to the previous letter in the alphabet. If s[i] == 'a', you should replace it with 'z'. This operation
     * costs previousCost[j] where j is the index of s[i] in the alphabet.
     * The shift distance is the minimum total cost of operations required to transform s into t.
     *
     * Return the shift distance from s to t.
     *
     * Input: s = "abab", t = "baba", nextCost = [100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
     * previousCost = [1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * Output: 2
     *
     * Input: s = "leet", t = "code", nextCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
     * previousCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
     * Output: 31
     *
     * Constraints:
     *
     * 1 <= s.length == t.length <= 10^5
     * s and t consist only of lowercase English letters.
     * nextCost.length == previousCost.length == 26
     * 0 <= nextCost[i], previousCost[i] <= 10^9
     * @param s
     * @param t
     * @param nextCost
     * @param previousCost
     * @return
     */
    // time = O(n * 26), space = O(1)
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int n = s.length();
        long res = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a', v = t.charAt(i) - 'a';
            if (u == v) continue;
            int nxt = v > u ? v - u : v + 26 - u, pre = u > v ? u - v : u + 26 - v;
            long v1 = 0, v2 = 0;
            for (int j = 0; j < nxt; j++) v1 += nextCost[(u + j) % 26];
            for (int j = 0; j < pre; j++) v2 += previousCost[(u - j + 26) % 26];
            res += Math.min(v1, v2);
        }
        return res;
    }
}