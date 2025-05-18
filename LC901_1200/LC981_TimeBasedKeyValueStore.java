package LC901_1200;
import java.util.*;
public class LC981_TimeBasedKeyValueStore {
    /**
     * Design a time-based key-value data structure that can store multiple values for the same key at different time
     * stamps and retrieve the key's value at a certain timestamp.
     *
     * Implement the TimeMap class:
     *
     * TimeMap() Initializes the object of the data structure.
     * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time
     * timestamp.
     * String get(String key, int timestamp) Returns a value such that set was called previously, with
     * timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the
     * largest timestamp_prev. If there are no values, it returns "".
     *
     * Input
     * ["TimeMap", "set", "get", "get", "set", "get", "get"]
     * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
     * Output
     * [null, null, "bar", "bar", null, "bar2", "bar2"]
     *
     * Constraints:
     *
     * 1 <= key.length, value.length <= 100
     * key and value consist of lowercase English letters and digits.
     * 1 <= timestamp <= 10^7
     * All the timestamps timestamp of set are strictly increasing.
     * At most 2 * 10^5 calls will be made to set and get.
     */
    HashMap<String, TreeMap<Integer, String>> map;
    public LC981_TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }
    // time = O(logn), space = O(n)
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }
    // time = O(logn), space = O(n)
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        TreeMap<Integer, String> tm = map.get(key);
        Integer fk = tm.floorKey(timestamp);
        return fk == null ? "" : tm.get(fk);
    }
}
