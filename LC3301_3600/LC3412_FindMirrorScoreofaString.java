package LC3301_3600;
import java.util.*;
public class LC3412_FindMirrorScoreofaString {
    /**
     * You are given a string s.
     *
     * We define the mirror of a letter in the English alphabet as its corresponding letter when the alphabet is
     * reversed. For example, the mirror of 'a' is 'z', and the mirror of 'y' is 'b'.
     *
     * Initially, all characters in the string s are unmarked.
     *
     * You start with a score of 0, and you perform the following process on the string s:
     *
     * Iterate through the string from left to right.
     * At each index i, find the closest unmarked index j such that j < i and s[j] is the mirror of s[i]. Then, mark
     * both indices i and j, and add the value i - j to the total score.
     * If no such index j exists for the index i, move on to the next index without making any changes.
     * Return the total score at the end of the process.
     *
     * Input: s = "aczzx"
     * Output: 5
     *
     * Input: s = "abcdef"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public long calculateScore(String s) {
        Stack<Integer>[] pos = new Stack[26];
        for (int i = 0; i < 26; i++) pos[i] = new Stack<>();
        int n = s.length();
        long res = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            int v = 25 - u;
            if (pos[v].isEmpty()) pos[u].push(i);
            else {
                int j = pos[v].pop();
                res += i - j;
            }
        }
        return res;
    }
}