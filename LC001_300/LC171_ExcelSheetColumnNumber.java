package LC001_300;
import java.util.*;
public class LC171_ExcelSheetColumnNumber {
    /**
     * Given a string columnTitle that represents the column title as appear in an Excel sheet, return its
     * corresponding column number.
     *
     * For example:
     *
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     * ...
     *
     * Input: columnTitle = "FXSHRXW"
     * Output: 2147483647
     *
     * Constraints:
     *
     * 1 <= columnTitle.length <= 7
     * columnTitle consists only of uppercase English letters.
     * columnTitle is in the range ["A", "FXSHRXW"].
     * @param columnTitle
     * @return
     */
    // time = O(n), space = O(1)
    public int titleToNumber(String columnTitle) {
        int t = 0, n = columnTitle.length();
        for (int i = 0; i < n; i++) {
            t = t * 26 + columnTitle.charAt(i) - 'A' + 1;
        }
        return t;
    }
}