package LC001_300;
import java.util.*;
public class LC93_RestoreIPAddresses {
    /**
     * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s.
     * You can return them in any order.
     *
     * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single
     * dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses
     * and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
     *
     * Input: s = "25525511135"
     * Output: ["255.255.11.135","255.255.111.35"]
     *
     * Constraints:
     *
     * 0 <= s.length <= 3000
     * s consists of digits only.
     * @param s
     * @return
     */
    // time = O(C(n - 1, 3)), space = O(1)
    List<String> res;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        dfs(s, 0, 0, new StringBuilder());
        return res;
    }

    private void dfs(String s, int u, int k, StringBuilder sb) {
        if (u == s.length()) {
            if (k == 4) {
                sb.setLength(sb.length() - 1);
                res.add(sb.toString());
            }
            return;
        }
        if (k == 4) return;

        int len = sb.length();
        for (int i = u, t = 0; i < s.length(); i++) {
            if (i > u && s.charAt(u) == '0') break;
            t = t * 10 + s.charAt(i) - '0';
            if (t <= 255) {
                sb.append(t).append('.');
                dfs(s, i + 1, k + 1, sb);
                sb.setLength(len);
            } else break;
        }
    }
}