package LC901_1200;

public class LC949_LargestTimeforGivenDigits {
    /**
     * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.
     *
     * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest
     * 24-hour time is 00:00, and the latest is 23:59.
     *
     * Return the latest 24-hour time in "HH:MM" format. If no valid time can be made, return an empty string.
     *
     * Input: arr = [1,2,3,4]
     * Output: "23:41"
     *
     * Input: arr = [5,5,5,5]
     * Output: ""
     *
     * Constraints:
     *
     * arr.length == 4
     * 0 <= arr[i] <= 9
     * @param arr
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public String largestTimeFromDigits(int[] arr) {
        String res = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == j || j == k || k == i) continue;
                    String h = "" + arr[i] + arr[j], m = "" + arr[k] + arr[6 - i - j - k], t = h + ":" + m;
                    if (h.compareTo("24") < 0 && m.compareTo("60") < 0 && res.compareTo(t) < 0) res = t;
                }
            }
        }
        return res;
    }

    // S2: dfs
    // time = O(1), space = O(1)
    int[] a;
    String res;
    boolean[] st;
    public String largestTimeFromDigits2(int[] arr) {
        a = arr;
        res = "";
        st = new boolean[4];
        dfs(0, new StringBuilder());
        if (res.length() == 0) return "";
        return res.substring(0, 2) + ":" + res.substring(2);
    }

    private void dfs(int u, StringBuilder sb) {
        if (u == 4) {
            String t = sb.toString();
            if (res.length() == 0 || comp(res, t)) res = t;
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (st[i]) continue;
            int x = a[i];
            if (u == 0 && x > 2 || u == 2 && x > 5) continue;
            if (u == 1 && sb.charAt(0) == '2' && x > 3) continue;
            st[i] = true;
            sb.append(x);
            dfs(u + 1, sb);
            sb.setLength(sb.length() - 1);
            st[i] = false;
        }
    }

    private boolean comp(String s, String t) {
        int h1 = Integer.parseInt(s.substring(0, 2));
        int h2 = Integer.parseInt(t.substring(0, 2));
        if (h1 != h2) return h1 < h2;
        int m1 = Integer.parseInt(s.substring(2));
        int m2 = Integer.parseInt(t.substring(2));
        return m1 < m2;
    }
}