package LC2401_2700;
import java.util.*;
public class LC2408_DesignSQL {
    /**
     *You are given n tables represented with two arrays names and columns, where names[i] is the name of the ith table
     * and columns[i] is the number of columns of the ith table.
     *
     * You should be able to perform the following operations:
     *
     * Insert a row in a specific table. Each row you insert has an id. The id is assigned using an auto-increment
     * method where the id of the first inserted row is 1, and the id of each other row inserted into the same table is
     * the id of the last inserted row (even if it was deleted) plus one.
     * Delete a row from a specific table. Note that deleting a row does not affect the id of the next inserted row.
     * Select a specific cell from any table and return its value.
     * Implement the SQL class:
     *
     * SQL(String[] names, int[] columns) Creates the n tables.
     * void insertRow(String name, String[] row) Adds a row to the table name. It is guaranteed that the table will
     * exist, and the size of the array row is equal to the number of columns in the table.
     * void deleteRow(String name, int rowId) Removes the row rowId from the table name. It is guaranteed that the table
     * and row will exist.
     * String selectCell(String name, int rowId, int columnId) Returns the value of the cell in the row rowId and the
     * column columnId from the table name.
     *
     * Input
     * ["SQL", "insertRow", "selectCell", "insertRow", "deleteRow", "selectCell"]
     * [[["one", "two", "three"], [2, 3, 1]], ["two", ["first", "second", "third"]], ["two", 1, 3], ["two",
     * ["fourth", "fifth", "sixth"]], ["two", 1], ["two", 2, 2]]
     * Output
     * [null, null, "third", null, null, "fifth"]
     *
     * Constraints:
     *
     * n == names.length == columns.length
     * 1 <= n <= 10^4
     * 1 <= names[i].length, row[i].length, name.length <= 20
     * names[i], row[i], and name consist of lowercase English letters.
     * 1 <= columns[i] <= 100
     * All the strings of names are distinct.
     * name exists in the array names.
     * row.length equals the number of columns in the chosen table.
     * rowId and columnId will be valid.
     * At most 250 calls will be made to insertRow and deleteRow.
     * At most 104 calls will be made to selectCell.
     * @param names
     * @param columns
     */
    // time = O(n), space = O(n)
    HashMap<String, HashMap<Integer, List<String>>> name2table;
    HashMap<String, Integer> name2id;
    public LC2408_DesignSQL(List<String> names, List<Integer> columns) {
        name2table = new HashMap<>();
        name2id = new HashMap<>();
        for (String name : names) {
            name2table.put(name, new HashMap<>());
            name2id.put(name, 1);
        }
    }

    public void insertRow(String name, List<String> row) {
        int id = name2id.get(name);
        name2table.get(name).put(id, row);
        name2id.put(name, id + 1);
    }

    public void deleteRow(String name, int rowId) {
        name2table.get(name).remove(rowId);
    }

    public String selectCell(String name, int rowId, int columnId) {
        return name2table.get(name).get(rowId).get(columnId - 1);
    }
}
