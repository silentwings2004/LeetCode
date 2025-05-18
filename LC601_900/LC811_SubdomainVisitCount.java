package LC601_900;
import java.util.*;
public class LC811_SubdomainVisitCount {
    /**
     * A website domain "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the
     * next level, we have "leetcode.com" and at the lowest level, "discuss.leetcode.com". When we visit a domain like
     * "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
     *
     * A count-paired domain is a domain that has one of the two formats "rep d1.d2.d3" or "rep d1.d2" where rep is the
     * number of visits to the domain and d1.d2.d3 is the domain itself.
     *
     * For example, "9001 discuss.leetcode.com" is a count-paired domain that indicates that discuss.leetcode.com was
     * visited 9001 times.
     * Given an array of count-paired domains cpdomains, return an array of the count-paired domains of each subdomain
     * in the input. You may return the answer in any order.
     *
     * Input: cpdomains = ["9001 discuss.leetcode.com"]
     * Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
     *
     * Input: cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
     * Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
     *
     * Constraints:
     *
     * 1 <= cpdomain.length <= 100
     * 1 <= cpdomain[i].length <= 100
     * cpdomain[i] follows either the "repi d1i.d2i.d3i" format or the "repi d1i.d2i" format.
     * repi is an integer in the range [1, 10^4].
     * d1i, d2i, and d3i consist of lowercase English letters.
     * @param cpdomains
     * @return
     */
    // S1
    // time = O(L), space = O(L)  L: 数组 cpdomains 中的所有字符串长度之和
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : cpdomains) {
            int k = str.indexOf(' ');
            int c = Integer.parseInt(str.substring(0, k));
            str = str.substring(k + 1);
            while (true) {
                map.put(str, map.getOrDefault(str, 0) + c);
                k = str.indexOf(".");
                if (k == -1) break;
                str = str.substring(k + 1);
            }
        }

        List<String> res = new ArrayList<>();
        for (String k : map.keySet()) {
            res.add(map.get(k) + " " + k);
        }
        return res;
    }

    // S2
    // time = O(L), space = O(L)
    public List<String> subdomainVisits2(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            int k = s.indexOf(' ');
            int c = Integer.parseInt(s.substring(0, k));
            s = s.substring(k + 1);
            while (true) {
                map.put(s, map.getOrDefault(s, 0) + c);
                k = s.indexOf('.');
                if (k == -1) break;
                s = s.substring(k + 1);
            }
        }

        List<String> res = new ArrayList<>();
        for (String key : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(map.get(key)).append(' ').append(key);
            res.add(sb.toString());
        }
        return res;
    }
}