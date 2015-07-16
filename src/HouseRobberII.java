/**
 * Created by Feiyu on 2015/7/15.
 * 做了很久！WA还是因为DP考虑的不周全，
 * 注意Dp问题要考虑清楚最优子结构（最优解由最优子解构成）
 *  和公共子问题是什么？
 **/
public class HouseRobberII {
    /**
     * Wrong Answer : 偷不偷一个点与前面有关，也和后面有关
     * Input: [2,1,1,2]
     * Output: 4
     * Expected: 3
     */
    public class Solution1 {
        public int rob(int[] nums) {
            if (nums.length == 0 || nums == null)
                return 0;
            else if (nums.length == 1)
                return nums[0];
            else if (nums.length == 2)
                return Math.max(nums[0], nums[1]);
            else if (nums.length == 3)
                return Math.max(nums[0], Math.max(nums[1], nums[2]));

            int[] money = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int maxMoneyK = 0,
                        realPrevI = i > 0 ? i - 1 : nums.length - 1;
                int KStart = (i - 2 < 0) ? (i - 2 + nums.length) : (i - 2),
                        KEnd = (i + 2) <= (nums.length - 1) ? (i + 2) : (i + 2 - nums.length);
                int realKStart = Math.min(KStart, KEnd),
                        realKEnd = Math.max(KEnd, KStart);
                for (int k = realKStart; k <= realKEnd; k++) {
                    maxMoneyK = Math.max(maxMoneyK, money[k]);
                }
                money[i] = Math.max(maxMoneyK + nums[i], money[realPrevI]);
            }
            return money[nums.length - 1];
        }
    }

    /**
     * Wrong Answer : 下标-0,1作为起点正向绕一圈，反向绕一圈。错在中间点是可以根据money大小隔1-N个的
     * Input: [1,3,1,3,100]
     * Output: 6
     * Expected: 103
     */
    public class Solution2 {
        public int rob(int[] nums) {
            if (nums.length == 0 || nums == null)
                return 0;
            else if (nums.length == 1)
                return nums[0];
            else if (nums.length == 2)
                return Math.max(nums[0], nums[1]);
            else if (nums.length == 3)
                return Math.max(nums[0], Math.max(nums[1], nums[2]));


            int a1 = 0, a2 = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums.length % 2 == 0) {
                    if (i % 2 == 0)
                        a1 += nums[i];
                    else
                        a2 += nums[i];
                } else {
                    if (i % 2 == 0 && i != nums.length - 1)
                        a1 += nums[i];
                    else if (i % 2 == 1)
                        a2 += nums[i];
                }
            }
            int a3 = 0, a4 = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums.length % 2 == 0) {
                    if (i % 2 == 0)
                        a3 += nums[i];
                    else
                        a4 += nums[i];
                } else {
                    if (i % 2 == 0 && i != 0)
                        a3 += nums[i];
                    else if (i % 2 == 1)
                        a4 += nums[i];
                }
            }
            return Math.max(Math.max(a1, a2), Math.max(a3, a4));
        }
    }

    /**
     * Accept : 构造序列a1,a2,a3,a4,a1 求 path(1y,5y)-a1 和 path(1n,5n)
     * path(1y,jy)=path(1y,j-1n)+aj
     * path(1y,jn)=max{ path(1y,j-1n) , path(1y,j-1y) }
     * path(1n,jy)=path(1n,j-1n)+aj
     * path(1n,jn)=max{ path(1n,j-1n) , path(1n,j-1y) }
     * path是环状链表从起点至起点的一条满足条件的路径
     * 复杂度为O(4n)
     */
    public class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0 || nums == null)
                return 0;
            else if (nums.length == 1)
                return nums[0];
            else if (nums.length == 2)
                return Math.max(nums[0], nums[1]);
            else if (nums.length == 3)
                return Math.max(nums[0], Math.max(nums[1], nums[2]));

            int[][] paths = new int[2 * (nums.length + 1)][2];

            paths[0][0] = nums[0];

            for (int j = 1, i = 0; j < nums.length + 1; j++) {
                paths[2 * j][2 * i] = paths[2 * (j - 1) + 1][2 * i] + ((j < nums.length) ? nums[j] : nums[0]);
                paths[2 * j + 1][2 * i] = Math.max(
                        paths[2 * (j - 1)][2 * i],
                        paths[2 * (j - 1) + 1][2 * i]);
                paths[2 * j][2 * i + 1] = paths[2 * (j - 1) + 1][2 * i + 1] + ((j < nums.length) ? nums[j] : nums[0]);
                paths[2 * j + 1][2 * i + 1] = Math.max(
                        paths[2 * (j - 1)][2 * i + 1],
                        paths[2 * (j - 1) + 1][2 * i + 1]);
            }

            int result = Math.max(paths[2 * nums.length][0] - nums[0], paths[2 * nums.length + 1][1]);
            return result;
        }
    }

    public static void main(String... args) {
        Solution solution = new HouseRobberII().new Solution();
        solution.rob(new int[]{1});
    }
}
