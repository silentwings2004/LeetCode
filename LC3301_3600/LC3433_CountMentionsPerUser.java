package LC3301_3600;
import java.util.*;
public class LC3433_CountMentionsPerUser {
    /**
     * You are given an integer numberOfUsers representing the total number of users and an array events of size n x 3.
     *
     * Each events[i] can be either of the following two types:
     *
     * Message Event: ["MESSAGE", "timestampi", "mentions_stringi"]
     * This event indicates that a set of users was mentioned in a message at timestampi.
     * The mentions_stringi string can contain one of the following tokens:
     * id<number>: where <number> is an integer in range [0,numberOfUsers - 1]. There can be multiple ids separated
     * by a single whitespace and may contain duplicates. This can mention even the offline users.
     * ALL: mentions all users.
     * HERE: mentions all online users.
     * Offline Event: ["OFFLINE", "timestampi", "idi"]
     * This event indicates that the user idi had become offline at timestampi for 60 time units. The user will
     * automatically be online again at time timestampi + 60.
     * Return an array mentions where mentions[i] represents the number of mentions the user with id i has across all
     * MESSAGE events.
     *
     * All users are initially online, and if a user goes offline or comes back online, their status change is processed
     * before handling any message event that occurs at the same timestamp.
     *
     * Note that a user can be mentioned multiple times in a single message event, and each mention should be counted
     * separately.
     *
     * Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]
     * Output: [2,2]
     *
     * Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]
     * Output: [2,2]
     *
     * Input: numberOfUsers = 2, events = [["OFFLINE","10","0"],["MESSAGE","12","HERE"]]
     * Output: [0,1]
     *
     * Constraints:
     *
     * 1 <= numberOfUsers <= 100
     * 1 <= events.length <= 100
     * events[i].length == 3
     * events[i][0] will be one of MESSAGE or OFFLINE.
     * 1 <= int(events[i][1]) <= 10^5
     * The number of id<number> mentions in any "MESSAGE" event is between 1 and 100.
     * 0 <= <number> <= numberOfUsers - 1
     * It is guaranteed that the user id referenced in the OFFLINE event is online at the time the event occurs.
     * @param numberOfUsers
     * @param events
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        Collections.sort(events, (o1, o2) -> {
            int a = Integer.parseInt(o1.get(1)), b = Integer.parseInt(o2.get(1));
            if (a != b) return a - b;
            return o2.get(0).compareTo(o1.get(0));
        });
        int n = numberOfUsers;
        int[] cnt = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (List<String> e : events) {
            String type = e.get(0);
            int ts = Integer.parseInt(e.get(1));
            String v = e.get(2);
            if (type.equals("MESSAGE")) {
                List<Integer> q = new ArrayList<>();
                for (int k : map.keySet()) {
                    if (map.get(k) <= ts) q.add(k);
                }
                for (int x : q) map.remove(x);
                if (v.equals("ALL")) {
                    for (int i = 0; i < n; i++) cnt[i]++;
                } else if (v.equals("HERE")) {
                    for (int i = 0; i < n; i++) {
                        if (!map.containsKey(i)) cnt[i]++;
                    }
                } else {
                    String[] strs = v.split(" ");
                    for (String s : strs) {
                        int id = Integer.parseInt(s.substring(2));
                        cnt[id]++;
                    }
                }
            } else {
                int id = Integer.parseInt(v);
                map.put(id, ts + 60);
            }
        }
        return cnt;
    }
}