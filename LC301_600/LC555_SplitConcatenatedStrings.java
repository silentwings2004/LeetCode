package LC301_600;

public class LC555_SplitConcatenatedStrings {
    /**
     * You are given an array of strings strs. You could concatenate these strings together into a loop, where for each
     * string, you could choose to reverse it or not. Among all the possible loops
     *
     * Return the lexicographically largest string after cutting the loop, which will make the looped string into a
     * regular one.
     *
     * Specifically, to find the lexicographically largest string, you need to experience two phases:
     *
     * Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same
     * order as given.
     * Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one
     * starting from the character at the cutpoint.
     * And your job is to find the lexicographically largest one among all the possible regular strings.
     *
     * Input: strs = ["abc","xyz"]
     * Output: "zyxcba"
     *
     * Input: strs = ["abc"]
     * Output: "cba"
     *
     * Constraints:
     *
     * 1 <= strs.length <= 1000
     * 1 <= strs[i].length <= 1000
     * 1 <= sum(strs[i].length) <= 1000
     * strs[i] consists of lowercase English letters.
     * @param strs
     * @return
     */
    // time = O(n^2 * k), space = O(n * k)
    public String splitLoopedString(String[] strs) {
        char maxChar = 'a';
        int n = strs.length;
        for (int i = 0; i < n; i++) { // O(n)
            for (int j = 0; j < strs[i].length(); j++) { // O(k)
                if (strs[i].charAt(j) > maxChar) maxChar = strs[i].charAt(j);
            }
            String rev = reverse(strs[i]); // O(k)
            if (rev.compareTo(strs[i]) > 0) strs[i] = rev;
        }

        String res = "";
        for (int i = 0; i < strs.length; i++) {  // O(n)
            if (strs[i].indexOf(maxChar) == -1) continue; // O(k)
            String[] arr = new String[]{strs[i], reverse(strs[i])};
            for (String s : arr) {  // O(2)
                for (int j = 0; j < s.length(); j++) {  // O(k)
                    if (s.charAt(j) == maxChar) {
                        String t = helper(strs, s, i, j);
                        if (res.compareTo(t) < 0) res = t; // O(k)
                    }
                }
            }
        }
        return res;
    }

    private String helper(String[] strs, String s, int id, int pos) { // O(n)
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(pos));
        int n = strs.length;
        for (int i = id + 1; i < n; i++) sb.append(strs[i]); // O(n)
        for (int i = 0; i < id; i++) sb.append(strs[i]);
        sb.append(s.substring(0, pos));
        return sb.toString();
    }

    private String reverse(String s) { // O(k)
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}