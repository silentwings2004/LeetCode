package LC001_300;
import java.util.*;
public class LC126_WordLadderII {
    /**
     * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
     * beginWord -> s1 -> s2 -> ... -> sk such that:
     *
     * Every adjacent pair of words differs by a single letter.
     * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
     * sk == endWord
     * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation
     * sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be
     * returned as a list of the words [beginWord, s1, s2, ..., sk].
     *
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
     *
     * Constraints:
     *
     * 1 <= beginWord.length <= 5
     * endWord.length == beginWord.length
     * 1 <= wordList.length <= 1000
     * wordList[i].length == beginWord.length
     * beginWord, endWord, and wordList[i] consist of lowercase English letters.
     * beginWord != endWord
     * All the words in wordList are unique.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    // time = O(n * k^2 + a), space = O(n * k)  k: 单词的长度，n: wordList的长度 a: 所有起点到终点的路径数
    List<List<String>> res;
    List<String> path;
    HashMap<String, List<String>> map;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        HashSet<String> dict = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        map = new HashMap<>();
        boolean flag = false;
        while (!q.isEmpty()) {
            int size = q.size();
            HashSet<String> set = new HashSet<>();
            while (size-- > 0) {
                String t = q.poll();
                if (t.equals(endWord)) flag = true;
                List<String> nexts = convert(t, dict);
                for (String next : nexts) {
                    if (!set.contains(next)) {
                        map.putIfAbsent(next, new ArrayList<>());
                        q.offer(next);
                        set.add(next);
                    }
                    map.get(next).add(t);
                }
            }
            dict.removeAll(set);
            if (flag) break;
        }

        if (flag) {
            path.add(endWord);
            dfs(endWord, beginWord);
        }
        return res;
    }

    private void dfs(String a, String b) {
        if (a.equals(b)) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (String next : map.get(a)) {
            path.add(0, next);
            dfs(next, b);
            path.remove(0);
        }
    }

    private List<String> convert(String s, HashSet<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
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
/**
 * A, {B, D}, {C}
 * 记录下前面是通过什么走到C的，用一个HashMap记录下
 * prev[C] = {B, D}
 * prev[B] = {A}
 * prev[D] = {A}
 * 所有元素，不管后面加什么，所有元素都是从beginWord开始
 * 根据哈希表肯定可以倒推
 */
