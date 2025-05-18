package LC3301_3600;

public class LC3302_FindtheLexicographicallySmallestValidSequence {
    /**
     * You are given two strings word1 and word2.
     *
     * A string x is called almost equal to y if you can change at most one character in x to make it identical to y.
     *
     * A sequence of indices seq is called valid if:
     *
     * The indices are sorted in ascending order.
     * Concatenating the characters at these indices in word1 in the same order results in a string that is almost equal
     * to word2.
     * Return an array of size word2.length representing the
     * lexicographically smallest
     *  valid sequence of indices. If no such sequence of indices exists, return an empty array.
     *
     * Note that the answer must represent the lexicographically smallest array, not the corresponding string formed by
     * those indices.
     *
     * Input: word1 = "vbcca", word2 = "abc"
     * Output: [0,1,2]
     *
     * Input: word1 = "bacdc", word2 = "abc"
     * Output: [1,2,4]
     *
     * Input: word1 = "aaaaaa", word2 = "aaabc"
     * Output: []
     *
     * Input: word1 = "abc", word2 = "ab"
     * Output: [0,1]
     *
     * Constraints:
     *
     * 1 <= word2.length < word1.length <= 3 * 10^5
     * word1 and word2 consist only of lowercase English letters.
     * @param word1
     * @param word2
     * @return
     */
    // time = O(n), space = O(n)
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] suf = new int[n + 1];
        suf[n] = m;
        for (int i = n - 1, j = m - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) j--;
            suf[i] = j + 1; // 以 i 为后缀能匹配到的 word2 的最后一个字符
        }

        int[] res = new int[m];
        boolean flag = false;
        for (int i = 0, j = 0; i < n; i++) {
            if (word1.charAt(i) == word2.charAt(j) || !flag && suf[i + 1] <= j + 1) { // i 后面能匹配的最大后缀在 j + 1 左边
                if (word1.charAt(i) != word2.charAt(j)) flag = true;
                res[j++] = i;
                if (j == m) return res;
            }
        }
        return new int[0];
    }
}
/**
 * 从特殊到一般
 * 从易到难
 * 先考虑不改的情况：子序列匹配 => 双指针
 * 如果不修改，那么就是一个子序列匹配的问题
 * 为了让下标数组的字典序最小，
 * 从左到右，能匹配就尽量匹配
 * 考虑可以修改：
 * 枚举修改哪个位置：以该位置为中心，分为2段，分别匹配前缀和后缀
 * 能改的时候修改一定是最好的
 */