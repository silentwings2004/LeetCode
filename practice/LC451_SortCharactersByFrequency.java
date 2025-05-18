package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SortCharactersByFrequency
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 451. Sort Characters By Frequency
 */
public class LC451_SortCharactersByFrequency {
    /**
     * Given a string, sort it in decreasing order based on the frequency of characters.
     *
     * Example 1:
     *
     * Input:
     * "tree"
     *
     * Output:
     * "eert"
     *
     * Explanation:
     * 'e' appears twice while 'r' and 't' both appear once.
     * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     *
     * Example 2:
     *
     * Input:
     * "cccaaa"
     *
     * Output:
     * "cccaaa"
     *
     * Explanation:
     * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
     * Note that "cacaca" is incorrect, as the same characters must be together.
     *
     * Example 3:
     *
     * Input:
     * "Aabb"
     *
     * Output:
     * "bbAa"
     *
     * Explanation:
     * "bbaA" is also a valid answer, but "Aabb" is incorrect.
     * Note that 'A' and 'a' are treated as two different characters.
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        HashMap<Character, Cell> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) map.get(c).freq++;
            else {
                Cell cell = new Cell(c, 1);
                map.put(c, cell);
            }
        }

        PriorityQueue<Cell> maxHeap = new PriorityQueue<>(map.size(), new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o2.freq - o1.freq;
            }
        });
        for (char c : map.keySet()) {
            maxHeap.offer(map.get(c));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.size();i++) {
            Cell pCell = maxHeap.poll();
            for (int j = 0; j < pCell.freq; j++) {
                sb.append(pCell.c);
            }
        }
        return sb.toString();
    }

    class Cell {
        private char c;
        private int freq;
        public Cell (char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}
