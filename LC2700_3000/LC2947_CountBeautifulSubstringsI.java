package LC2700_3000;

public class LC2947_CountBeautifulSubstringsI {
    /**
     * You are given a string s and a positive integer k.
     *
     * Let vowels and consonants be the number of vowels and consonants in a string.
     *
     * A string is beautiful if:
     *
     * vowels == consonants.
     * (vowels * consonants) % k == 0, in other terms the multiplication of vowels and consonants is divisible by k.
     * Return the number of non-empty beautiful substrings in the given string s.
     *
     * A substring is a contiguous sequence of characters in a string.
     *
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     *
     * Consonant letters in English are every letter except vowels.
     *
     * Input: s = "baeyh", k = 2
     * Output: 2
     *
     * Input: s = "abba", k = 1
     * Output: 3
     *
     * Input: s = "bcdf", k = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * 1 <= k <= 1000
     * s consists of only English lowercase letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n^2), space = O(1)
    public int beautifulSubstrings(String s, int k) {
        int n = s.length(), res = 0;
        String vowel = "aeiou";
        for (int i = 0; i < n; i++) {
            int a = 0, b = 0;
            if (vowel.indexOf(s.charAt(i)) != -1) a++;
            else b++;
            for (int j = i + 1; j < n; j++) {
                if (vowel.indexOf(s.charAt(j)) != -1) a++;
                else b++;
                if (a == b && a * b % k == 0) res++;
            }
        }
        return res;
    }
}