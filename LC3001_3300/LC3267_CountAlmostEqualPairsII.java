package LC3001_3300;
import java.util.*;
public class LC3267_CountAlmostEqualPairsII {
    /**
     * Attention: In this version, the number of operations that can be performed, has been increased to twice.
     * You are given an array nums consisting of positive integers.
     *
     * We call two integers x and y almost equal if both integers can become equal after performing the following
     * operation at most twice:
     *
     * Choose either x or y and swap any two digits within the chosen number.
     * Return the number of indices i and j in nums where i < j such that nums[i] and nums[j] are almost equal.
     *
     * Note that it is allowed for an integer to have leading zeros after performing an operation.
     *
     * Input: nums = [1023,2310,2130,213]
     *
     * Output: 4
     *
     * Input: nums = [1,10,100]
     *
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= nums.length <= 5000
     * 1 <= nums[i] < 10^7
     * @param nums
     * @return
     */
    // S1
    // time = O(n(logU)^5), space = O(n + (logU)^4)
    public int countPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (o1,o2) -> o1.length() - o2.length());
        for (String x : strs) {
            char[] s = x.toCharArray();
            int m = s.length;
            HashSet<Integer> set = new HashSet<>();
            set.add(Integer.parseInt(x));
            for (int i = 0; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    swap(s, i, j);
                    set.add(Integer.parseInt(String.valueOf(s)));
                    for (int a = i + 1; a < m; a++) {
                        for (int b = a + 1; b < m; b++) {
                            swap(s, a, b);
                            set.add(Integer.parseInt(String.valueOf(s)));
                            swap(s, a, b);
                        }
                    }
                    swap(s, i, j);
                }
            }
            for (int v : set) res += map.getOrDefault(v, 0);
            int v = Integer.parseInt(x);
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        return res;
    }

    private void swap(char[] s, int i, int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }

    // S2
    class Solution {
        // time = O(n * (logn)^2), space = O(n)
        HashMap<Integer, HashSet<Integer>> map;
        int res;
        public int countPairs(int[] nums) {
            map = new HashMap<>();
            res = 0;
            int n = nums.length, maxLen = 0;
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = String.valueOf(nums[i]);
                maxLen = Math.max(maxLen, strs[i].length());
            }

            for (int i = 0; i < n; i++) {
                if (strs[i].length() < maxLen) {
                    strs[i] = "0".repeat(maxLen - strs[i].length()) + strs[i];
                }
            }

            for (int i = 0; i < n; i++) work(strs[i], i);
            return res;
        }

        private void work(String str, int idx) {
            char[] s = str.toCharArray();
            int n = s.length;
            HashSet<String> set = new HashSet<>();
            HashSet<Integer> rs = new HashSet<>();
            set.add(str);
            int v = Integer.parseInt(str);
            if (map.containsKey(v)) rs.addAll(map.get(v));
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    swap(s, i, j);
                    v = Integer.parseInt(String.valueOf(s));
                    if (set.add(String.valueOf(s)) && map.containsKey(v)) rs.addAll(map.get(v));
                    swap(s, i, j);
                }
            }
            res += rs.size();
            for (String x : set) {
                v = Integer.parseInt(x);
                map.putIfAbsent(v, new HashSet<>());
                map.get(v).add(idx);
            }
        }

        private void swap(char[] s, int i, int j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}