package LC1201_1500;
import java.util.*;
public class LC1366_RankTeamsbyVotes {
    /**
     * In a special ranking system, each voter gives a rank from highest to lowest to all teams participating in the
     * competition.
     *
     * The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the
     * first position, we consider the second position to resolve the conflict, if they tie again, we continue this
     * process until the ties are resolved. If two or more teams are still tied after considering all positions, we
     * rank them alphabetically based on their team letter.
     *
     * You are given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams
     * according to the ranking system described above.
     *
     * Return a string of all teams sorted by the ranking system.
     *
     * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
     * Output: "ACB"
     *
     * Input: votes = ["WXYZ","XYZW"]
     * Output: "XWYZ"
     *
     * Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
     * Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
     *
     * Constraints:
     *
     * 1 <= votes.length <= 1000
     * 1 <= votes[i].length <= 26
     * votes[i].length == votes[j].length for 0 <= i, j < votes.length.
     * votes[i][j] is an English uppercase letter.
     * All characters of votes[i] are unique.
     * All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
     * @param votes
     * @return
     */
    // time = O(n * k + n * logn), space = O(n)
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        int[][] cnt = new int[26][n + 1];
        for (int i = 0; i < 26; i++) cnt[i][n] = i;
        for (String s : votes) {
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                cnt[c - 'A'][i]++;
            }
        }
        Arrays.sort(cnt, (o1, o2) -> {
            for (int i = 0; i < n; i++) {
                if (o1[i] != o2[i]) return o2[i] - o1[i];
            }
            return o1[n] - o2[n];
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append((char)('A' + cnt[i][n]));
        return sb.toString();
    }
}