/**
 * Created by Feiyu on 2015/7/11.
 **/
public class EditDistance {
    /**
     * Accept,数组为n+1 * m+1
     */
    public class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            int[][] solution = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++)
                solution[i][0] = i;
            for (int j = 0; j <= n; j++)
                solution[0][j] = j;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    solution[i][j] =
                            Math.min(Math.min(solution[i - 1][j] + 1,
                                            solution[i][j - 1] + 1),
                                    solution[i - 1][j - 1] + (
                                            word1.charAt(i - 1) == word2.charAt(j - 1)
                                                    ? 0 : 1));
                }
            }
            return solution[m][n];
        }
    }

    static public void main(String... ar) {
        Solution solution = new EditDistance().new Solution();
        solution.minDistance("polynomial", "exponential");
    }
}
