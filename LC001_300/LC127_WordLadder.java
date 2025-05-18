package LC001_300;
import java.util.*;
public class LC127_WordLadder {
    /**
     * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
     * beginWord -> s1 -> s2 -> ... -> sk such that:
     *
     * Every adjacent pair of words differs by a single letter.
     * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
     * sk == endWord
     * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
     * transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
     *
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= beginWord.length <= 10
     * endWord.length == beginWord.length
     * 1 <= wordList.length <= 5000
     * wordList[i].length == beginWord.length
     * beginWord, endWord, and wordList[i] consist of lowercase English letters.
     * beginWord != endWord
     * All the words in wordList are unique.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    // time = O(n * c^2), space = O(n * c^2)  n 为 wordList 的长度，c 为列表中单词的长度。
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        int step = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String t = q.poll();
                if (t.equals(endWord)) return step;
                List<String> nexts = convert(t, dict);
                for (String next : nexts) {
                    q.offer(next);
                    dict.remove(next);
                }
            }
            step++;
        }
        return 0;
    }

    private List<String> convert(String s, HashSet<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char t = chars[i];
            for (int j = 0; j < 26; j++) {
                chars[i] = (char)(j + 'a');
                String str = String.valueOf(chars);
                if (chars[i] != t && dict.contains(str)) res.add(str);
            }
            chars[i] = t;
        }
        return res;
    }
}