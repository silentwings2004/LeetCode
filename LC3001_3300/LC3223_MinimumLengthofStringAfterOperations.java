package LC3001_3300;

public class LC3223_MinimumLengthofStringAfterOperations {
    /**
     * You are given a string s.
     *
     * You can perform the following process on s any number of times:
     *
     * Choose an index i in the string such that there is at least one character to the left of index i that is equal
     * to s[i], and at least one character to the right that is also equal to s[i].
     * Delete the closest character to the left of index i that is equal to s[i].
     * Delete the closest character to the right of index i that is equal to s[i].
     * Return the minimum length of the final string s that you can achieve.
     *
     * Input: s = "abaacbcbb"
     * Output: 5
     *
     * Input: s = "aa"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 2 * 10^5
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumLength(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                res += cnt[i] % 2 == 0 ? 2 : cnt[i] % 2;
            }
        }
        return res;
    }
}
/**
 * 每次选的 s[i] 至少出现 3 次数
 * 然后把 s[i] 的出现次数 -2
 * 只关心每个字母的出现次数
 * c 次 能操作多少次?
 * c = 3,5,7,9... => 剩余 1 个
 * c = 4,6,8... => 剩余 2 个
 */