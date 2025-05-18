package LC1501_1800;

public class LC1678_GoalParserInterpretation {
    /**
     * You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()"
     * and/or "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and
     * "(al)" as the string "al". The interpreted strings are then concatenated in the original order.
     *
     * Given the string command, return the Goal Parser's interpretation of command.
     *
     * Input: command = "G()(al)"
     * Output: "Goal"
     *
     * Input: command = "G()()()()(al)"
     * Output: "Gooooal"
     *
     * Input: command = "(al)G(al)()()G"
     * Output: "alGalooG"
     *
     * Constraints:
     *
     * 1 <= command.length <= 100
     * command consists of "G", "()", and/or "(al)" in some order.
     * @param command
     * @return
     */
    // time = O(n), space = O(n)
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        int n = command.length();
        for (int i = 0; i < n; i++) {
            if (command.charAt(i) == 'G') sb.append('G');
            else {
                int j = i + 1;
                while (j < n && command.charAt(j) != ')') j++;
                if (j == i + 1) sb.append('o');
                else sb.append("al");
                i = j;
            }
        }
        return sb.toString();
    }
}
