package LC301_600;
import java.util.*;
public class LC599_MinimumIndexSumofTwoLists {
    /**
     * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants
     * represented by strings.
     *
     * You need to help them find out their common interest with the least list index sum. If there is a choice tie
     * between answers, output all of them with no order requirement. You could assume there always exists an answer.
     *
     * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines",
     * "Hungry Hunter Steakhouse","Shogun"]
     * Output: ["Shogun"]
     *
     * Constraints:
     *
     * 1 <= list1.length, list2.length <= 1000
     * 1 <= list1[i].length, list2[i].length <= 30
     * list1[i] and list2[i] consist of spaces ' ' and English letters.
     * All the stings of list1 are unique.
     * All the stings of list2 are unique.
     * @param list1
     * @param list2
     * @return
     */
    // time = O(m + n), space = O(n)
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
        int sum = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            if (map.containsKey(s)) {
                int t = i + map.get(s);
                if (t < sum) {
                    sum = t;
                    res = new ArrayList<>();
                    res.add(s);
                } else if (t == sum) res.add(s);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}