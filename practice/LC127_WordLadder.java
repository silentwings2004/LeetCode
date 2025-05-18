package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: WordLadder
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 127. Word Ladder
 */
public class LC127_WordLadder {
    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
     * transformation sequence from beginWord to endWord, such that:
     *
     * Only one letter can be changed at a time.
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * Note:
     *
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * Example 1:
     *
     * Input:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * Output: 5
     *
     * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * Example 2:
     *
     * Input:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * Output: 0
     *
     * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // corner case
        if (beginWord == null || endWord == null || wordList == null) {
            return -1;
        }

        HashSet<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int minLen = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = convert(cur, dict);
                for (String next : nexts) {
                    if (next.equals(endWord)) return minLen + 1;
                    queue.offer(next);
                    dict.remove(next);
                }
            }
            minLen++;
        }
//        throw new IllegalStateException("No path found!");
        return 0;
    }

    private List<String> convert(String cur, HashSet<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            char temp = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                chars[i] = j;
                String str = String.valueOf(chars);
                if (j != temp && dict.contains(str)) res.add(str);
            }
            chars[i] = temp;
        }
        return res;
    }
}
