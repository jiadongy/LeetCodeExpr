/**
 * Created by Feiyu on 2015/7/17.
 **/
public class UniquePathsII {
    /**
     * Accept
     * U(i,j)=U(i-1,j)+U(i,j-1)
     * U(1,j)=U(i,1)=1
     * 初始化时有障碍的行列都置0，计算是有障碍的点为0
     */
    public class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            final int m = obstacleGrid.length, n = obstacleGrid[0].length;

            int[][] path = new int[m][n];
            path[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
            for (int i = 1; i < m; i++)
                path[i][0] = (obstacleGrid[0][0] == 0 && path[i - 1][0] != 0
                        && obstacleGrid[i][0] == 0) ? 1 : 0;
            for (int i = 1; i < n; i++)
                path[0][i] = (obstacleGrid[0][0] == 0 && path[0][i - 1] != 0
                        && obstacleGrid[0][i] == 0) ? 1 : 0;
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    path[i][j] = obstacleGrid[i][j] == 0 ? (path[i - 1][j] +
                            path[i][j - 1]) : 0;

            return path[m - 1][n - 1];


        }
    }

    static public void main(String[] args) {
        Solution solution = new UniquePathsII().new Solution();
        solution.uniquePathsWithObstacles(new int[][]{
                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
        });
        solution.uniquePathsWithObstacles(new int[][]{
                {0, 1}
        });
        solution.uniquePathsWithObstacles(new int[][]{
                {0, 0}, {1, 1}, {0, 0}
        });
        solution.uniquePathsWithObstacles(new int[][]{
                {0, 1, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}
        });

    }
}
