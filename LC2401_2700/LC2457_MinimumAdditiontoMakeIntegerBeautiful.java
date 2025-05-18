package LC2401_2700;

public class LC2457_MinimumAdditiontoMakeIntegerBeautiful {
    /**
     * You are given two positive integers n and target.
     *
     * An integer is considered beautiful if the sum of its digits is less than or equal to target.
     *
     * Return the minimum non-negative integer x such that n + x is beautiful. The input will be generated such that it
     * is always possible to make n beautiful.
     *
     * Input: n = 16, target = 6
     * Output: 4
     *
     * Input: n = 467, target = 6
     * Output: 33
     *
     * Input: n = 1, target = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^12
     * 1 <= target <= 150
     * The input will be generated such that it is always possible to make n beautiful.
     * @param n
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public long makeIntegerBeautiful(long n, int target) {
        String s = String.valueOf(n);
        int sum = 0;
        for (char c : s.toCharArray()) sum += c - '0';
        if (sum <= target) return 0;

        int m = s.length();
        for (int i = m - 2; i >= 0; i--) {
            if (s.charAt(i) == '9') continue;
            sum = 0;
            for (int j = 0; j <= i; j++) sum += s.charAt(j) - '0';
            sum++;
            if (sum <= target) {
                char[] chars = s.toCharArray();
                for (int j = i + 1; j < m; j++) chars[j] = '0';
                chars[i]++;
                return Long.parseLong(String.valueOf(chars)) - n;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        for (int i = 0; i < m; i++) sb.append(0);
        return Long.parseLong(sb.toString()) - n;
    }
}
