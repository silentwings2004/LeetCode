package LC3001_3300;
import java.util.*;
public class LC3006_FindBeautifulIndicesintheGivenArrayI {
    /**
     * You are given a 0-indexed string s, a string a, a string b, and an integer k.
     *
     * An index i is beautiful if:
     *
     * 0 <= i <= s.length - a.length
     * s[i..(i + a.length - 1)] == a
     * There exists an index j such that:
     * 0 <= j <= s.length - b.length
     * s[j..(j + b.length - 1)] == b
     * |j - i| <= k
     * Return the array that contains beautiful indices in sorted order from smallest to largest.
     *
     * Input: s = "isawsquirrelnearmysquirrelhouseohmy", a = "my", b = "squirrel", k = 15
     * Output: [16,33]
     *
     * Input: s = "abcd", a = "a", b = "a", k = 4
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= k <= s.length <= 10^5
     * 1 <= a.length, b.length <= 10
     * s, a, and b contain only lowercase English letters.
     * @param s
     * @param a
     * @param b
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), len1 = a.length(), len2 = b.length();
        List<Integer> q1 = new ArrayList<>(), q2 = new ArrayList<>();
        for (int i = 0; i + len1 - 1 < n; i++) {
            if (s.substring(i, i + len1).equals(a)) q1.add(i);
        }
        for (int i = 0; i + len2 - 1 < n; i++) {
            if (s.substring(i, i + len2).equals(b)) q2.add(i);
        }
        if (q1.size() == 0 || q2.size() == 0) return res;
        for (int x : q1) {
            int pos1 = find1(q2, x + k);
            int y = q2.get(pos1);
            if (y >= x && y - x <= k) res.add(x);
            else {
                int pos2 = find2(q2, x - k);
                y = q2.get(pos2);
                if (x >= y && x - y <= k) res.add(x);
            }
        }
        return res;
    }

    private int find1(List<Integer> q2, int t) {
        int l = 0, r = q2.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q2.get(mid) <= t) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private int find2(List<Integer> q2, int t) {
        int l = 0, r = q2.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q2.get(mid) >= t) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}