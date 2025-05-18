package LC001_300;
import java.util.*;
public class LC187_RepeatedDNASequences {
    /**
     * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
     *
     * For example, "ACGAATTCCG" is a DNA sequence.
     * When studying DNA, it is useful to identify repeated sequences within the DNA.
     *
     * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur
     * more than once in a DNA molecule. You may return the answer in any order.
     *
     * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * Output: ["AAAAACCCCC","CCCCCAAAAA"]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either 'A', 'C', 'G', or 'T'.
     * @param s
     * @return
     */
    // S1: sliding window
    // time = O(n), space = O(n)
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i + 10 <= n; i++) {
            String sub = s.substring(i, i + 10);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
        }

        List<String> res = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) > 1) res.add(key);
        }
        return res;
    }

    // S2: string hash
    final int P = 131;
    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        long[] p = new long[n + 1], h = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + s.charAt(i - 1);
        }

        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 1; i + 9 <= n; i++) {
            int l = i, r = i + 9;
            long hash = h[r] - h[l - 1] * p[r - l + 1];
            if (map.containsKey(hash) && map.get(hash) == 1) {
                res.add(s.substring(i - 1, i + 9));
            }
            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }
        return res;
    }

    // S3： bit mask
    // time = O(n), space = O(n)
    public List<String> findRepeatedDnaSequences3(String s) {
        List<String> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0) return res;

        int n = s.length();
        int key = 0;

        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = convert(s.charAt(i));
            key = ((key << 2) & 0xfffff) | val;
            if (i < 9) continue;
            Boolean shouldAdd = map.get(key);
            if (shouldAdd == null) map.put(key, false);
            else if (!shouldAdd) {
                res.add(s.substring(i - 9, i + 1));
                map.put(key, true);
            }
        }
        return res;
    }

    private int convert(char c) {
        switch(c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: return -1;
        }
    }
}