package LC2401_2700;

public class LC2468_SplitMessageBasedonLimit {
    /**
     * You are given a string, message, and a positive integer, limit.
     *
     * You must split message into one or more parts based on limit. Each resulting part should have the suffix "<a/b>",
     * where "b" is to be replaced with the total number of parts and "a" is to be replaced with the index of the part,
     * starting from 1 and going up to b. Additionally, the length of each resulting part (including its suffix) should
     * be equal to limit, except for the last part whose length can be at most limit.
     *
     * The resulting parts should be formed such that when their suffixes are removed and they are all concatenated in
     * order, they should be equal to message. Also, the result should contain as few parts as possible.
     *
     * Return the parts message would be split into as an array of strings. If it is impossible to split message as
     * required, return an empty array.
     *
     * Input: message = "this is really a very awesome message", limit = 9
     * Output: ["thi<1/14>","s i<2/14>","s r<3/14>","eal<4/14>","ly <5/14>","a v<6/14>","ery<7/14>"," aw<8/14>",
     * "eso<9/14>","me<10/14>"," m<11/14>","es<12/14>","sa<13/14>","ge<14/14>"]
     *
     * Input: message = "short message", limit = 15
     * Output: ["short mess<1/2>","age<2/2>"]
     *
     * Constraints:
     *
     * 1 <= message.length <= 10^4
     * message consists only of lowercase English letters and ' '.
     * 1 <= limit <= 10^4
     * @param message
     * @param limit
     * @return
     */
    // S1: 递推
    // time = O(n), space = O(n)
    public String[] splitMessage(String message, int limit) {
        int n = message.length();
        for (int i = 1; i <= n; i++) {
            if (get(i, limit) >= n) {
                String[] res = new String[i];
                for (int j = 1, k = 0; j <= i && k < n; j++) {
                    String str = "<" + j + "/" + i + ">";
                    int len = Math.min(n - k, limit - str.length());
                    res[j - 1] = message.substring(k, k + len) + str;
                    k += len;
                }
                return res;
            }
        }
        return new String[0];
    }

    private int get(int k, int limit) {
        int len = String.valueOf(k).length(), s = 0;
        int res = (limit - 3 - len) * k;
        for (int i = 1, t = 9; i < len; i++, t *= 10) {
            res -= i * t;
            s += t;
        }
        res -= (k - s) * len;
        return res;
    }

    // S2: B.S.
    // time = O(nlogn), space = O(n)
    public String[] splitMessage2(String message, int limit) {
        int n = message.length();
        int l = 1, r = n;

        while (l < r) {
            int mid = l + r >> 1;
            if (check(message, mid, limit)) r = mid;
            else l = mid + 1;
        }

        int m = r, t = 0;
        String[] res = new String[m];
        for (int i = 1; i <= m; i++) {
            int tail = 3 + String.valueOf(i).length() + String.valueOf(m).length();
            if (tail >= limit) return new String[0];
            String str = "<" + i + "/" + m + ">";
            res[i - 1] = message.substring(t, Math.min(n, t + limit - tail)) + str;
            t += limit - tail;
        }
        return res;
    }

    private boolean check(String s, int mid, int limit) {
        int n = s.length();
        for (int i = 1; i <= mid; i++) {
            int tail = 3 + String.valueOf(i).length() + String.valueOf(mid).length();
            n -= limit - tail;
        }
        return n <= 0;
    }
}
