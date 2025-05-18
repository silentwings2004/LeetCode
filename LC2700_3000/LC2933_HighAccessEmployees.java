package LC2700_3000;
import java.util.*;
public class LC2933_HighAccessEmployees {
    /**
     * You are given a 2D 0-indexed array of strings, access_times, with size n. For each i where 0 <= i <= n - 1,
     * access_times[i][0] represents the name of an employee, and access_times[i][1] represents the access time of that
     * employee. All entries in access_times are within the same day.
     *
     * The access time is represented as four digits using a 24-hour time format, for example, "0800" or "2250".
     *
     * An employee is said to be high-access if he has accessed the system three or more times within a one-hour period.
     *
     * Times with exactly one hour of difference are not considered part of the same one-hour period. For example,
     * "0815" and "0915" are not part of the same one-hour period.
     *
     * Access times at the start and end of the day are not counted within the same one-hour period. For example,
     * "0005" and "2350" are not part of the same one-hour period.
     *
     * Return a list that contains the names of high-access employees with any order you want.
     *
     * Input: access_times = [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]
     * Output: ["a"]
     *
     * Input: access_times = [["d","0002"],["c","0808"],["c","0829"],["e","0215"],["d","1508"],["d","1444"],["d","1410"],
     * ["c","0809"]]
     * Output: ["c","d"]
     *
     * Input: access_times = [["cd","1025"],["ab","1025"],["cd","1046"],["cd","1055"],["ab","1124"],["ab","1120"]]
     * Output: ["ab","cd"]
     *
     * Constraints:
     *
     * 1 <= access_times.length <= 100
     * access_times[i].length == 2
     * 1 <= access_times[i][0].length <= 10
     * access_times[i][0] consists only of English small letters.
     * access_times[i][1].length == 4
     * access_times[i][1] is in 24-hour time format.
     * access_times[i][1] consists only of '0' to '9'.
     * @param access_times
     * @return
     */
    // time = O(l * n + nlogn), space = O(n)
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        List<String> res = new ArrayList<>();
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (List<String> t : access_times) {
            String id = t.get(0), time = t.get(1);
            map.putIfAbsent(id, new ArrayList<>());
            map.get(id).add(convert(time));
        }

        for (String key : map.keySet()) {
            List<Integer> v = map.get(key);
            if (v.size() < 3) continue;
            Collections.sort(v);
            int m = v.size();
            for (int i = 0; i + 2 < m; i++) {
               if (v.get(i + 2) - v.get(i) < 60) {
                   res.add(key);
                   break;
               }
            }
        }
        return res;
    }

    private int convert(String s) {
        int h = Integer.parseInt(s.substring(0, 2)), m = Integer.parseInt(s.substring(2));
        return h * 60 + m;
    }
}
