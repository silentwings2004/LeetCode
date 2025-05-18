package LC601_900;

public class LC686_RepeatedStringMatch {
    /**
     * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a
     * substring of it. If it is impossible for b to be a substring of a after repeating it, return -1.
     *
     * Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is "abcabc".
     *
     * Input: a = "abcd", b = "cdabcdab"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= a.length <= 10^4
     * 1 <= b.length <= 10^4
     * a and b consist of lower-case English letters.
     * @param a
     * @param b
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        int count = 1;
        String copy = a;
        for (int i = 0; i <= 1 + n / m; i++) {
            if (a.indexOf(b) != -1) return count;
            a += copy;
            count++;
        }
        return -1;
    }

    // S2
    // time = O(n), space = O(n)
    public int repeatedStringMatch2(String a, String b) {
        if (a.contains(b) && a.length() > b.length()) return 1;

        for (int i = 0; i < b.length(); i++) {
            if (!a.contains(String.valueOf(b.charAt(i)))) return -1;
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(a);
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }
        if (sb.toString().contains(b)) return count;
        if (sb.append(a).toString().contains(b)) return count + 1;
        return -1;
    }

    // S3: KMP
    // time = O(m + n), space = O(m + n)
    public int repeatedStringMatch3(String a, String b) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < b.length()) sb.append(a);
        sb.append(a);
        String s = sb.toString();
        int n = s.length(), m = b.length();
        s = "#" + s;
        String p = "#" + b;

        int[] ne = new int[m + 1];
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (p.charAt(i) == p.charAt(j + 1)) j++;
            ne[i] = j;
        }

        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (s.charAt(i) == p.charAt(j + 1)) j++;
            if (j == m) return (i + a.length() - 1) / a.length();
        }
        return -1;
    }
}
/**
 * 枚举下起始位置
 * 需要枚举的起点只有a的字符数量
 * 后面最多再加一段a，即可判断是否存在b => kmp
 */
