/**
 * Created by Feiyu on 2015/7/8 0008.
 **/
public class SudokuSolver {
    /**
     * Accept，标准的回溯，约束条件3个
     */
    public class Solution {
        private int times = 0;

        public void solveSudoku(char[][] board) {
            short[][] soltion = new short[board.length][board.length];
            int start = -1, last = 0;
            for (int i = 0; i < board.length; i++)
                for (int j = 0; j < board.length; j++)
                    if (board[i][j] != '.')
                        soltion[i][j] = -1;
                    else {
                        start = start == -1 ? i * soltion.length + j : start;
                        last = i * soltion.length + j;
                    }

            int index = start;
            int i, j;
            while (index >= start) {
                i = index / board.length;
                j = index % board.length;
                soltion[i][j]++;
                times++;
                while (soltion[i][j] <= 9 && !isValid(i, j, soltion, board)) {
                    soltion[i][j]++;
                    times++;
                }
                if (soltion[i][j] <= 9) {
                    if (index == last) {
                        setAnswer(soltion, board);
                        return;
                    } else {
                        do {
                            index++;
                        }
                        while (index < last &&
                                soltion[index / board.length][index % board.length] == -1);
                        soltion[index / board.length][index % board.length] = 0;
                    }
                } else
                    do {
                        index--;
                    }
                    while (index >= start &&
                            soltion[index / board.length][index % board.length] == -1);

            }
        }

        private boolean isValid(int i, int j, short[][] solution, char[][] board) {
            for (int j1 = 0; j1 < board.length; j1++)
                if (j1 != j && getRealValue(i, j1, solution, board) == solution[i][j])
                    return false;
            for (int i1 = 0; i1 < board.length; i1++)
                if (i1 != i && getRealValue(i1, j, solution, board) == solution[i][j])
                    return false;
            for (int i1 = (i / 3) * 3; i1 < (i / 3) * 3 + 3; i1++)
                for (int j1 = (j / 3) * 3; j1 < (j / 3) * 3 + 3; j1++) {
                    if (i1 != i && j1 != j
                            && getRealValue(i1, j1, solution, board) == solution[i][j]) {
                        return false;
                    }
                }
            return true;
        }

        private void setAnswer(short[][] soltion, char[][] board) {
            for (int i = 0; i < soltion.length; i++)
                for (int j = 0; j < soltion.length; j++)
                    if (soltion[i][j] != -1)
                        board[i][j] = (char) (soltion[i][j] + '0');
        }

        protected final short getRealValue(int i, int j, short[][] solution, char[][] board) {
            return solution[i][j] == -1 ? (short) (board[i][j] - '0') : solution[i][j];
        }
    }

    static public void main(String... a) {
        Solution solution = new SudokuSolver().new Solution();
        solution.solveSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        });
        System.out.print("Times: " + solution.times + " / " + Math.pow(9, 81));
        boolean x = false;
    }
}
