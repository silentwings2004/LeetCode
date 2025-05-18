package LC1801_2100;
import java.util.*;
public class LC1805_NumberofDifferentIntegersinaString {
    /**
     * You are given a string word that consists of digits and lowercase English letters.
     *
     * You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become
     * " 123  34 8  34". Notice that you are left with some integers that are separated by at least one space: "123",
     * "34", "8", and "34".
     *
     * Return the number of different integers after performing the replacement operations on word.
     *
     * Two integers are considered different if their decimal representations without any leading zeros are different.
     *
     * Input: word = "a123bc34d8ef34"
     * Output: 3
     *
     * Input: word = "leet1234code234"
     * Output: 2
     *
     * Input: word = "a1b01c001"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= word.length <= 1000
     * word consists of digits and lowercase English letters.
     * @param word
     * @return
     */
    public int numDifferentIntegers(String word) {
        int n = word.length();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (!Character.isDigit(c)) continue;
            int j = i;
            while (j < n && Character.isDigit(word.charAt(j))) j++;
            while (i < j - 1 && word.charAt(i) == '0') i++;
            set.add(word.substring(i, j));
            i = j - 1;
        }
        return set.size();
    }
}