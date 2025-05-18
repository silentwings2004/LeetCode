package LC3301_3600;
import java.util.*;
public class LC3484_DesignSpreadsheet {
    /**
     * A spreadsheet is a grid with 26 columns (labeled from 'A' to 'Z') and a given number of rows. Each cell in the
     * spreadsheet can hold an integer value between 0 and 10^5.
     *
     * Implement the Spreadsheet class:
     *
     * Spreadsheet(int rows) Initializes a spreadsheet with 26 columns (labeled 'A' to 'Z') and the specified number of
     * rows. All cells are initially set to 0.
     * void setCell(String cell, int value) Sets the value of the specified cell. The cell reference is provided in the
     * format "AX" (e.g., "A1", "B10"), where the letter represents the column (from 'A' to 'Z') and the number
     * represents a 1-indexed row.
     * void resetCell(String cell) Resets the specified cell to 0.
     * int getValue(String formula) Evaluates a formula of the form "=X+Y", where X and Y are either cell references
     * or non-negative integers, and returns the computed sum.
     * Note: If getValue references a cell that has not been explicitly set using setCell, its value is considered 0.
     *
     * Input:
     * ["Spreadsheet", "getValue", "setCell", "getValue", "setCell", "getValue", "resetCell", "getValue"]
     * [[3], ["=5+7"], ["A1", 10], ["=A1+6"], ["B2", 15], ["=A1+B2"], ["A1"], ["=A1+B2"]]
     * Output:
     * [null, 12, null, 16, null, 25, null, 15]
     *
     * 1 <= rows <= 10^3
     * 0 <= value <= 10^5
     * The formula is always in the format "=X+Y", where X and Y are either valid cell references or non-negative
     * integers with values less than or equal to 105.
     * Each cell reference consists of a capital letter from 'A' to 'Z' followed by a row number between 1 and rows.
     * At most 104 calls will be made in total to setCell, resetCell, and getValue.
     * @param rows
     */
    // time = O(1), space = O(n)
    HashMap<String, Integer> map;
    public LC3484_DesignSpreadsheet(int rows) {
        map = new HashMap<>();
    }

    public void setCell(String cell, int value) {
        map.put(cell, value);
    }

    public void resetCell(String cell) {
        map.remove(cell);
    }

    public int getValue(String formula) {
        int res = 0;
        String[] cells = formula.substring(1).split("\\+");
        for (String cell : cells) {
            if (Character.isUpperCase(cell.charAt(0))) {
                res += map.getOrDefault(cell, 0);
            } else res += Integer.parseInt(cell);
        }
        return res;
    }
}
/**
 * 创建一个 key 为字符串，value 为整数的哈希表
 * 直接把字符串当做 key 存进来即可，无需解析
 */