/**
 * Created by Feiyu on 2015/7/15.
 **/
public class HouseRobber {
    public class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0 || nums == null)
                return 0;
            else if (nums.length == 1)
                return nums[0];
            else if (nums.length == 2)
                return Math.max(nums[0], nums[1]);

            int[][] money = new int[2][nums.length];
            money[0][0] = nums[0];
            money[1][0] = 0;
            for (int i = 1; i < nums.length; i++) {

                money[0][i] = nums[i] + money[1][i - 1];
                money[1][i] = Math.max(money[0][i - 1], money[1][i - 1]);
            }
            return Math.max(money[0][nums.length - 1], money[1][nums.length - 1]);
        }
    }

    public static void main(String... args) {
        Solution solution = new HouseRobber().new Solution();
        solution.rob(new int[]{2, 1, 1, 2});
    }
}
