package LC3301_3600;

public class LC3522_CalculateScoreAfterPerformingInstructions {
    /**
     * You are given two arrays, instructions and values, both of size n.
     *
     * You need to simulate a process based on the following rules:
     *
     * You start at the first instruction at index i = 0 with an initial score of 0.
     * If instructions[i] is "add":
     * Add values[i] to your score.
     * Move to the next instruction (i + 1).
     * If instructions[i] is "jump":
     * Move to the instruction at index (i + values[i]) without modifying your score.
     * The process ends when you either:
     *
     * Go out of bounds (i.e., i < 0 or i >= n), or
     * Attempt to revisit an instruction that has been previously executed. The revisited instruction is not executed.
     * Return your score at the end of the process.
     *
     * Input: instructions = ["jump","add","add","jump","add","jump"], values = [2,1,3,1,-2,-3]
     * Output: 1
     *
     * Input: instructions = ["jump","add","add"], values = [3,1,1]
     * Output: 0
     *
     * Input: instructions = ["jump"], values = [0]
     * Output: 0
     *
     * Constraints:
     *
     * n == instructions.length == values.length
     * 1 <= n <= 10^5
     * instructions[i] is either "add" or "jump".
     * -10^5 <= values[i] <= 10^5
     * @param instructions
     * @param values
     * @return
     */
    // time = O(n), space = O(n)
    public long calculateScore(String[] instructions, int[] values) {
        int n = instructions.length;
        long res = 0;
        boolean[] st = new boolean[n];
        for (int i = 0; i >= 0 && i < n && !st[i];) {
            st[i] = true;
            if (instructions[i].equals("add")) {
                res += values[i];
                i++;
            } else i += values[i];
        }
        return res;
    }
}