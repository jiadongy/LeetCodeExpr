/**
 * Created by Feiyu on 2015/7/17.
 **/
public class DungeonGame {
    /**
     * WA : min求了所有path上的最小值，而不是最终选定的path中的最小值
     * Input: [[0,5],[-2,-3]]
     * Output: 3
     * Expected: 1
     */
    public class Solution1 {
        public int calculateMinimumHP(int[][] dungeon) {
            int min = 0;
            int[][] path = new int[dungeon.length + 1][dungeon[0].length + 1];
            for (int i = 0; i < path.length; i++)
                path[i][0] = Integer.MIN_VALUE;
            for (int i = 0; i < path[0].length; i++)
                path[0][i] = Integer.MIN_VALUE;
            path[0][1] = path[1][0] = 0;

            for (int i = 1; i < path.length; i++) {
                for (int j = 1; j < path[0].length; j++) {
                    path[i][j] = dungeon[i - 1][j - 1] + Math.max(path[i - 1][j], path[i][j - 1]);
                    min = Math.min(min, path[i][j]);
                }
            }
            return -min + 1;
        }
    }

    /**
     * WA : 题目理解错了。只要能到终点，生命值最小即可，不是最后的生命值最大。
     * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
     */
    public class Solution2 {
        public int calculateMinimumHP(int[][] dungeon) {
            int[][] path = new int[dungeon.length + 1][dungeon[0].length + 1];
            char[][] pathMap = new char[dungeon.length][dungeon[0].length];

            for (int i = 0; i < path.length; i++)
                path[i][0] = Integer.MIN_VALUE;
            for (int i = 0; i < path[0].length; i++)
                path[0][i] = Integer.MIN_VALUE;
            path[0][1] = path[1][0] = 0;

            for (int i = 1; i < path.length; i++) {
                for (int j = 1; j < path[0].length; j++) {
                    if (path[i - 1][j] > path[i][j - 1]) {
                        path[i][j] = path[i - 1][j] + dungeon[i - 1][j - 1];
                        pathMap[i - 1][j - 1] = 'U';
                    } else if (path[i - 1][j] < path[i][j - 1]) {
                        path[i][j] = path[i][j - 1] + dungeon[i - 1][j - 1];
                        pathMap[i - 1][j - 1] = 'L';
                    } else {
                        path[i][j] = path[i][j - 1] + dungeon[i - 1][j - 1];
                        pathMap[i - 1][j - 1] = 'B';
                    }
                }
            }
            int i = dungeon.length, j = dungeon[0].length;
            int min = path[i][j];
            while (!(i == 1 && j == 1)) {
                switch (pathMap[i - 1][j - 1]) {
                    case 'U':
                        min = Math.min(min, path[--i][j]);
                        break;
                    case 'B':
                    case 'L':
                        min = Math.min(min, path[i][--j]);
                        break;
                }
            }
            return min >= 0 ? 1 : (-min + 1);
        }
    }

    /**
     * WA : sum中标示的路径不一定是min中标示的路径
     */
    public class Solution3 {
        public int calculateMinimumHP(int[][] dungeon) {
            int[][] min = new int[dungeon.length][dungeon[0].length],
                    sum = new int[dungeon.length][dungeon[0].length];

            sum[0][0] = dungeon[0][0] < 0 ? dungeon[0][0] : -1;
            min[0][0] = dungeon[0][0] < 0 ? dungeon[0][0] : -1;
            int fix = dungeon[0][0] >= 0 ? dungeon[0][0] + 1 : 0;

            for (int i = 1; i < min.length; i++) {
                sum[i][0] = sum[i - 1][0] + dungeon[i][0];
                min[i][0] = min[i - 1][0] + (dungeon[i][0] < 0 ? dungeon[i][0] : 0);
            }
            for (int i = 1; i < min[0].length; i++) {
                sum[0][i] = sum[0][i - 1] + dungeon[0][i];
                min[0][i] = min[0][i - 1] + (dungeon[0][i] < 0 ? dungeon[0][i] : 0);
            }

            for (int i = 1; i < sum.length; i++) {
                for (int j = 1; j < sum[0].length; j++) {
                    sum[i][j] = Math.max(sum[i][j - 1], sum[i - 1][j]) + dungeon[i][j];
                    if (dungeon[i][j] >= 0) {
                        min[i][j] = Math.max(min[i - 1][j], min[i][j - 1]);
                    } else {
                        int tmp1 = sum[i - 1][j] + dungeon[i][j];
                        int tmp2 = (tmp1 >= min[i - 1][j]) ? min[i - 1][j] : tmp1;
                        int tmp3 = sum[i][j - 1] + dungeon[i][j];
                        int tmp4 = (tmp3 >= min[i][j - 1]) ? min[i][j - 1] : tmp3;
                        min[i][j] = Math.max(tmp2, tmp4);
                    }
                }
            }
            int finalMin = min[min.length - 1][min[0].length - 1] + fix;
            return finalMin >= 0 ? 1 : (-finalMin + 1);
        }
    }

    /**
     * Accept
     * Note ： 正向求M(i,j)困难，~~反向~~~求M(0,0)
     * Reference http://www.cnblogs.com/easonliu/p/4237644.html
     *              http://blog.csdn.net/ljiabin/article/details/42616291
     * Tag : 二分查找 + DP ? 用到了BS？
     * 求可行路径中的最小子序列和？？那怎么求所有的可行路径？
     */
    public class Solution {
        public int calculateMinimumHP(int[][] dungeon) {
            final int I = dungeon.length, J = dungeon[0].length;
            int[][] min = new int[I][J];
            min[I - 1][J - 1] = dungeon[I - 1][J - 1] < 0 ? -dungeon[I - 1][J - 1] : 0;
            //min中单元格必须大于等于0，即勇士的血不能低于0，否则错误
            for (int i = J - 2; i >= 0; i--) {
                min[I - 1][i] = Math.max(min[I - 1][i + 1] - dungeon[I - 1][i], 0);
            }
            for (int i = I - 2; i >= 0; i--) {
                min[i][J - 1] = Math.max(min[i + 1][J - 1] - dungeon[i][J - 1], 0);
            }
            for (int i = I - 2; i >= 0; i--) {
                for (int j = J - 2; j >= 0; j--) {
                    min[i][j] = Math.max(Math.min(min[i][j + 1], min[i + 1][j]) - dungeon[i][j], 0);
                }
            }
            int res = min[0][0] + 1;
            return res;
        }
    }

    static public void main(String[] args) {
        Solution solution = new DungeonGame().new Solution();
        solution.calculateMinimumHP(new int[][]{
                {-3, 5}  //4
        });
        solution.calculateMinimumHP(new int[][]{
                {1, -4, 5, -99}, {2, -2, -2, -1} //3
        });
        solution.calculateMinimumHP(new int[][]{
                {0, 0}, {-5, -4} //5
        });
        solution.calculateMinimumHP(new int[][]{
                {3, -20, 30}, {-3, 4, 0} //1
        });
        solution.calculateMinimumHP(new int[][]{
                {1, -2, 3}, {2, -2, -2}  //2
        });
        solution.calculateMinimumHP(new int[][]{
                {-200} //201
        });
        solution.calculateMinimumHP(new int[][]{
                {0, 5}, {-2, -3} //1
        });
        solution.calculateMinimumHP(new int[][]{
                {0, 0} //1
        });

    }
}
