package LC2700_3000;

public class LC2769_FindtheMaximumAchievableNumber {
    /**
     * You are given two integers, num and t.
     *
     * An integer x is called achievable if it can become equal to num after applying the following operation no more
     * than t times:
     *
     * Increase or decrease x by 1, and simultaneously increase or decrease num by 1.
     * Return the maximum possible achievable number. It can be proven that there exists at least one achievable number.
     *
     * Input: num = 4, t = 1
     * Output: 6
     *
     * Input: num = 3, t = 2
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= num, t <= 50
     * @param num
     * @param t
     * @return
     */
    // time = O(1), space = O(1)
    public int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
}