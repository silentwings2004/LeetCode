package LC1201_1500;

public class LC1476_SubrectangleQueries {
    /**
     * Implement the class SubrectangleQueries which receives a rows x cols rectangle as a matrix of integers in the
     * constructor and supports two methods:
     *
     * 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
     *
     * Updates all values with newValue in the subrectangle whose upper left coordinate is (row1,col1) and bottom right
     * coordinate is (row2,col2).
     * 2. getValue(int row, int col)
     *
     * Returns the current value of the coordinate (row,col) from the rectangle.
     *
     * Input
     * ["SubrectangleQueries","getValue","updateSubrectangle","getValue","getValue","updateSubrectangle","getValue","getValue"]
     * [[[[1,2,1],[4,3,4],[3,2,1],[1,1,1]]],[0,2],[0,0,3,2,5],[0,2],[3,1],[3,0,3,2,10],[3,1],[0,2]]
     * Output
     * [null,1,null,5,5,null,10,5]
     *
     * Input
     * ["SubrectangleQueries","getValue","updateSubrectangle","getValue","getValue","updateSubrectangle","getValue"]
     * [[[[1,1,1],[2,2,2],[3,3,3]]],[0,0],[0,0,2,2,100],[0,0],[2,2],[1,1,2,2,20],[2,2]]
     * Output
     * [null,1,null,100,100,null,20]
     *
     * Constraints:
     *
     * There will be at most 500 operations considering both methods: updateSubrectangle and getValue.
     * 1 <= rows, cols <= 100
     * rows == rectangle.length
     * cols == rectangle[i].length
     * 0 <= row1 <= row2 < rows
     * 0 <= col1 <= col2 < cols
     * 1 <= newValue, rectangle[i][j] <= 10^9
     * 0 <= row < rows
     * 0 <= col < cols
     * @param rectangle
     */
    // time = O(m * n), space O(m * n)
    int[][] r;
    public LC1476_SubrectangleQueries(int[][] rectangle) {
        r = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                r[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return r[row][col];
    }
}
