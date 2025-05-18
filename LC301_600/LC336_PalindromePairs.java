package LC301_600;
import java.util.*;
public class LC336_PalindromePairs {
    /**
     * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the
     * concatenation of the two words words[i] + words[j] is a palindrome.
     *
     * Input: words = ["abcd","dcba","lls","s","sssll"]
     * Output: [[0,1],[1,0],[3,2],[2,4]]
     *
     * Constraints:
     *
     * 1 <= words.length <= 5000
     * 0 <= words[i].length <= 300
     * words[i] consists of lower-case English letters.
     * @param words
     * @return
     */
    // time = O(nlogn + n * k^2), space = O(n * k)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (words == null || words.length == 0) return res;

        int n = words.length;
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
        }
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length()); // 通过排序，后面扫一个加一个的方法来去重！
        for (int j = 0; j < n; j++) {
            String s = words[j];
            for (int k = 0; k <= s.length(); k++) {
                String s1 = s.substring(0, k);
                String s2 = s.substring(k);
                if (isPalin(s1)) {
                    String t = s2;
                    t = reverse(t);
                    if (!t.equals(s) && set.contains(t)) res.add(Arrays.asList(map.get(t), map.get(s)));
                }
                if (isPalin(s2)) {
                    String t = s1;
                    t = reverse(t);
                    if (!t.equals(s) && set.contains(t)) res.add(Arrays.asList(map.get(s), map.get(t)));
                }
            }
            set.add(words[j]);
        }
        return res;
    }

    private boolean isPalin(String s) {
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    // S2
    // time = O(n * k^2), space = O(n * k)
    public List<List<Integer>> palindromePairs2(String[] words) {
        int n = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String w = reverse(words[i]);
            map.put(w, i);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String w = words[i];
            for (int j = 0; j <= w.length(); j++) {
                String left = w.substring(0, j), right = w.substring(j);
                if (isPalin(right) && map.containsKey(left) && map.get(left) != i) {
                    res.add(Arrays.asList(i, map.get(left)));
                }
                if (isPalin(left) && map.containsKey(right) && map.get(right) != i && w.length() != words[map.get(right)].length()) {
                    res.add(Arrays.asList(map.get(right), i));
                }
            }
        }
        return res;
    }
}
/**
 * O(n^2)
 * {i, j} 快速精准的去找，效率更高
 * 如果i在j之前
 * xxxx yyy yyyyy
 * 通过一个set，根据当前第j个串，看它xxxx在不在这个set里面 => 要遍历长度次
 * O(n * l)
 *
 * 枚举较长的串 wi
 * 有多少个j可以组成第一种形式，枚举分界点 => right回文串，有没有一个单词翻转后 = left (哈希表把每个单词翻转后的结果存下来)
 * O(l)*O(l)*O(n) = O(n*l^2)
 * 如果长度相等的话，只在第一种情况中算一遍，第二种情况就不算了 => 避免重复，只会在算i的时候把j算进去
 */
