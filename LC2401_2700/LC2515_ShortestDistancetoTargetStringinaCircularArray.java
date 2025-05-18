package LC2401_2700;

public class LC2515_ShortestDistancetoTargetStringinaCircularArray {
    /**
     * You are given a 0-indexed circular string array words and a string target. A circular array means that the
     * array's end connects to the array's beginning.
     *
     * Formally, the next element of words[i] is words[(i + 1) % n] and the previous element of words[i] is
     * words[(i - 1 + n) % n], where n is the length of words.
     * Starting from startIndex, you can move to either the next word or the previous word with 1 step at a time.
     *
     * Return the shortest distance needed to reach the string target. If the string target does not exist in words,
     * return -1.
     *
     * Input: words = ["hello","i","am","leetcode","hello"], target = "hello", startIndex = 1
     * Output: 1
     *
     * Input: words = ["a","b","leetcode"], target = "leetcode", startIndex = 0
     * Output: 1
     *
     * Input: words = ["i","eat","leetcode"], target = "ate", startIndex = 0
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 100
     * words[i] and target consist of only lowercase English letters.
     * 0 <= startIndex < words.length
     * @param words
     * @param target
     * @param startIndex
     * @return
     */
    // time = O(n), space = O(1)
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            String w1 = words[(startIndex + i) % n];
            String w2 = words[(startIndex - i + n) % n];
            if (w1.equals(target) || w2.equals(target)) res = Math.min(res, i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
