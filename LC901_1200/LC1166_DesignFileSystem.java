package LC901_1200;
import java.util.*;
public class LC1166_DesignFileSystem {
    /**
     * You are asked to design a file system that allows you to create new paths and associate them with different values.
     *
     * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English
     * letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
     *
     * Implement the FileSystem class:
     *
     * bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns
     * true. Returns false if the path already exists or its parent path doesn't exist.
     * int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
     *
     * Input:
     * ["FileSystem","createPath","get"]
     * [[],["/a",1],["/a"]]
     * Output:
     * [null,true,1]
     *
     * Constraints:
     *
     * The number of calls to the two functions is less than or equal to 10^4 in total.
     * 2 <= path.length <= 100
     * 1 <= value <= 10^9
     */
    // time = O(n), space = O(n)
    Node root;
    public LC1166_DesignFileSystem() {
        root = new Node(0);
    }

    public boolean createPath(String path, int value) {
        Node node = root;
        String[] strs = path.split("/");
        int n = strs.length;
        for (int i = 0; i < n - 1; i++) {
            if (strs[i].length() == 0) continue;
            if (!node.map.containsKey(strs[i])) return false;
            node = node.map.get(strs[i]);
        }
        if (node.map.containsKey(strs[n - 1])) return false;
        node.map.put(strs[n - 1], new Node(value));
        return true;
    }

    public int get(String path) {
        Node node = root;
        String[] strs = path.split("/");
        for (String s : strs) {
            if (s.length() == 0) continue;
            if (!node.map.containsKey(s)) return -1;
            node = node.map.get(s);
        }
        return node.val;
    }

    private class Node {
        private HashMap<String, Node> map;
        private int val;
        public Node(int val) {
            this.map = new HashMap<>();
            this.val = val;
        }
    }
}
