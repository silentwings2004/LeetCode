package LC1501_1800;
import java.util.*;
public class LC1657_DetermineifTwoStringsAreClose {
    /**
     * Two strings are considered close if you can attain one from the other using the following operations:
     *
     * Operation 1: Swap any two existing characters.
     * For example, abcde -> aecdb
     * Operation 2: Transform every occurrence of one existing character into another existing character,
     * and do the same with the other character.
     * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
     * You can use the operations on either string as many times as necessary.
     *
     * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
     *
     * Input: word1 = "abc", word2 = "bca"
     * Output: true
     *
     * Input: word1 = "cabbba", word2 = "aabbss"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 105
     * word1 and word2 contain only lowercase English letters.
     *
     * @param word1
     * @param word2
     * @return
     */
    // time = O(n), space = O(1)
    public boolean closeStrings(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m != n) return false;
        int[] c1 = new int[26], c2 = new int[26];
        for (int i = 0; i < n; i++) {
            c1[word1.charAt(i) - 'a']++;
            c2[word2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i] && (c1[i] == 0 || c2[i] == 0)) {
                return false;
            }
        }
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }
}

/**
 * 1. have the same letter types
 * 2. have the same frequency array
 * 第一条规则说明单纯的乱序不影响判断两个字符串是否close。只要两个字符串排序后是一样的，那么就是close。
 * 第二条规则说明两个问题：首先两个字符串必须包含相同种类的字母，因为规则二本身无法创造出新的字母。
 * 如果两个字符串的字母种类不同，那么规则二是无法使得他们一致的（即使是乱序）。
 * 其次，同一个字符串中不同种类的字符可以互换彼此的频次，因此必然要求这两个字符串的字母频次分布也一致。
 * 因此一个close的必要条件就是将两个字符串的频次数组都分别排序，查验它们是否相同。
 */
