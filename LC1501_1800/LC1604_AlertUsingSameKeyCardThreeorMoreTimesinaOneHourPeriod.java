package LC1501_1800;
import java.util.*;
public class LC1604_AlertUsingSameKeyCardThreeorMoreTimesinaOneHourPeriod {
    /**
     * LeetCode company workers use key-cards to unlock office doors. Each time a worker uses their key-card, the
     * security system saves the worker's name and the time when it was used. The system emits an alert if any worker
     * uses the key-card three or more times in a one-hour period.
     *
     * You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds to a person's
     * name and the time when their key-card was used in a single day.
     *
     * Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
     *
     * Return a list of unique worker names who received an alert for frequent keycard use. Sort the names in ascending
     * order alphabetically.
     *
     * Notice that "10:00" - "11:00" is considered to be within a one-hour period, while "22:51" - "23:52" is not
     * considered to be within a one-hour period.
     *
     * Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00",
     * "09:00","11:00","13:00","15:00"]
     * Output: ["daniel"]
     *
     * Input: keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00",
     * "21:20","21:30","23:00"]
     * Output: ["bob"]
     *
     * Constraints:
     *
     * 1 <= keyName.length, keyTime.length <= 10^5
     * keyName.length == keyTime.length
     * keyTime[i] is in the format "HH:MM".
     * [keyName[i], keyTime[i]] is unique.
     * 1 <= keyName[i].length <= 10
     * keyName[i] contains only lowercase English letters.
     * @param keyName
     * @param keyTime
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> res = new ArrayList<>();
        HashMap<String, List<Integer>> map = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(keyName[i], new ArrayList<>());
            String[] strs = keyTime[i].split(":");
            int time = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
            map.get(keyName[i]).add(time);
        }

        for (String p : map.keySet()) {
            List<Integer> t = map.get(p);
            Collections.sort(t);
            if (check(t)) res.add(p);
        }
        Collections.sort(res);
        return res;
    }

    private boolean check(List<Integer> t) {
        int n = t.size();
        for (int i = 0; i + 2 < n; i++) {
            int a = t.get(i), b = t.get(i + 2);
            if (b - a <= 60) return true;
        }
        return false;
    }
}