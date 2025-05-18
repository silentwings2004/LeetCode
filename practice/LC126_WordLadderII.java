package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: WordLadderII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 126. Word Ladder II
 */
public class LC126_WordLadderII {
    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation
     * sequence(s) from beginWord to endWord, such that:
     *
     * Only one letter can be changed at a time
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * Note:
     *
     * Return an empty list if there is no such transformation sequence.
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
     * Output:
     * [
     *   ["hit","hot","dot","dog","cog"],
     *   ["hit","hot","lot","log","cog"]
     * ]
     * Example 2:
     *
     * Input:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * Output: []
     *
     * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    // S1: One direction
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new LinkedList<>();
        // corner case
        if (beginWord == null || endWord == null || wordList == null) return res;

        HashSet<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        HashMap<String, List<String>> graph = new HashMap<>();
        boolean isOver = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            HashSet<String> levelVisited = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = convert(cur, dict);
                for (String next : nexts) {
                    if (next.equals(endWord)) isOver = true;
                    if (!dict.contains(next)) continue;
                    if (levelVisited.add(next)) {
                        queue.offer(next);
                        graph.put(next, new ArrayList<>());
                    }
                    graph.get(next).add(cur);
                }
            }
            dict.removeAll(levelVisited);
            if (isOver) {
                List<String> path = new LinkedList<>();
                path.add(endWord);
                dfs(res, endWord, beginWord, graph, path);
                return res;
            }
        }
        return res;
    }

    private void dfs(List<List<String>> res, String cur, String end, HashMap<String, List<String>> graph,
                     List<String> path) {
        // base case
        if (cur.equals(end)) {
            res.add(0, new ArrayList<>(path));
            return;
        }

        for (String next : graph.get(cur)) {
            path.add(0, next);
            dfs(res, next, end, graph, path);
            path.remove(0);
        }
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
