package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: JewelsandStones
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 771. Jewels and Stones
 */
public class LC771_JewelsandStones {
    /**
     * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
     * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also
     * jewels.
     *
     * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
     * so "a" is considered a different type of stone from "A".
     *
     * Example 1:
     *
     * Input: J = "aA", S = "aAAbbbb"
     * Output: 3
     *
     * Example 2:
     *
     * Input: J = "z", S = "ZZ"
     * Output: 0
     * Note:
     *
     * S and J will consist of letters and have length at most 50.
     * The characters in J are distinct.
     * @param J
     * @param S
     * @return
     */
    // S1: two pointers
    // time = O(m * n), space = O(1)
    public int numJewelsInStones(String J, String S) {
        // corner case
        if (J == null || J.length() == 0 || S == null || S.length() == 0) return 0;

        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            for (int j = 0; j < S.length(); j++) {
                if (J.charAt(i) == S.charAt(j)) count++;
            }
        }
        return count;
    }

    // S2: time = O(m + n), space = O(m)
    public int numJewelsInStones2(String J, String S) {
        // corner case
        if (J == null || J.length() == 0 || S == null || S.length() == 0) return 0;

        int count = 0;
        HashSet<Character> set = new HashSet<>();
        for (char ch : J.toCharArray()) set.add(ch);
        for (char c : S.toCharArray()) {
            if (set.contains(c)) count++;
        }
        return count;
    }
}
