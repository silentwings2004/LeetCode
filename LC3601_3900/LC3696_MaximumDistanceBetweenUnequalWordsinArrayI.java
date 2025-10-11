package LC3601_3900;
import java.util.*;
public class LC3696_MaximumDistanceBetweenUnequalWordsinArrayI {
    /**
     * You are given a string array words.
     *
     * Find the maximum distance between two distinct indices i and j such that:
     *
     * words[i] != words[j], and
     * the distance is defined as j - i + 1.
     * Return the maximum distance among all such pairs. If no valid pair exists, return 0.
     *
     * Input: words = ["leetcode","leetcode","codeforces"]
     * Output: 3
     *
     * Input: words = ["a","b","c","a","a"]
     * Output: 4
     *
     * Input: words = ["z","z","z"]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 10
     * words[i] consists of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n), space = O(1)
    public int maxDistance(String[] words) {
        int n = words.length;
        if (n == 1) return 0;
        int res = 0;
        for (int i = n - 1; i > 0; i--) {
            if (!words[i].equals(words[0])) {
                res = i + 1;
                break;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (!words[i].equals(words[n - 1])) {
                res = Math.max(res, n - i);
                break;
            }
        }
        return res;
    }
}