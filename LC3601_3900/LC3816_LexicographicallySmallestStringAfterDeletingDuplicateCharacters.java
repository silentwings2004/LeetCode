package LC3601_3900;

public class LC3816_LexicographicallySmallestStringAfterDeletingDuplicateCharacters {
    /**
     * You are given a string s that consists of lowercase English letters.
     *
     * You can perform the following operation any number of times (possibly zero times):
     *
     * Choose any letter that appears at least twice in the current string s and delete any one occurrence.
     * Return the lexicographically smallest resulting string that can be formed this way.
     *
     * Input: s = "aaccb"
     * Output: "aacb"
     *
     * Input: s = "z"
     * Output: "z"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains lowercase English letters only.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String lexSmallestAfterDeletion(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int u = c - 'a';
            while (sb.length() > 0) {
                int v = sb.charAt(sb.length() - 1) - 'a';
                if (v > u && cnt[v] > 1) {
                    sb.setLength(sb.length() - 1);
                    cnt[v]--;
                } else break;
            }
            sb.append(c);
        }
        while (sb.length() > 0) {
            int v = sb.charAt(sb.length() - 1) - 'a';
            if (cnt[v] > 1) {
                sb.setLength(sb.length() - 1);
                cnt[v]--;
            } else break;
        }
        return sb.toString();
    }
}
/**
 * ref: LC316
 * 字典序最小 ->
 */