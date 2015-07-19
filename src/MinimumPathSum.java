/**
 * Created by Feiyu on 2015/7/17.
 **/
public class MinimumPathSum {
    /**
     * Accept but > 400ms
     * Sum(i,j) = min{ Sum(i-1,j) , Sum(i,j-1) } + grid(i,j)
     * Sum(1,j)=Sum(1,j-1)+grid(1,j)
     * Sum(i,1)=Sum(i-1,1)+grid(i,1)
     */
    public class Solution {
        public int minPathSum(int[][] grid) {
            int[][] path = new int[grid.length][grid[0].length];
            path[0][0] = grid[0][0];
            for (int i = 1; i < path.length; i++)
                path[i][0] = path[i - 1][0] + grid[i][0];
            for (int i = 1; i < path[0].length; i++)
                path[0][i] = path[0][i - 1] + grid[0][i];

            for (int i = 1; i < path.length; i++) {
                for (int j = 1; j < path[0].length; j++) {
                    path[i][j] = grid[i][j] + Math.min(path[i - 1][j], path[i][j - 1]);
                }
            }
            return path[path.length - 1][path[0].length - 1];
        }
    }

    static public void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
        solution.minPathSum(new int[][]{
                {-3, 5}, {2, 6}
        });
        solution.minPathSum(new int[][]{
                {1, 2, 5}, {3, 2, 1}
        });


    }
}
