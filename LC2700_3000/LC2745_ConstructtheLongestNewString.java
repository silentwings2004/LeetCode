package LC2700_3000;

public class LC2745_ConstructtheLongestNewString {
    /**
     * You are given three integers x, y, and z.
     *
     * You have x strings equal to "AA", y strings equal to "BB", and z strings equal to "AB". You want to choose some
     * (possibly all or none) of these strings and concactenate them in some order to form a new string. This new string
     * must not contain "AAA" or "BBB" as a substring.
     *
     * Return the maximum possible length of the new string.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: x = 2, y = 5, z = 1
     * Output: 12
     *
     * Input: x = 3, y = 2, z = 2
     * Output: 14
     *
     * Constraints:
     *
     * 1 <= x, y, z <= 50
     * @param x
     * @param y
     * @param z
     * @return
     */
    // time = O(1), space = O(1)
    public int longestString(int x, int y, int z) {
        return (Math.min(x, y) * 2 + z) * 2 + (x == y ? 0 : 2);
    }
}