package LC3901_4200;

public class LC4026_MaximumGapBetweenStations {
    /**
     * You are given two strings skill and station of lengths n and m, respectively.
     *
     * skill[i] represents the skill of worker i, and station[j] represents the skill supported by station j.
     *
     * You must assign every worker to a distinct station. Let ji be the index of the station assigned to worker i. A
     * valid assignment must satisfy:
     *
     * station[ji] == skill[i] for every 0 <= i < n.
     * The assigned station indices must be strictly increasing in worker order, meaning j0 < j1 < ... < jn - 1.
     * The gap of an assignment is the maximum difference between the station indices assigned to two consecutive
     * workers. In other words, it is max(ji - ji - 1) over all 1 <= i < n.
     *
     * If there is only one worker, the gap is 0.
     *
     * Return the maximum possible gap among all valid assignments. It is guaranteed that at least one valid assignment
     * exists.
     *
     * Input: skill = "aa", station = "aaaa"
     * Output: 3
     *
     * Input: skill = "xyz", station = "xyzz"
     * Output: 2
     *
     * Input: skill = "cbc", station = "cbcdbc"
     * Output: 4
     *
     * Constraints:
     *
     * skill.length == n
     * station.length == m
     * 1 <= n <= m <= 10^5
     * skill and station consist of lowercase English letters.
     * It is guaranteed that a valid assignment exists for every worker.
     * @param skill
     * @param station
     * @return
     */
    // time = O(n), space = O(n)
    public int maximumGap(String skill, String station) {
        int n = skill.length(), m = station.length();
        int[] suf = new int[n];
        for (int i = n - 1, j = m - 1; i >= 0; i--, j--) {
            while (station.charAt(j) != skill.charAt(i)) j--;
            suf[i] = j;
        }

        int res = 0;
        for (int i = 0, j = 0; i < n - 1; i++, j++) {
            while (station.charAt(j) != skill.charAt(i)) j++;
            res = Math.max(res, suf[i + 1] - j);
        }
        return res;
    }
}
/**
 * 枚举 i：在哪里产生了最大的差值
 * j1 - j0 => j1 尽量大，j0 尽量小
 * 寻找一个最短的前缀和后缀，之间的距离就是最大差值
 * 最短的前缀，包含 s 的前 i 个字母
 * 最短的后缀，包含 s 的后 i 个字母
 * 子序列问题 => 双指针
 */