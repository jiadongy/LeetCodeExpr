/**
 * Created by Feiyu on 2015/7/19.
 **/
public class JumpGame {
    /**
     * Accept 判断每次循环maxReach是否增加
     */
    public class Solution {
        public boolean canJump(int[] nums) {
            int posNow = 0;
            int maxReach = 0, lastMaxReach = -1;
            if (nums.length <= 1)//处理起点即终点的情况
                return true;
            while (maxReach > lastMaxReach) {
                lastMaxReach = maxReach;
                int indexAdd = 1;
                for (int i = nums[posNow]; i >= 1; i--) {
                    if (posNow + i >= nums.length - 1 || posNow + i + nums[posNow + i] >= nums.length - 1) {
                        return true;
                    } else {
                        if (maxReach <= posNow + i + nums[posNow + i]) {
                            maxReach = posNow + i + nums[posNow + i];
                            indexAdd = i;
                        }
                    }
                }
                posNow = posNow + indexAdd;
            }
            return false;
        }
    }

    public static void main(String[] a) {
        Solution solution = new JumpGame().new Solution();
        solution.canJump(new int[]{3, 2, 1, 0, 4});
        solution.canJump(new int[]{2, 3, 1, 1, 4});
    }
}
