package LC601_900;

public class LC771_JewelsandStones {
    /**
     * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones
     * you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have
     * are also jewels.
     *
     * Letters are case sensitive, so "a" is considered a different type of stone from "A".
     *
     * Input: jewels = "aA", stones = "aAAbbbb"
     * Output: 3
     *
     * Input: jewels = "z", stones = "ZZ"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= jewels.length, stones.length <= 50
     * jewels and stones consist of only English letters.
     * All the characters of jewels are unique.
     * @param jewels
     * @param stones
     * @return
     */
    // time = O(n), space = O(1)
    public int numJewelsInStones(String jewels, String stones) {
        int n = stones.length(), res = 0;
        for (int i = 0; i < n; i++) {
            if (jewels.indexOf(stones.charAt(i)) != -1) res++;
        }
        return res;
    }
}