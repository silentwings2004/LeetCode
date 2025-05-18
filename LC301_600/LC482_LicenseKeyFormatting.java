package LC301_600;

public class LC482_LicenseKeyFormatting {
    /**
     * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
     * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
     *
     * We want to reformat the string s such that each group contains exactly k characters, except for the first group,
     * which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash
     * inserted between two groups, and you should convert all lowercase letters to uppercase.
     *
     * Return the reformatted license key.
     *
     * Input: s = "5F3Z-2e-9-w", k = 4
     * Output: "5F3Z-2E9W"
     *
     * Input: s = "2-5g-3-J", k = 2
     * Output: "2-5G-3J"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of English letters, digits, and dashes '-'.
     * 1 <= k <= 10^4
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int n = s.length(), cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') continue;
            else {
                sb.append(Character.toUpperCase(c));
                cnt++;
                if (cnt == k) {
                    sb.append('-');
                    cnt = 0;
                }
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') sb.setLength(sb.length() - 1);
        return sb.reverse().toString();
    }

    // S2
    // time = O(n), space = O(n)
    public String licenseKeyFormatting2(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != '-') sb.append(c);
        }
        StringBuilder res = new StringBuilder();
        int n = sb.length();
        for (int i = 0; i < n % k; i++) res.append(Character.toUpperCase(sb.charAt(i)));
        for (int i = n % k; i < n;) {
            if (res.length() > 0) res.append('-');
            for (int j = 0; j < k; j++) res.append(Character.toUpperCase(sb.charAt(i++)));
        }
        return res.toString();
    }
}
