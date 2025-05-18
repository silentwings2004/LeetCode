package LC3001_3300;
import java.util.*;
public class LC3081_ReplaceQuestionMarksinStringtoMinimizeItsValue {
    /**
     * You are given a string s. s[i] is either a lowercase English letter or '?'.
     *
     * For a string t having length m containing only lowercase English letters, we define the function cost(i) for an
     * index i as the number of characters equal to t[i] that appeared before it, i.e. in the range [0, i - 1].
     *
     * The value of t is the sum of cost(i) for all indices i.
     *
     * For example, for the string t = "aab":
     *
     * cost(0) = 0
     * cost(1) = 1
     * cost(2) = 0
     * Hence, the value of "aab" is 0 + 1 + 0 = 1.
     * Your task is to replace all occurrences of '?' in s with any lowercase English letter so that the value of s is
     * minimized.
     *
     * Return a string denoting the modified string with replaced occurrences of '?'. If there are multiple strings
     * resulting in the minimum value, return the
     * lexicographically smallest one.
     *
     * Input:  s = "???"
     * Output:  "abc"
     *
     * Input: s = "a?a?"
     * Output: "abac"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either a lowercase English letter or '?'.
     * @param s
     * @return
     */
    // time = O(26n + nlogn), space = O(n)
    public String minimizeStringValue(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] cnt = new int[26];

        for (int i = 0; i < n; i++) {
            if (chars[i] != '?') {
                int u = chars[i] - 'a';
                cnt[u]++;
            }
        }
        List<Integer> q = new ArrayList<>();
        List<Character> p = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                int t = Integer.MAX_VALUE;
                int u = -1;
                for (int j = 0; j < 26; j++) {
                    if (cnt[j] < t) {
                        t = cnt[j];
                        u = j;
                    }
                }
                q.add(i);
                p.add((char)(u + 'a'));
                cnt[u]++;
            }
        }

        Collections.sort(p);
        for (int i = 0; i < q.size(); i++) chars[q.get(i)] = p.get(i);
        return String.valueOf(chars);
    }

    // S2
    // time = O(nlogn + nlog26), space = O(n + 26)
    public String minimizeStringValue2(String s) {
        char[] chars = s.toCharArray();
        int[] cnt = new int[26];
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '?') cnt[chars[i] - 'a']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 0; i < 26; i++) pq.offer(new int[]{cnt[i], i});

        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                int[] t = pq.poll();
                int a = t[0], b = t[1];
                q.add(b);
                pq.offer(new int[]{a + 1, b});
            }
        }
        Collections.sort(q);
        for (int i = 0, j = 0; i < n; i++) {
            if (chars[i] == '?') chars[i] = (char)(q.get(j++) + 'a');
        }
        return String.valueOf(chars);
    }
}
/**
 * a...a...a
 * 0 + 1 + 2
 * cost 之和字母在 s 中的出现次数有关
 * x 个 a   x * (x - 1) / 2
 * y 个 b
 * 基本不等式
 * 26个字母的出现次数尽量的接近
 * 循环 q 次
 * 每次把 ? 改成出现次数最少的字母
 * => 最小堆模拟
 */