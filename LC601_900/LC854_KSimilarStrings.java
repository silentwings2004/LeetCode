package LC601_900;
import java.util.*;
public class LC854_KSimilarStrings {
    /**
     * Strings s1 and s2 are k-similar (for some non-negative integer k) if we can swap the positions of two letters in
     * s1 exactly k times so that the resulting string equals s2.
     *
     * Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are k-similar.
     *
     * Input: s1 = "ab", s2 = "ba"
     * Output: 1
     *
     * Input: s1 = "abc", s2 = "bca"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s1.length <= 20
     * s2.length == s1.length
     * s1 and s2 contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}.
     * s2 is an anagram of s1.
     * @param s1
     * @param s2
     * @return
     */
    // S1: IDA*
    // time = O(2^n), space = O(n)
    public int kSimilarity(String s1, String s2) {
        if (s1.equals(s2)) return 0;
        int depth = 1;
        while (!dfs(s1, s2, depth)) depth++;
        return depth;
    }

    private int h(String s1, String s2) {
        int res = 0, n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) res++;
        }
        return (res + 1) / 2;
    }

    private boolean dfs(String s1, String s2, int depth) {
        if (depth == 0) return s1.equals(s2);
        if (h(s1, s2) > depth) return false;

        int n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                for (int j = i + 1; j < n; j++) {
                    if (s2.charAt(i) == s1.charAt(j)) {
                        s1 = swap(s1, i, j);
                        if (dfs(s1, s2, depth - 1)) return true;
                        s1 = swap(s1, i, j);
                    }
                }
                break;
            }
        }
        return false;
    }

    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
        return String.valueOf(chars);
    }

    // S2: BFS
    // time = O(2^n), space = O(n)
    public int kSimilarity2(String s1, String s2) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        queue.offer(s1);
        set.add(s1);
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                if (cur.equals(s2)) return step;

                for (String next : getNext(cur, s2)) {
                    if (set.add(next)) queue.offer(next);
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> getNext(String s1, String s2) {
        List<String> res = new ArrayList<>();
        char[] chars = s1.toCharArray();

        int n = chars.length;
        for (int i = 0; i < n; i++) {
            if (chars[i] != s2.charAt(i)) {
                for (int j = i + 1; j < n; j++) {
                    if (chars[j] == s2.charAt(i)) {
                        swapChars(chars, i, j);
                        res.add(String.valueOf(chars));
                        swapChars(chars, i, j);
                    }
                }
                break;
            }
        }
        return res;
    }

    private void swapChars(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
/**
 * 所有置换都可以拆成若干个环 -> 转化成自环
 * 如果交换2个字符属于同一个环 -> 将这个环拆成2个环
 * 如果在环之间交换 -> 交换结果就是将2个环合并
 * 目标：将所有的环变成单环每操作一次，最多只能将环数+1 => 环内交换 n - m
 * 环内任取2个点取交换，都可以拆成2个环
 * 长度为k的环 -> 拆成一个长度为k-1和长度为1的两个环
 * 环的方案会不确定，所以要暴搜，每个环的入边和出边都不唯一
 * dfs -> 迭代加深 + 启发式 => IDA*
 * 每交换1次最多只能将2个字母变换到正确的位置上，先统计下不正确的位置有cnt个，最小的步数就是[cnt/2] 上取整，比如3对不同的，需要交换2次！
 */