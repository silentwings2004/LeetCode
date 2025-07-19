package LC1201_1500;
import java.util.*;
public class LC1233_RemoveSubFoldersfromtheFilesystem {
    /**
     * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may
     * return the answer in any order.
     *
     * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
     *
     * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase
     * English letters.
     *
     * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
     *
     * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
     * Output: ["/a","/c/d","/c/f"]
     *
     * Input: folder = ["/a","/a/b/c","/a/b/d"]
     * Output: ["/a"]
     *
     * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
     * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
     *
     * Constraints:
     *
     * 1 <= folder.length <= 4 * 10^4
     * 2 <= folder[i].length <= 100
     * folder[i] contains only lowercase letters and '/'.
     * folder[i] always starts with the character '/'.
     * Each folder name is unique.
     * @param folder
     * @return
     */
    // time = O(nlogn), space = O(1)
    public List<String> removeSubfolders(String[] folder) {
        List<String> res = new ArrayList<>();
        Arrays.sort(folder, (o1, o2) -> o1.compareTo(o2));
        int n = folder.length, j = 0;
        for (int i = 0; i < n; i++) {
            if (folder[i].length() > folder[j].length() && folder[i].indexOf(folder[j]) == 0 && folder[i].charAt(folder[j].length()) == '/') continue;
            res.add(folder[j]);
            j = i;
        }
        res.add(folder[j]);
        return res;
    }

    // S2: Trie
    // time = O(nlogn), space = O(n)
    TrieNode root;
    public List<String> removeSubfolders2(String[] folder) {
        Arrays.sort(folder, (o1, o2) -> o1.length() - o2.length());
        root = new TrieNode();
        List<String> res = new ArrayList<>();
        for (String f : folder) if (insert(f)) res.add(f);
        return res;
    }

    private boolean insert(String s) {
        TrieNode node = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = Character.isLetter(s.charAt(i)) ? s.charAt(i) - 'a' : 26;
            if (node.next[u] == null) node.next[u] = new TrieNode();
            node = node.next[u];
            if (node.isEnd && s.charAt(i + 1) == '/') return false;
        }
        node.isEnd = true;
        return true;
    }

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;
        public TrieNode() {
            this.next = new TrieNode[27];
            this.isEnd = false;
        }
    }

    // S3: 分组循环
    // time = O(n * m), space = O(n * m)
    public List<String> removeSubfolders3(String[] folder) {
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String s : folder) set.add(s);
        for (String s : folder) {
            int n = s.length();
            boolean f = true;
            for (int i = 0; i < n; i++) {
                int j = i;
                while (j < n && s.charAt(j) != '/') j++;
                String t = s.substring(0, j);
                if (!t.equals(s) && set.contains(t)) {
                    f = false;
                    break;
                }
                i = j;
            }
            if (f) res.add(s);
        }
        return res;
    }
}
/**
 * 只要满足前者一定是后者的前缀，但要以'/'结尾 => 给每个字符串最后加个'/'即可直接判断前缀
 * 可以用trie来做
 * 只要直接按照字典序排序
 */