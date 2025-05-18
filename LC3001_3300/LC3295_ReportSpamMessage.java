package LC3001_3300;
import java.util.*;
public class LC3295_ReportSpamMessage {
    /**
     * You are given an array of strings message and an array of strings bannedWords.
     *
     * An array of words is considered spam if there are at least two words in it that exactly match any word in
     * bannedWords.
     *
     * Return true if the array message is spam, and false otherwise.
     *
     * Input: message = ["hello","world","leetcode"], bannedWords = ["world","hello"]
     * Output: true
     *
     * Input: message = ["hello","programming","fun"], bannedWords = ["world","programming","leetcode"]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= message.length, bannedWords.length <= 10^5
     * 1 <= message[i].length, bannedWords[i].length <= 15
     * message[i] and bannedWords[i] consist only of lowercase English letters.
     * @param message
     * @param bannedWords
     * @return
     */
    // time = O(n + m), space = O(m)
    public boolean reportSpam(String[] message, String[] bannedWords) {
        HashSet<String> set = new HashSet<>();
        for (String s : bannedWords) set.add(s);
        int cnt = 0;
        for (String x : message) {
            if (set.contains(x)) cnt++;
        }
        return cnt >= 2;
    }
}