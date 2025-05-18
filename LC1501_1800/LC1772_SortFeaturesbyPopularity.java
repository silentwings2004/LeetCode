package LC1501_1800;
import java.util.*;
public class LC1772_SortFeaturesbyPopularity {
    /**
     * You are given a string array features where features[i] is a single word that represents the name of a feature
     * of the latest product you are working on. You have made a survey where users have reported which features they
     * like. You are given a string array responses, where each responses[i] is a string containing space-separated words.
     *
     * The popularity of a feature is the number of responses[i] that contain the feature. You want to sort the features
     * in non-increasing order by their popularity. If two features have the same popularity, order them by their
     * original index in features. Notice that one response could contain the same feature multiple times; this feature
     * is only counted once in its popularity.
     *
     * Return the features in sorted order.
     *
     * Input: features = ["cooler","lock","touch"], responses = ["i like cooler cooler","lock touch cool","locker like
     * touch"]
     * Output: ["touch","cooler","lock"]
     *
     * Input: features = ["a","aa","b","c"], responses = ["a","a aa","a a a a a","b a"]
     * Output: ["a","aa","b","c"]
     *
     * Constraints:
     *
     * 1 <= features.length <= 10^4
     * 1 <= features[i].length <= 10
     * features contains no duplicates.
     * features[i] consists of lowercase letters.
     * 1 <= responses.length <= 10^2
     * 1 <= responses[i].length <= 10^3
     * responses[i] consists of lowercase letters and spaces.
     * responses[i] contains no two consecutive spaces.
     * responses[i] has no leading or trailing spaces.
     * @param features
     * @param responses
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String[] sortFeatures(String[] features, String[] responses) {
        int n = features.length;
        HashMap<String, Integer> map = new HashMap<>();
        int id = 0;
        for (String x : features) map.put(x, id++);
        int[] cnt = new int[n];
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) ids[i] = i;
        for (String r : responses) {
            String[] strs = r.split(" ");
            HashSet<String> set = new HashSet<>();
            for (String str : strs) set.add(str);
            for (String x : set) {
                if (map.containsKey(x)) cnt[map.get(x)]++;
            }
        }
        Arrays.sort(ids, (o1, o2) -> cnt[o1] != cnt[o2] ? cnt[o2] - cnt[o1] : o1 - o2);
        String[] res = new String[n];
        for (int i = 0; i < n; i++) res[i] = features[ids[i]];
        return res;
    }
}