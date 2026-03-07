package LC3601_3900;
import java.util.*;
public class LC3860_UniqueEmailGroups {
    /**
     * You are given an array of strings emails, where each string is a valid email address.
     *
     * Two email addresses belong to the same group if both their normalized local names and normalized domain names
     * are identical.
     *
     * The normalization rules are as follows:
     *
     * The local name is the part before the '@' symbol.
     * Ignore all dots '.'.
     * Ignore everything after the first '+', if present.
     * Convert to lowercase.
     * The domain name is the part after the '@' symbol.
     * Convert to lowercase.
     * Return an integer denoting the number of unique email groups after normalization.
     *
     * Input: emails = ["test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
     * "testemail+david@lee.tcode.com"]
     * Output: 2
     *
     * Input: emails = ["A@B.com", "a@b.com", "ab+xy@b.com", "a.b@b.com"]
     * Output: 2
     *
     * Input: emails = ["a.b+c.d+e@DoMain.com", "ab+xyz@domain.com", "ab@domain.com"]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= emails.length <= 1000
     * 1 <= emails[i].length <= 100
     * emails[i] consists of lowercase and uppercase English letters, digits, and the characters '.', '+', and '@'.
     * Each emails[i] contains exactly one '@' character.
     * All local and domain names are non-empty; local names do not start with '+'.
     * Domain names end with the ".com" suffix and contain at least one character before ".com".
     * @param emails
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public int uniqueEmailGroups(String[] emails) {
        HashSet<String> set = new HashSet<>();
        for (String e : emails) {
            String[] strs = e.split("@");
            set.add(helper(strs[0]) + convert(strs[1]));
        }
        return set.size();
    }

    private String helper(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (c == '.') continue;
            if (c == '+') break;
            sb.append(c);
        }
        return sb.toString();
    }

    private String convert(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(Character.toLowerCase(s.charAt(i)));
        return sb.toString();
    }
}
