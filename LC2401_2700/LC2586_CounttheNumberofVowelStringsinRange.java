package LC2401_2700;

public class LC2586_CounttheNumberofVowelStringsinRange {
    /**
     * You are given a 0-indexed array of string words and two integers left and right.
     *
     * A string is called a vowel string if it starts with a vowel character and ends with a vowel character where vowel
     * characters are 'a', 'e', 'i', 'o', and 'u'.
     *
     * Return the number of vowel strings words[i] where i belongs to the inclusive range [left, right].
     *
     * Input: words = ["are","amy","u"], left = 0, right = 2
     * Output: 2
     *
     * Input: words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 10
     * words[i] consists of only lowercase English letters.
     * 0 <= left <= right < words.length
     * @param words
     * @param left
     * @param right
     * @return
     */
    // time = O(n), space = O(1)
    public int vowelStrings(String[] words, int left, int right) {
        String vowel = "aeiou";
        int res = 0;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            int m = word.length();
            char a = word.charAt(0), b = word.charAt(m - 1);
            if (vowel.indexOf(a) != -1 && vowel.indexOf(b) != -1) res++;
        }
        return res;
    }
}