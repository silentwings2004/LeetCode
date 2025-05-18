package LC601_900;
import java.util.*;
public class LC635_DesignLogStorageSystem {
    /**
     * You are given several logs, where each log contains a unique ID and timestamp. Timestamp is a string that has
     * the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are
     * zero-padded decimal numbers.
     *
     * Implement the LogSystem class:
     *
     * LogSystem() Initializes the LogSystem object.
     * void put(int id, string timestamp) Stores the given log (id, timestamp) in your storage system.
     * int[] retrieve(string start, string end, string granularity) Returns the IDs of the logs whose timestamps are
     * within the range from start to end inclusive. start and end all have the same format as timestamp, and
     * granularity means how precise the range should be (i.e. to the exact Day, Minute, etc.). For example,
     * start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", and granularity = "Day" means that we need to find
     * the logs within the inclusive range from Jan. 1st 2017 to Jan. 2nd 2017, and the Hour, Minute, and Second for
     * each log entry can be ignored.
     *
     * Input
     * ["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
     * [[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"], ["2016:01:01:01:01:01",
     * "2017:01:01:23:00:00", "Year"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
     * Output
     * [null, null, null, null, [3, 2, 1], [2, 1]]
     *
     * Constraints:
     *
     * 1 <= id <= 500
     * 2000 <= Year <= 2017
     * 1 <= Month <= 12
     * 1 <= Day <= 31
     * 0 <= Hour <= 23
     * 0 <= Minute, Second <= 59
     * granularity is one of the values ["Year", "Month", "Day", "Hour", "Minute", "Second"].
     * At most 500 calls will be made to put and retrieve.
     */
    // S1
    HashMap<String, List<Integer>> map;
    String[] strs = new String[]{"Year", "Month", "Day", "Hour", "Minute", "Second"};
    public LC635_DesignLogStorageSystem() {
        map = new HashMap<>();
    }
    // time = O(1), space = O(n)
    public void put(int id, String timestamp) {
        map.putIfAbsent(timestamp, new ArrayList<>());
        map.get(timestamp).add(id);
    }
    // time = O(n), space = O(n）
    public List<Integer> retrieve(String start, String end, String granularity) {
        int op = -1;
        for (int i = 0; i < 6; i++) {
            if (strs[i].equals(granularity)) {
                op = i;
                break;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (String key : map.keySet()) {
            if (helper(start, end, key, op)) set.addAll(map.get(key));
        }
        return new ArrayList<>(set);
    }

    private boolean helper(String s, String t, String x, int op) {
        String[] strs1 = s.split(":");
        String[] strs2 = t.split(":");
        String[] strs3 = x.split(":");

        for (int i = 0; i <= op; i++) {
            int a = Integer.parseInt(strs1[i]);
            int b = Integer.parseInt(strs3[i]);
            if (a == b) continue;
            if (a > b) return false;
            else break;
        }

        for (int i = 0; i <= op; i++) {
            int a = Integer.parseInt(strs2[i]);
            int b = Integer.parseInt(strs3[i]);
            if (a == b) continue;
            return a > b;
        }
        return true;
    }

    // S2
    class LogSystem {
        List<Log> logs;
        int[] pos = new int[]{4, 7, 10, 13, 16, 19};
        HashMap<String, Integer> map;
        public LogSystem() {
            logs = new ArrayList<>();
            map = new HashMap<>();
            map.put("Year", 0);
            map.put("Month", 1);
            map.put("Day", 2);
            map.put("Hour", 3);
            map.put("Minute", 4);
            map.put("Second", 5);
        }
        // time = O(1), space = O(n)
        public void put(int id, String timestamp) {
            logs.add(new Log(id, timestamp));
        }
        // time = O(n), space = O(n)
        public List<Integer> retrieve(String start, String end, String granularity) {
            HashSet<Integer> set = new HashSet<>();
            int idx = pos[map.get(granularity)];
            for (Log log : logs) {
                String a = start.substring(0, idx);
                String b = end.substring(0, idx);
                String c = log.ts.substring(0, idx);
                if (c.compareTo(a) >= 0 && c.compareTo(b) <= 0) set.add(log.id);
            }
            return new ArrayList<>(set);
        }

        private class Log {
            private int id;
            private String ts;
            public Log(int id, String ts) {
                this.id = id;
                this.ts = ts;
            }
        }
    }
}