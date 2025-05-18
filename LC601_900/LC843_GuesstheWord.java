package LC601_900;
import java.util.*;
public class LC843_GuesstheWord {
    /**
     * You are given an array of unique strings words where words[i] is six letters long. One word of words was chosen
     * as a secret word.
     *
     * You are also given the helper object Master. You may call Master.guess(word) where word is a six-letter-long
     * string, and it must be from words. Master.guess(word) returns:
     *
     * -1 if word is not from words, or
     * an integer representing the number of exact matches (value and position) of your guess to the secret word.
     * There is a parameter allowedGuesses for each test case where allowedGuesses is the maximum number of times you
     * can call Master.guess(word).
     *
     * For each test case, you should call Master.guess with the secret word without exceeding the maximum number of
     * allowed guesses. You will get:
     *
     * "Either you took too many guesses, or you did not find the secret word." if you called Master.guess more than
     * allowedGuesses times or if you did not call Master.guess with the secret word, or
     * "You guessed the secret word correctly." if you called Master.guess with the secret word with the number of
     * calls to Master.guess less than or equal to allowedGuesses.
     * The test cases are generated such that you can guess the secret word with a reasonable strategy (other than
     * using the bruteforce method).
     *
     * Input: secret = "acckzz", words = ["acckzz","ccbazz","eiowzz","abcczz"], allowedGuesses = 10
     * Output: You guessed the secret word correctly.
     *
     * Input: secret = "hamada", words = ["hamada","khaled"], allowedGuesses = 10
     * Output: You guessed the secret word correctly.
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * words[i].length == 6
     * words[i] consist of lowercase English letters.
     * All the strings of wordlist are unique.
     * secret exists in words.
     * 10 <= allowedGuesses <= 30
     * @param wordlist
     * @param master
     */
    // time = O(n^2*logn), space = O(n^2)
    int n;
    int[][] f;
    boolean[] st;
    public void findSecretWord(String[] wordlist, Master master) {
        n = wordlist.length;
        f = new int[n][n];
        st = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 6; k++) {
                    if (wordlist[i].charAt(k) == wordlist[j].charAt(k)) f[i][j]++;
                }
            }
        }

        for (int i = 0; i < 30; i++) {
            int k = -1, w = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (st[j]) continue;
                int t = 0;
                for (int u = 0; u <= 6; u++) {
                    t = Math.max(t, get(j, u));
                }
                if (w > t) {
                    w = t;
                    k = j;
                }
            }
            int res = master.guess(wordlist[k]);
            if (res == 6) break;
            st[k] = true;
            for (int j = 0; j < n; j++) {
                if (f[k][j] != res) st[j] = true;
            }
        }
    }

    private int get(int j, int u) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!st[i] && f[j][i] == u) res++;
        }
        return res;
    }

    // S2: Random
    class Solution {
        Random random;
        public void findSecretWord(String[] words, Master master) {
            random = new Random();
            for (int i = 0; i < 30; i++) {
                int r = (random.nextInt(100) + 1) % words.length;
                String x = words[r];
                int count = master.guess(x);
                if (count == 6) return;

                List<String> q = new ArrayList<>();
                for (String s : words) {
                    if (s.equals(x)) continue;
                    if (check(s, x) == count) q.add(s);
                }
                words = q.toArray(new String[q.size()]);
            }
        }

        private int check(String s, String t) {
            int res = 0;
            for (int i = 0; i < 6; i++) {
                if (s.charAt(i) == t.charAt(i)) res++;
            }
            return res;
        }
    }

    interface Master {
        public int guess(String word) {}
    }

    /**
     * // This is the Master's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface Master {
     *     public int guess(String word) {}
     * }
     */
}
/**
 * 直觉上选最坏情况下最好的来猜
 * 剩余的可以选择的数量越少越好
 * 匹配的字母数量
 */