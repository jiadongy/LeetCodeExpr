/**
 * Created by Feiyu on 2015/7/17.
 **/
public class UniquePaths {
    /**
     * Accept ,
     * U(i,j)=U(i-1,j)+U(i,j-1)
     * U(1,j)=U(i,1)=1
     */
    public class Solution {
        public int uniquePaths(int m, int n) {
            int[][] path = new int[m][n];
            path[0][0] = 1;
            for (int i = 1; i < m; i++)
                path[i][0] = 1;
            for (int i = 1; i < n; i++)
                path[0][i] = 1;
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++)
                    path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
            return path[m - 1][n - 1];
        }
    }

    static public void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        solution.uniquePaths(3, 4);

    }
}
