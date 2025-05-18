package LC2401_2700;
import java.util.*;
public class LC2456_MostPopularVideoCreator {
    /**
     * You are given two string arrays creators and ids, and an integer array views, all of length n. The ith video on
     * a platform was created by creator[i], has an id of ids[i], and has views[i] views.
     *
     * The popularity of a creator is the sum of the number of views on all of the creator's videos. Find the creator
     * with the highest popularity and the id of their most viewed video.
     *
     * If multiple creators have the highest popularity, find all of them.
     * If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
     * Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the highest
     * popularity and idi is the id of their most popular video. The answer can be returned in any order.
     *
     * Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
     * Output: [["alice","one"],["bob","two"]]
     *
     * Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
     * Output: [["alice","b"]]
     *
     * Constraints:
     *
     * n == creators.length == ids.length == views.length
     * 1 <= n <= 10^5
     * 1 <= creators[i].length, ids[i].length <= 5
     * creators[i] and ids[i] consist only of lowercase English letters.
     * 0 <= views[i] <= 10^5
     * @param creators
     * @param ids
     * @param views
     * @return
     */
    // time = O(n), space = O(n)
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        HashMap<String, Long> cnt = new HashMap<>();
        HashMap<String, List<Integer>> map = new HashMap<>();
        int n = creators.length;
        for (int i = 0; i < n; i++) { // O(n)
            cnt.put(creators[i], cnt.getOrDefault(creators[i], 0L) + views[i]);
            map.putIfAbsent(creators[i], new ArrayList<>());
            map.get(creators[i]).add(i);
        }

        long max = 0;
        for (long v : cnt.values()) max = Math.max(max, v);

        List<List<String>> res = new ArrayList<>();
        for (String key : cnt.keySet()) {
            long val = cnt.get(key);
            if (val == max) {
                long highest = -1;
                String id = "";
                for (int i : map.get(key)) {
                    if (views[i] > highest) {
                        highest = views[i];
                        id = ids[i];
                    } else if (views[i] == highest && ids[i].compareTo(id) < 0) id = ids[i];
                }
                res.add(Arrays.asList(key, id));
            }
        }
        return res;
    }
}
