package LC1201_1500;
import java.util.*;
public class LC1257_SmallestCommonRegion {
    /**
     * You are given some lists of regions where the first region of each list includes all other regions in that list.
     *
     * Naturally, if a region x contains another region y then x is bigger than y. Also, by definition, a region x
     * contains itself.
     *
     * Given two regions: region1 and region2, return the smallest region that contains both of them.
     *
     * If you are given regions r1, r2, and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2
     * includes r3.
     *
     * It is guaranteed the smallest region exists.
     *
     * Input:
     * regions = [["Earth","North America","South America"],
     * ["North America","United States","Canada"],
     * ["United States","New York","Boston"],
     * ["Canada","Ontario","Quebec"],
     * ["South America","Brazil"]],
     * region1 = "Quebec",
     * region2 = "New York"
     * Output: "North America"
     *
     * Input: regions = [["Earth", "North America", "South America"],["North America", "United States", "Canada"],
     * ["United States", "New York", "Boston"],["Canada", "Ontario", "Quebec"],["South America", "Brazil"]],
     * region1 = "Canada", region2 = "South America"
     * Output: "Earth"
     *
     * Constraints:
     *
     * 2 <= regions.length <= 10^4
     * 2 <= regions[i].length <= 20
     * 1 <= regions[i][j].length, region1.length, region2.length <= 20
     * region1 != region2
     * regions[i][j], region1, and region2 consist of English letters.
     * @param regions
     * @param region1
     * @param region2
     * @return
     */
    // time = O(n), space = O(n)
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        HashMap<String, String> map = new HashMap<>();
        for (List<String> r : regions) {
            String p = r.get(0);
            for (int i = 1; i < r.size(); i++) {
                map.put(r.get(i), p);
            }
        }

        HashSet<String> set = new HashSet<>();
        String r1 = region1, r2 = region2;
        while (map.containsKey(r1)) {
            set.add(r1);
            r1 = map.get(r1);
        }
        set.add(r1);
        while (!set.contains(r2)) r2 = map.get(r2);
        return r2;
    }
}