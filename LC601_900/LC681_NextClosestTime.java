package LC601_900;
import java.util.*;
public class LC681_NextClosestTime {
    /**
     * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There
     * is no limit on how many times a digit can be reused.
     *
     * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34",
     * "12:9" are all invalid.
     *
     * Input: time = "19:34"
     * Output: "19:39"
     *
     * Input: time = "23:59"
     * Output: "22:22"
     *
     * Constraints:
     *
     * time.length == 5
     * time is a valid time in the form "HH:MM".
     * 0 <= HH < 24
     * 0 <= MM < 60
     * @param time
     * @return
     */
    // time = O(4^4), space = O(1)
    String res;
    int mind;
    public String nextClosestTime(String time) {
        res = "";
        mind = 24 * 60 + 1;
        HashSet<Integer> set = new HashSet<>();
        int n = time.length();
        for (int i = 0; i < n; i++) {
            char c = time.charAt(i);
            if (Character.isDigit(c)) set.add(c - '0');
        }

        List<Integer> xs = new ArrayList<>(set);
        int t = convert(time);
        dfs(xs, 0, new StringBuilder(), t);
        return res;
    }

    private void dfs(List<Integer> xs, int u, StringBuilder sb, int t) {
        if (u == 4) {
            String s = sb.toString();
            s = s.substring(0, 2) + ":" + s.substring(2);
            int time = convert(s);
            if (time == -1) return;
            if (time <= t) time += 24 * 60;
            if (time - t < mind) {
                mind = time - t;
                res = s;
            }
            return;
        }

        int len = sb.length();
        for (int i = 0; i < xs.size(); i++) {
            sb.append(xs.get(i));
            dfs(xs, u + 1, sb, t);
            sb.setLength(len);
        }
    }

    private int convert(String time) {
        int h = Integer.parseInt(time.substring(0, 2)), m = Integer.parseInt(time.substring(3));
        if (h > 23 || m > 59) return -1;
        return h * 60 + m;
    }
}