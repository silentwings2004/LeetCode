package LC2401_2700;

public class LC2514_CountAnagrams {
    /**
     * You are given a string s containing one or more words. Every consecutive pair of words is separated by a single
     * space ' '.
     *
     * A string t is an anagram of string s if the ith word of t is a permutation of the ith word of s.
     *
     * For example, "acb dfe" is an anagram of "abc def", but "def cab" and "adc bef" are not.
     * Return the number of distinct anagrams of s. Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: s = "too hot"
     * Output: 18
     *
     * Input: s = "aa"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters and spaces ' '.
     * There is single space between consecutive words.
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    long mod = (long)(1e9 + 7);
    public int countAnagrams(String s) {
        long[] p = new long[N];
        p[0] = 1;
        for (int i = 1; i < N; i++) p[i] = p[i - 1] * i % mod;

        long res = 1;
        String[] strs = s.split(" ");
        for (String str : strs) {
            int n = str.length();
            long sum = p[n], factor = 1;
            int[] cnt = new int[26];
            for (char c : str.toCharArray()) cnt[c - 'a']++;
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > 0) factor = (factor * p[cnt[i]]) % mod;
            }
            sum = sum * qmi(factor, mod - 2, mod) % mod;
            res = res * sum % mod;
        }
        return (int) res;
    }

    private long qmi(long a, long k, long p) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % p;
            a = a * a % p;
            k >>= 1;
        }
        return res;
    }
}