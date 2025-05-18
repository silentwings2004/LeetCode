package LC2401_2700;

public class LC2582_PassthePillow {
    /**
     * There are n people standing in a line labeled from 1 to n. The first person in the line is holding a pillow
     * initially. Every second, the person holding the pillow passes it to the next person standing in the line. Once
     * the pillow reaches the end of the line, the direction changes, and people continue passing the pillow in the
     * opposite direction.
     *
     * For example, once the pillow reaches the nth person they pass it to the n - 1th person, then to the n - 2th
     * person and so on.
     * Given the two positive integers n and time, return the index of the person holding the pillow after time seconds.
     *
     * Input: n = 4, time = 5
     * Output: 2
     *
     * Input: n = 3, time = 2
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * 1 <= time <= 1000
     * @param n
     * @param time
     * @return
     */
    // time = O(1), space = O(1)
    public int passThePillow(int n, int time) {
        int p = 2 * (n - 1);
        int r = time % p;
        return r < n ? r + 1 : 2 * n - 1 - r;
    }
}