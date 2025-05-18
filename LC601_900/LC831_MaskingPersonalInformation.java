package LC601_900;

public class LC831_MaskingPersonalInformation {
    /**
     * You are given a personal information string s, representing either an email address or a phone number. Return
     * the masked personal information using the below rules.
     *
     * Email address:
     *
     * An email address is:
     *
     * A name consisting of uppercase and lowercase English letters, followed by
     * The '@' symbol, followed by
     * The domain consisting of uppercase and lowercase English letters with a dot '.' somewhere in the middle (not the
     * first or last character).
     * To mask an email:
     *
     * The uppercase letters in the name and domain must be converted to lowercase letters.
     * The middle letters of the name (i.e., all but the first and last letters) must be replaced by 5 asterisks "*****".
     * Phone number:
     *
     * A phone number is formatted as follows:
     *
     * The phone number contains 10-13 digits.
     * The last 10 digits make up the local number.
     * The remaining 0-3 digits, in the beginning, make up the country code.
     * Separation characters from the set {'+', '-', '(', ')', ' '} separate the above digits in some way.
     * To mask a phone number:
     *
     * Remove all separation characters.
     * The masked phone number should have the form:
     * "***-***-XXXX" if the country code has 0 digits.
     * "+*-***-***-XXXX" if the country code has 1 digit.
     * "+**-***-***-XXXX" if the country code has 2 digits.
     * "+***-***-***-XXXX" if the country code has 3 digits.
     * "XXXX" is the last 4 digits of the local number.
     *
     * Input: s = "LeetCode@LeetCode.com"
     * Output: "l*****e@leetcode.com"
     *
     * Input: s = "AB@qq.com"
     * Output: "a*****b@qq.com"
     *
     * Input: s = "1(234)567-890"
     * Output: "***-***-7890"
     *
     * Constraints:
     *
     * s is either a valid email or a phone number.
     * If s is an email:
     * 8 <= s.length <= 40
     * s consists of uppercase and lowercase English letters and exactly one '@' symbol and '.' symbol.
     * If s is a phone number:
     * 10 <= s.length <= 20
     * s consists of digits, spaces, and the symbols '(', ')', '-', and '+'.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String maskPII(String s) {
        if (s.indexOf('@') != -1) return work_email(s);
        else return work_phone(s);
    }

    private String work_email(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(Character.toLowerCase(c));
        s = sb.toString();
        int a = s.indexOf('@');
        String name1 = s.substring(0, a);
        String name2 = s.substring(a + 1);
        return name1.charAt(0) + "*****" + name1.charAt(name1.length() - 1) + "@" + name2;
    }

    private String work_phone(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) sb.append(c);
        }
        s = sb.toString();
        int n = s.length();
        if (n == 10) return "***-***-" + s.substring(6);
        return "+" + "*".repeat(n - 10) + "-***-***-" + s.substring(n - 4);
    }

    // S2
    // time = O(n), space = O(n)
    public String maskPII2(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        if (s.indexOf('@') != -1) {
            String[] strs = s.split("@");
            sb.append(Character.toLowerCase(strs[0].charAt(0))).append("*****").append(Character.toLowerCase(strs[0].charAt(strs[0].length() - 1)));
            sb.append('@');
            for (char c : strs[1].toCharArray()) sb.append(Character.toLowerCase(c));
        } else {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) sb.append(c);
            }
            s = sb.toString();
            n = s.length();
            int t = n - 10;
            sb = new StringBuilder();
            if (t == 0) sb.append("***-***-").append(s.substring(n - 4));
            if (t == 1) sb.append("+*-***-***-").append(s.substring(n - 4));
            if (t == 2) sb.append("+**-***-***-").append(s.substring(n - 4));
            if (t == 3) sb.append("+***-***-***-").append(s.substring(n - 4));
        }
        return sb.toString();
    }
}