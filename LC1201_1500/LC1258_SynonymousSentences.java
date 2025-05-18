package LC1201_1500;
import java.util.*;
public class LC1258_SynonymousSentences {
    /**
     * You are given a list of equivalent string pairs synonyms where synonyms[i] = [si, ti] indicates that si and ti
     * are equivalent strings. You are also given a sentence text.
     *
     * Return all possible synonymous sentences sorted lexicographically.
     *
     * Input: synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]], text = "I am happy today but was sad
     * yesterday"
     * Output: ["I am cheerful today but was sad yesterday","I am cheerful today but was sorrow yesterday","I am happy
     * today but was sad yesterday","I am happy today but was sorrow yesterday","I am joy today but was sad yesterday",
     * "I am joy today but was sorrow yesterday"]
     *
     * Input: synonyms = [["happy","joy"],["cheerful","glad"]], text = "I am happy today but was sad yesterday"
     * Output: ["I am happy today but was sad yesterday","I am joy today but was sad yesterday"]
     *
     * Constraints:
     *
     * 0 <= synonyms.length <= 10
     * synonyms[i].length == 2
     * 1 <= si.length, ti.length <= 10
     * si != ti
     * text consists of at most 10 words.
     * All the pairs of synonyms are unique.
     * The words of text are separated by single spaces.
     * @param synonyms
     * @param text
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 25;
    int[] p;
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        p = new int[N];
        for (int i = 0; i < N; i++) p[i] = i;
        HashMap<String, Integer> ids = new HashMap<>();
        HashMap<Integer, List<String>> map = new HashMap<>();
        int idx = 0;
        for (List<String> e : synonyms) {
            String s = e.get(0), t = e.get(1);
            ids.putIfAbsent(s, idx++);
            ids.putIfAbsent(t, idx++);
            int a = ids.get(s), b = ids.get(t);
            if (find(a) != find(b)) p[find(a)] = find(b);
        }

        for (String key : ids.keySet()) {
            int id = ids.get(key);
            int fa = find(id);
            map.putIfAbsent(fa, new ArrayList<>());
            map.get(fa).add(key);
        }

        String[] strs = text.split(" ");
        Queue<String> q = new LinkedList<>();
        q.offer("");
        for (String s : strs) {
            int sz = q.size();
            if (ids.containsKey(s)) {
                int id = ids.get(s);
                int fa = find(id);
                while (sz-- > 0) {
                    String t = q.poll();
                    for (String k : map.get(fa)) {
                        q.offer(t + k + " ");
                    }
                }
            } else {
                while (sz-- > 0) {
                    String t = q.poll();
                    q.offer(t + s + " ");
                }
            }
        }
        List<String> res = new ArrayList<>();
        while (!q.isEmpty()) {
            String t = q.poll();
            res.add(t.substring(0, t.length() - 1));
        }
        Collections.sort(res);
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}